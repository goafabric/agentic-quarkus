package org.goafabric.agentic.mapple.web

import jakarta.annotation.PreDestroy
import jakarta.enterprise.context.ApplicationScoped
import org.goafabric.agentic.mapple.TestCaseLoader
import org.goafabric.agentic.mapple.agents.MappleReturnAgent
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@ApplicationScoped
class MappleRunService(
    private val mappleReturnAgent: MappleReturnAgent,
    private val registry: MappleRunRegistry,
) {

    private val executor: ExecutorService = Executors.newSingleThreadExecutor { runnable ->
        Thread(runnable, "mapple-run").apply { isDaemon = true }
    }

    fun start(caseId: String): RunStart {
        val customerCase = TestCaseLoader.load(caseId)
        val runId = registry.create(caseId)

        executor.submit {
            registry.markRunning(runId)
            try {
                val result = registry.runWith(runId) { mappleReturnAgent.execute(customerCase) }
                registry.complete(runId, result)
            } catch (t: Throwable) {
                registry.fail(runId, t.rootMessage())
            }
        }

        return RunStart(runId, caseId)
    }

    fun status(runId: String): RunStatus? = registry.snapshot(runId)

    @PreDestroy
    fun shutdown() {
        executor.shutdownNow()
    }
}

internal fun Throwable.rootMessage(): String =
    generateSequence(this) { it.cause }
        .mapNotNull { it.message }
        .lastOrNull()
        ?: toString()

data class RunStart(val runId: String, val caseId: String)
