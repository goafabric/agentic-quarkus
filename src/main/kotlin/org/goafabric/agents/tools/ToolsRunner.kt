package org.goafabric.agents.tools

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

@ApplicationScoped
class ToolsRunner(@param:ConfigProperty(name = "tools.runner.enabled") private val enabled: Boolean,
    private val assistant: ToolsConfiguration.Assistant) {

    fun onStart(@Observes ev: StartupEvent) {
        if (enabled) {
            val scanner = Scanner(System.`in`)
            while (true) {
                println("[User]: ")
                println("[Agent]: " + assistant.chat(scanner.nextLine()));
            }
        }
    }
}