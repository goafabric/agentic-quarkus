package org.goafabric.agentic.simpsons

import dev.langchain4j.agentic.declarative.ParallelAgent

interface SimpsonsCastParallelAgent {
    @ParallelAgent(
        name = "castPicker",
        description = "Picks a random Simpsons character and location in parallel",
        subAgents = [SimpsonsCharacterAgent::class, SimpsonsLocationAgent::class]
    )
    fun execute(): String
}
