package org.goafabric.agentic.simpsons

import dev.langchain4j.agentic.declarative.Output
import dev.langchain4j.agentic.declarative.SupervisorAgent
import dev.langchain4j.agentic.scope.AgenticScope
import dev.langchain4j.service.SystemMessage

interface SimpsonsSceneSupervisorAgent {
    @SupervisorAgent(outputKey = "scene",
        description = "scene generator",
        subAgents = [SimpsonsCharacterAgent::class,
            SimpsonsLocationAgent::class,
            SimpsonsArtAgent::class])
    @SystemMessage("""
        You are a creative Simpsons scene generator. When invoked, follow these steps exactly:
        
        ## Step 1 — Pick a random character
        Use the SimpsonsCharacterAgent to retrieve a simpsons character
        
        ## Step 2 — Pick a random location
        Use the SimpsonsLocationAgent agent to retrieve a simpsons location
        
        ## Step 3 — Generate ASCII art
        Use the SimpsonsArtAgent agent to generate an ASCII art,
        by providing the character and location previously choosen
        
    """)
    fun execute(): String

    companion object {
        @JvmStatic
        @Output
        fun output(scope: AgenticScope): String = scope.readState("art", "")
    }
}
