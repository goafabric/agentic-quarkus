package org.goafabric.agents.tools

import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.tool.ToolProvider
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces

//https://docs.quarkiverse.io/quarkus-langchain4j/dev/mcp.html#_declarative_tool_provider_generation
//https://docs.langchain4j.dev/tutorials/agents/#mcp-based-tool-agents
@ApplicationScoped
class ToolsConfiguration {
    @Produces
    fun mcpBot(model: ChatModel, toolProvider: ToolProvider): Assistant {
        return AiServices.builder(Assistant::class.java)
            .chatModel(model)
            .toolProvider(toolProvider)
            .build()
    }

    /*
    @Produces
    fun calleeMcp(@ConfigProperty(name = "quarkus.langchain4j.mcp.callee.url") url: String) =
        DefaultMcpClient.Builder().transport(StreamableHttpMcpTransport.Builder().url(url).build()).build()


     */
    /*
    @Produces
    fun mcpToolProvider(@McpClientName("callee") callee: McpClient) : ToolProvider {
        return McpToolProvider.builder().mcpClients(callee).build()
    }

     */

}
