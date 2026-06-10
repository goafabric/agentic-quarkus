package org.goafabric.agents.agentic

import dev.langchain4j.agentic.AgenticServices
import dev.langchain4j.agentic.mcp.McpAgent
import dev.langchain4j.mcp.client.McpClient
import dev.langchain4j.model.chat.ChatModel
import io.quarkiverse.langchain4j.mcp.runtime.McpClientName
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Produces


@ApplicationScoped
class AgentConfiguration {
    @Produces
    fun creativeWriterAgent(model: ChatModel): CreativeWriterAgent {
        return AgenticServices
            .agentBuilder(CreativeWriterAgent::class.java)
            .chatModel(model)
            .build()
    }

    @Produces
    fun calleeAgent(model: ChatModel, @McpClientName("callee") callee: McpClient): CalleeAgent {
        return McpAgent.builder(callee, CalleeAgent::class.java)
            .toolName("sayMyName")
            .build()
    }

}