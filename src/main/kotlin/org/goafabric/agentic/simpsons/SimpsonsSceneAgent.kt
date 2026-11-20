package org.goafabric.agentic.simpsons

import dev.langchain4j.agentic.declarative.Output
import dev.langchain4j.agentic.declarative.SequenceAgent
import dev.langchain4j.agentic.scope.AgenticScope

interface SimpsonsSceneAgent {
    @SequenceAgent(outputKey = "scene",
        description = "scene generator",
        subAgents = [SimpsonsCharacterAgent::class,
        SimpsonsLocationAgent::class, SimpsonsArtAgent::class])
    fun execute(): String

    companion object {
        @JvmStatic
        @Output
        fun output(scope: AgenticScope): String = scope.readState("art", "")
    }
}
