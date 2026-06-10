package org.goafabric.agents.agentic.agents

import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class Orchestrator(private val creativeWriterAgent: CreativeWriterAgent,
                   private val calleeAgent: CalleeAgent) {
    fun play(message : String): String {
        val tokens = message.split(" ")
        if (tokens.size < 2) error("not enough info")
        return when (tokens[0]) {
            "say" -> calleeAgent.sayMyName(tokens[1])
            "story" -> creativeWriterAgent.generateStory(tokens[1])
            else -> error("unknown keyword " + tokens[0])
        }
    }
}