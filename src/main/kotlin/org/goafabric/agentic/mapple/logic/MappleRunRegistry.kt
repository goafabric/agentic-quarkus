package org.goafabric.agentic.mapple.logic

import dev.langchain4j.agentic.observability.AgentInvocationError
import dev.langchain4j.agentic.observability.AgentListener
import dev.langchain4j.agentic.observability.AgentRequest
import dev.langchain4j.agentic.observability.AgentResponse
import jakarta.enterprise.context.ApplicationScoped
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@ApplicationScoped
class MappleRunRegistry : AgentListener {

    private val runs = ConcurrentHashMap<String, RunState>()
    private val currentRunId = ThreadLocal<String?>()

    override fun beforeAgentInvocation(agentRequest: AgentRequest) {
        onAgentStarted(agentRequest.agentName())
    }

    override fun afterAgentInvocation(agentResponse: AgentResponse) {
        onAgentFinished(agentResponse.agentName(), agentResponse.output())
    }

    override fun onAgentInvocationError(agentInvocationError: AgentInvocationError) {
        onAgentFailed(agentInvocationError.agentName(), agentInvocationError.error()?.rootMessage())
    }

    fun create(caseId: String): String {
        val runId = UUID.randomUUID().toString()
        val run = RunState(runId, caseId)
        AGENT_ORDER.forEach { run.agents[it] = AgentState(it) }
        runs[runId] = run
        return runId
    }

    /**
     * The agentic runtime invokes the listeners synchronously on the calling thread,
     * so a ThreadLocal is enough to attach listener callbacks to the active run.
     */
    fun <T> runWith(runId: String, block: () -> T): T {
        currentRunId.set(runId)
        return try {
            block()
        } finally {
            currentRunId.remove()
        }
    }

    fun markRunning(runId: String) {
        runs[runId]?.let { run -> synchronized(run) { run.phase = "RUNNING" } }
    }

    fun onAgentStarted(name: String?) {
        val agentName = name ?: return
        if (!isTrackedAgent(agentName)) return
        withCurrentRun { run ->
            synchronized(run) {
                val agent = run.agents.getOrPut(agentName) { AgentState(agentName) }
                agent.phase = "RUNNING"
                agent.startedAt = System.currentTimeMillis()
                run.currentAgent = agentName
            }
        }
    }

    fun onAgentFinished(name: String?, output: Any?) {
        val agentName = name ?: return
        if (!isTrackedAgent(agentName)) return
        withCurrentRun { run ->
            synchronized(run) {
                val agent = run.agents[agentName] ?: return@withCurrentRun
                agent.phase = "DONE"
                agent.output = output?.toString()
                agent.finishedAt = System.currentTimeMillis()
                if (run.currentAgent == agentName) run.currentAgent = null
            }
        }
    }

    fun onAgentFailed(name: String?, error: String?) {
        val agentName = name ?: return
        if (!isTrackedAgent(agentName)) return
        withCurrentRun { run ->
            synchronized(run) {
                val agent = run.agents[agentName] ?: return@withCurrentRun
                agent.phase = "ERROR"
                agent.error = error
                agent.finishedAt = System.currentTimeMillis()
                if (run.currentAgent == agentName) run.currentAgent = null
            }
        }
    }

    fun complete(runId: String, result: String) {
        runs[runId]?.let { run ->
            synchronized(run) {
                run.phase = "DONE"
                run.result = result
                run.status = field(result, "Status")
                run.reason = field(result, "Reason")
                run.currentAgent = null
                run.finishedAt = System.currentTimeMillis()
            }
        }
    }

    fun fail(runId: String, error: String) {
        runs[runId]?.let { run ->
            synchronized(run) {
                run.phase = "ERROR"
                run.error = error
                run.currentAgent = null
                run.finishedAt = System.currentTimeMillis()
            }
        }
    }

    fun snapshot(runId: String): RunStatus? = runs[runId]?.let { run ->
        synchronized(run) {
            RunStatus(
                runId = run.runId,
                caseId = run.caseId,
                state = run.phase,
                currentAgent = run.currentAgent,
                agents = run.agents.values.map { AgentStatus(it.name, it.phase, it.output, it.error) },
                result = run.result,
                status = run.status,
                reason = run.reason,
                error = run.error,
            )
        }
    }

    private inline fun withCurrentRun(block: (RunState) -> Unit) {
        val runId = currentRunId.get() ?: return
        val run = runs[runId] ?: return
        block(run)
    }

    private fun isTrackedAgent(name: String): Boolean =
        name.endsWith("Agent", ignoreCase = true)

    private fun field(text: String, name: String): String? =
        text.lineSequence()
            .map { it.trim() }
            .firstOrNull { it.startsWith("$name:") }
            ?.substringAfter("$name:")
            ?.trim()
            ?.takeIf { it.isNotEmpty() }

    private class RunState(val runId: String, val caseId: String) {
        @Volatile var phase: String = "QUEUED"
        @Volatile var currentAgent: String? = null
        @Volatile var result: String? = null
        @Volatile var status: String? = null
        @Volatile var reason: String? = null
        @Volatile var error: String? = null
        @Volatile var finishedAt: Long? = null
        val agents = linkedMapOf<String, AgentState>()
    }

    private class AgentState(val name: String) {
        @Volatile var phase: String = "PENDING"
        @Volatile var output: String? = null
        @Volatile var error: String? = null
        @Volatile var startedAt: Long? = null
        @Volatile var finishedAt: Long? = null
    }

    companion object {
        private val AGENT_ORDER = listOf("intakeAgent", "guaranteeAgent", "outputAgent")
    }
}

data class AgentStatus(
    val name: String,
    val state: String,
    val output: String? = null,
    val error: String? = null,
)

data class RunStatus(
    val runId: String,
    val caseId: String,
    val state: String,
    val currentAgent: String? = null,
    val agents: List<AgentStatus> = emptyList(),
    val result: String? = null,
    val status: String? = null,
    val reason: String? = null,
    val error: String? = null,
)
