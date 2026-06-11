package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V

interface StoryAgent {
    @Agent(outputKey = "story", description = "Generates a story based on the given topic")

    @UserMessage(
        """
            You are a creative writer.
            Generate a draft of a story no more than
            3 sentences long around the given topic.
            Return only the story and nothing else.
            The topic is {{topic}}.            
            """
    )
    fun generateStory(@V("topic") topic: String): String
}