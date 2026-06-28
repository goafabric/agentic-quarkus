package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox
import io.quarkus.runtime.annotations.RegisterForProxy

@RegisterForProxy(targets = [CalleeAgent::class, dev.langchain4j.agentic.internal.InternalAgent::class, dev.langchain4j.agentic.internal.AgenticScopeOwner::class, dev.langchain4j.service.memory.ChatMemoryAccess::class, dev.langchain4j.agentic.agent.ChatMessagesAccess::class, dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class])
interface CalleeAgent {
    @Agent(outputKey = "message", description = "Says a name via callee mcp")

    @UserMessage("Use the sayMyName tool to say the following name: {{name}}")
    @McpToolBox("callee")
    fun sayMyName(@V("name") name: String): String
}