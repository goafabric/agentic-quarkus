package org.goafabric.agentic.agents

import jakarta.enterprise.context.ApplicationScoped

//Will be replaced by supervised agent pattern from langchain
@ApplicationScoped
class SuperAgent(private val storyAgent: StoryAgent,
                 private val calleeAgent: CalleeAgent,
                 private val personAgent: PersonAgent,
                 private val observationAgent: ObservationAgent
) {
    fun execute(message : String): String {
        val tokens = message.split(" ")
        if (tokens.size < 2) return "not enough parameters (say <p>, find <p>, observe <p>, story <p>"
        return when (tokens[0]) {
            "say" -> calleeAgent.sayMyName(tokens[1])
            "find" -> personAgent.findPerson(tokens[1], "Simpson")
            "observe" -> observationAgent.findObservation(tokens[1])
            "story" -> storyAgent.generateStory(tokens[1])
            else -> "unknown keyword $tokens[0]"
        }
    }
}