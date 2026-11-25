package org.goafabric.agentic.simpsons.masteragent

import dev.langchain4j.agentic.declarative.Output
import dev.langchain4j.agentic.declarative.SupervisorAgent
import dev.langchain4j.agentic.scope.AgenticScope
import dev.langchain4j.service.V
import org.goafabric.agentic.simpsons.subagent.SimpsonsArtAgent
import org.goafabric.agentic.simpsons.subagent.SimpsonsCharacterAgent
import org.goafabric.agentic.simpsons.subagent.SimpsonsLocationAgent

interface SimpsonsSceneSupervisorAgent {
    @SupervisorAgent(outputKey = "scene",
        description = "scene generator",
        subAgents = [SimpsonsCharacterAgent::class,
            SimpsonsLocationAgent::class,
            SimpsonsArtAgent::class])
    fun execute(@V("request") request: String): String

    companion object {
        @JvmStatic @Output fun output(scope: AgenticScope): String = scope.readState("art", "")
    }
}
