package org.goafabric.agents.llm

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
class AgentRunner(
    val mcpAssistant: Assistant
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    fun onStart(@Observes ev: StartupEvent) {
        try {
            //println("[Agent]: " + mcpAssistant.chat("please greet me"));
            val scanner = Scanner(System.`in`)
            while (true) {
                println("[User]: ")
                println("[Agent]: " + mcpAssistant.chat(scanner.nextLine()));
            }
        } catch (e: Exception) {
            log.error("error", e)
        }
    }

}
