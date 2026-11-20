package org.goafabric.agentic.simpsons

import dev.langchain4j.agentic.declarative.SequenceAgent

interface SimpsonsSceneAgent {
    @SequenceAgent(outputKey = "art",
        description = "scene generator",
        subAgents = [SimpsonsCharacterAgent::class,
        SimpsonsLocationAgent::class, SimpsonsArtAgent::class])
    fun execute(): String
}