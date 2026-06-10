package org.goafabric.agents.tools

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
class ToolsRunner(val assistant: ToolsConfiguration.Assistant) {
    fun onStart(@Observes ev: StartupEvent) {
        val scanner = Scanner(System.`in`)
        while (true) {
            println("[User]: ")
            println("[Agent]: " + assistant.chat(scanner.nextLine()));
        }
    }
}