package org.goafabric.agents.agentic.agents

import dev.langchain4j.agentic.AgenticServices
import dev.langchain4j.model.chat.ChatModel
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces

@ApplicationScoped
class CreativeWriterAgentConfiguration {
    @Produces
    fun creativeWriterAgent(model: ChatModel): CreativeWriterAgent {
        return AgenticServices
            .agentBuilder(CreativeWriterAgent::class.java)
            .chatModel(model)
            .build()
    }
}