package org.goafabric.agents.tools

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.goafabric.agents.agentic.agents.CalleeAgent
import org.goafabric.agents.agentic.agents.CreativeWriterAgent

@ApplicationScoped
class AgentRunner(private val creativeWriterAgent: CreativeWriterAgent,
                  private val calleeAgent: CalleeAgent
) {

    fun onStart(@Observes ev: StartupEvent) {
        //println("### story ####\r\n" + creativeWriterAgent.generateStory("fairy"))
        println("### " + calleeAgent.sayMyName("homer"))
    }
}