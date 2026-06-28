/*
package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.agentic.AgenticServices
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy
import dev.langchain4j.model.chat.ChatModel
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V
import jakarta.enterprise.context.ApplicationScoped
import jakarta.ws.rs.Produces


@ApplicationScoped
class SuperVisorAgentConfig {
    @Produces
    fun superVisorAgent(chatModel: ChatModel,
                        calleeAgent: CalleeAgent,
                        personAgent: PersonAgent,
                        observationAgent: ObservationAgent,
                        storyAgent: StoryAgent) : SupervisorAgent {
        return AgenticServices
            .supervisorBuilder(SupervisorAgent::class.java)
            .chatModel(chatModel)
            .subAgents(calleeAgent, personAgent, observationAgent, storyAgent)
            .responseStrategy(SupervisorResponseStrategy.SUMMARY)
            .build()
    }

    interface SupervisorAgent {
        @UserMessage(
            """
            You are a supervisor agent.
            You can use the CalleeAgent to say names
            You can use the PersonAgent to find persons
            You can use the ObservationAgent to find observations for patients
            You can us the StoryAgent to write a story
            """
        )

        @Agent
        fun execute(@V("request") request: String): String
    }
}*/
