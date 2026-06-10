package org.goafabric.agents.agentic

import dev.langchain4j.agentic.AgenticServices
import dev.langchain4j.model.chat.ChatModel
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Produces


@ApplicationScoped
class AgentConfiguration {
    @Produces
    fun creativeWriterAgent(model: ChatModel): CreativeWriterAgent {
        return AgenticServices
            .agentBuilder(CreativeWriterAgent::class.java)
            .chatModel(model)
            .outputKey("story")
            .build()
    }
}