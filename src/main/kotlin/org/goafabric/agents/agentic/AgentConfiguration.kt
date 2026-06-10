package org.goafabric.agents.agentic

import dev.langchain4j.agentic.AgenticServices
import dev.langchain4j.model.chat.ChatModel
import jakarta.ws.rs.Produces


class AgentConfiguration {
    @Produces
    fun yo(model: ChatModel): CreativeWriter {
        val creativeWriter = AgenticServices
            .agentBuilder(CreativeWriter::class.java)
            .chatModel(model)
            .outputKey("story")
            .build()


        creativeWriter.generateStory("donkey")
        return creativeWriter
    }
}