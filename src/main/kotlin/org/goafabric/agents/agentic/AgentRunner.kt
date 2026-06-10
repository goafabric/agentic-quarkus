package org.goafabric.agents.tools

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.goafabric.agents.agentic.agents.CalleeAgent
import org.goafabric.agents.agentic.agents.CreativeWriterAgent
import org.goafabric.agents.agentic.agents.Orchestrator
import org.goafabric.agents.agentic.agents.PersonAgent
import java.util.Scanner

@ApplicationScoped
class AgentRunner(private val creativeWriterAgent: CreativeWriterAgent,
                  private val calleeAgent: CalleeAgent,
                  private val personAgent: PersonAgent,
                  private val orchestrator: Orchestrator
) {

    fun onStart(@Observes ev: StartupEvent) {
        //println("### story ####\r\n" + creativeWriterAgent.generateStory("fairy"))
        //println("### " + calleeAgent.sayMyName("homer"))
        //println("###" + personAgent.findPerson("Homer", "Simpson"))
        orchestrate()
    }

    fun orchestrate() {
        val scanner = Scanner(System.`in`)
        while (true) {
            println("[User]: ")
            println("[Agent]: " + orchestrator.play(scanner.nextLine()));
        }
    }
}