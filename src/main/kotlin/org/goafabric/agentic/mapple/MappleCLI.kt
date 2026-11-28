package org.goafabric.agentic.mapple

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.goafabric.agentic.mapple.agents.MappleReturnAgent

@ApplicationScoped
class MappleCLI(@ConfigProperty(name = "agentic.mode") private val mode: String,
                @ConfigProperty(name = "agentic.mapple.cli", defaultValue = "false") private val cliEnabled: Boolean,
                private val mappleReturnAgent: MappleReturnAgent,
) {

    fun onStart(@Observes ev: StartupEvent) {
        if (mode != "mapple" || !cliEnabled) return

        SELECTED_CASES.forEach { caseId ->
            println("## executing Mapple return agent for $caseId")
            println(mappleReturnAgent.execute(MappleTestCases.load(caseId)))
            println()
        }
        Quarkus.asyncExit()
    }

    companion object {
        // hardcoded for now; add more case ids here to run them in sequence
        private val SELECTED_CASES = listOf("case-01-homer")
    }
}
