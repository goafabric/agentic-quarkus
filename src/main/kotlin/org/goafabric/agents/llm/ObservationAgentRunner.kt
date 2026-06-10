package org.goafabric.agents.llm

import jakarta.enterprise.context.ApplicationScoped
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
class ObservationAgentRunner(
    val mcpAssistant: Assistant
) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass.name)

    fun run() {
        try {
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
