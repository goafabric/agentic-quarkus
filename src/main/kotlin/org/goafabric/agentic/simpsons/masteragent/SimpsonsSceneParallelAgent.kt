package org.goafabric.agentic.simpsons.masteragent

import dev.langchain4j.agentic.declarative.Output
import dev.langchain4j.agentic.declarative.ParallelAgent
import dev.langchain4j.agentic.declarative.SequenceAgent
import dev.langchain4j.agentic.scope.AgenticScope
import org.goafabric.agentic.simpsons.subagent.SimpsonsArtAgent
import org.goafabric.agentic.simpsons.subagent.SimpsonsCharacterAgent
import org.goafabric.agentic.simpsons.subagent.SimpsonsLocationAgent

interface SimpsonsSceneParallelAgent {
    @SequenceAgent(
        outputKey = "scene",
        description = "scene generator (parallel cast)",
        subAgents = [SimpsonsCastParallelAgent::class, SimpsonsArtAgent::class]
    )
    fun execute(): String

    companion object {
        @JvmStatic @Output fun output(scope: AgenticScope): String = scope.readState("art", "")
    }
}

interface SimpsonsCastParallelAgent {
    @ParallelAgent(
        name = "castPicker",
        description = "Picks a random Simpsons character and location in parallel",
        subAgents = [SimpsonsCharacterAgent::class, SimpsonsLocationAgent::class]
    )
    fun execute(): String
}