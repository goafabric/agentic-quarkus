package org.goafabric.agentic.simpsons

import dev.langchain4j.agentic.declarative.SequenceAgent

interface SimpsonsSceneAgent {
    @SequenceAgent(outputKey = "scene",
        description = "scene generator",
        subAgents =[SimpsonsCharacterAgent::class,
        SimpsonsLocationAgent::class, SimpsonsSceneAgent::class])
    fun execute(): String
}