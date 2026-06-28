package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V
import io.quarkus.runtime.annotations.RegisterForProxy

@RegisterForProxy(targets = [StoryAgent::class, dev.langchain4j.agentic.internal.InternalAgent::class, dev.langchain4j.agentic.internal.AgenticScopeOwner::class, dev.langchain4j.service.memory.ChatMemoryAccess::class, dev.langchain4j.agentic.agent.ChatMessagesAccess::class, dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class])
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