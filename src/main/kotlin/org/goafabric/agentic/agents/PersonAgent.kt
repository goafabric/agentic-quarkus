package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox
import io.quarkus.runtime.annotations.RegisterForProxy

@RegisterForProxy(targets = [PersonAgent::class, dev.langchain4j.agentic.internal.InternalAgent::class, dev.langchain4j.agentic.internal.AgenticScopeOwner::class, dev.langchain4j.service.memory.ChatMemoryAccess::class, dev.langchain4j.agentic.agent.ChatMessagesAccess::class, dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class])
interface PersonAgent {
    @Agent(outputKey = "persons", description = "Finds persons by name via person mcp")
    @McpToolBox("person")

    @UserMessage("Use the find tool to search for persons matching the given name. {{firstName}}")
    fun findPerson(@V("firstName") firstName: String): String
}