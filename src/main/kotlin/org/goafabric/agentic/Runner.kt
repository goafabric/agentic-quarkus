package org.goafabric.agentic

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.goafabric.agentic.agents.CalleeAgent
import org.goafabric.agentic.agents.SuperAgent
import org.goafabric.agentic.assistant.Assistant
import java.util.*

@ApplicationScoped
class Runner(private val calleeAgent: CalleeAgent,
             private val superAgent: SuperAgent,
             private val assistant: Assistant,
             @param:ConfigProperty(name = "agentic.mode") private val mode: String) {

    fun onStart(@Observes ev: StartupEvent) {
        //println("### " + calleeAgent.sayMyName("homer"))
        try {
            val scanner = Scanner(System.`in`)
            while (true) {
                println("[User]: ")
                when (mode) {
                    "agentic" -> println("[Agent]: " + superAgent.execute(scanner.nextLine()));
                    "assistant" -> println("[Agent]: " + assistant.chat(scanner.nextLine()));
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}