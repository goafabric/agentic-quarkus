package org.goafabric.agents.tools

import dev.langchain4j.mcp.McpToolProvider
import dev.langchain4j.mcp.client.McpClient
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.mcp.runtime.McpClientName
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces

//https://docs.quarkiverse.io/quarkus-langchain4j/dev/mcp.html#_declarative_tool_provider_generation
//https://docs.langchain4j.dev/tutorials/agents/#mcp-based-tool-agents
@ApplicationScoped
class AssistantConfiguration {
    interface Assistant {
        @SystemMessage("You are a helpful assistant that can interact with mcp servers")
        fun chat(@UserMessage message: String): String
    }

    @Produces
    fun assistantService(model: ChatModel, @McpClientName("callee") callee: McpClient): Assistant {
        return AiServices.builder(Assistant::class.java)
            .chatModel(model)
            .toolProvider(McpToolProvider.builder()
                .mcpClients(callee).build())
            .build()
    }

}
