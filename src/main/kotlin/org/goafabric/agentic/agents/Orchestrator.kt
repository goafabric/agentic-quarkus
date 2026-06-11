package org.goafabric.agentic.agents

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class Orchestrator(private val creativeWriterAgent: CreativeWriterAgent,
                   private val calleeAgent: CalleeAgent,
                   private val personAgent: PersonAgent
) {
    fun play(message : String): String {
        val tokens = message.split(" ")
        if (tokens.size < 2) error("not enough info")
        return when (tokens[0]) {
            "say" -> calleeAgent.sayMyName(tokens[1])
            "find" -> personAgent.findPerson(tokens[1], "Simpson")
            "story" -> creativeWriterAgent.generateStory(tokens[1])
            else -> error("unknown keyword " + tokens[0])
        }
    }
}