package org.goafabric.agents.tools

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.goafabric.agents.agentic.CalleeAgent
import org.goafabric.agents.agentic.CreativeWriterAgent

@ApplicationScoped
class AgentRunner(private val creativeWriterAgent: CreativeWriterAgent, private val calleeAgent: CalleeAgent) {

    fun onStart(@Observes ev: StartupEvent) {
        /*
        val story = creativeWriterAgent.generateStory("fairy")
        print("### story ####\r\n")
        println(story)

         */

        println("### " + calleeAgent.sayMyName("homer"))
    }
}