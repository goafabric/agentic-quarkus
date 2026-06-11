package org.goafabric.agentic

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.goafabric.agentic.agents.CalleeAgent
import org.goafabric.agentic.agents.CreativeWriterAgent
import org.goafabric.agentic.agents.Orchestrator
import org.goafabric.agentic.agents.PersonAgent
import org.goafabric.agentic.assistant.AssistantConfiguration
import java.util.*

@ApplicationScoped
class AgentRunner(private val creativeWriterAgent: CreativeWriterAgent,
                  private val calleeAgent: CalleeAgent,
                  private val personAgent: PersonAgent,
                  private val orchestrator: Orchestrator,
                  private val assistant: AssistantConfiguration.Assistant
) {

    fun onStart(@Observes ev: StartupEvent) {
        //println("### story ####\r\n" + creativeWriterAgent.generateStory("fairy"))
        //println("### " + calleeAgent.sayMyName("homer"))
        //println("###" + personAgent.findPerson("Homer", "Simpson"))
        orchestrate()
    }

    fun orchestrate() {
        try {
            val scanner = Scanner(System.`in`)
            while (true) {
                println("[User]: ")
                //println("[Agent]: " + orchestrator.play(scanner.nextLine()));
                println("[Agent]: " + assistant.chat(scanner.nextLine()));
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}