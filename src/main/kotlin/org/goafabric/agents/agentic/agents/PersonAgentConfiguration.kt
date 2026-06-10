package org.goafabric.agents.agentic.agents

import dev.langchain4j.agentic.AgenticServices
import dev.langchain4j.mcp.McpToolProvider
import dev.langchain4j.mcp.client.McpClient
import dev.langchain4j.model.chat.ChatModel
import io.quarkiverse.langchain4j.mcp.runtime.McpClientName
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces

@ApplicationScoped
class PersonAgentConfiguration {
    @Produces
    fun personAgent(model: ChatModel, @McpClientName("person") person: McpClient): PersonAgent {
        val toolProvider = McpToolProvider.builder().mcpClients(person).build()
        return AgenticServices
            .agentBuilder(PersonAgent::class.java)
            .chatModel(model)
            .toolProvider(toolProvider)
            .build()
    }
}
