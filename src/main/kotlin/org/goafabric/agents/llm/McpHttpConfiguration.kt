package org.goafabric.agents.llm

import dev.langchain4j.mcp.McpToolProvider
import dev.langchain4j.mcp.client.DefaultMcpClient
import dev.langchain4j.mcp.client.McpClient
import dev.langchain4j.mcp.client.transport.http.StreamableHttpMcpTransport
import dev.langchain4j.model.anthropic.AnthropicChatModel
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.tool.ToolProvider
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import java.time.Duration

//https://github.com/modelcontextprotocol/servers/tree/main/src
@ApplicationScoped
class McpHttpConfiguration {
    @Produces
    fun mcpBot(model: ChatModel): Assistant {
        val transport = StreamableHttpMcpTransport.Builder()
            .url("http://localhost:50800/mcp")
            .build()

        val mcpClient: McpClient = DefaultMcpClient.Builder()
            .transport(transport)
            .build()

        val toolProvider: ToolProvider = McpToolProvider.builder()
            .mcpClients(listOf(mcpClient))
            .build()

        return AiServices.builder(Assistant::class.java)
            .chatModel(model)
            .toolProvider(toolProvider)
            .build()
    }

    /*
    @Produces
    fun chatModelOpenAi(): ChatModel {
        return OpenAiChatModel.builder().apiKey("")
            .baseUrl("http://localhost:11434/v1")
            .modelName("gpt-oss:20b")
            .timeout(Duration.ofSeconds(60)).temperature(0.0)
            .build()
    }

     */


    @Produces
    fun chatModelAnthropic(): ChatModel {
        return AnthropicChatModel.builder().apiKey(System.getenv("ANTHROPIC_FOUNDRY_API_KEY"))
            .baseUrl("https://%s.services.ai.azure.com/anthropic/v1".format(System.getenv("ANTHROPIC_FOUNDRY_RESOURCE")))
            .modelName(System.getenv("ANTHROPIC_DEFAULT_SONNET_MODEL"))
            .timeout(Duration.ofSeconds(60)).temperature(0.0)
            .build()
    }
}
