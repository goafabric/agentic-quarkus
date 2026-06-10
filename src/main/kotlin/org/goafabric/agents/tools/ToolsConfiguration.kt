package org.goafabric.agents.tools

import dev.langchain4j.mcp.McpToolProvider
import dev.langchain4j.mcp.client.McpClient
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.tool.ToolProvider
import io.quarkiverse.langchain4j.mcp.runtime.McpClientName
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces

//https://docs.quarkiverse.io/quarkus-langchain4j/dev/mcp.html#_declarative_tool_provider_generation
//https://docs.langchain4j.dev/tutorials/agents/#mcp-based-tool-agents
@ApplicationScoped
class ToolsConfiguration {
    @Produces
    fun mcpBot(model: ChatModel, @McpClientName("callee") calleeMcp: McpClient): Assistant {
        val toolProvider = McpToolProvider.builder().mcpClients(calleeMcp).build()
        return AiServices.builder(Assistant::class.java)
            .chatModel(model)
            .toolProvider(toolProvider)
            .build()
    }

    /*
    @Produces
    fun toolProvider(@McpClientName("callee") calleeMcp: McpClient) : ToolProvider {
        return McpToolProvider.builder().mcpClients(calleeMcp).build()
    }

     */

}
