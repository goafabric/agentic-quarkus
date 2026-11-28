package org.goafabric.agentic.mapple

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.goafabric.agentic.mapple.agents.MappleReturnAgent

@ApplicationScoped
class MappleCLI(@ConfigProperty(name = "agentic.mode") private val mode: String,
                private val mappleReturnAgent: MappleReturnAgent,

) {

    fun onStart(@Observes ev: StartupEvent) {
        if (mode != "mapple") return
        println("## executing Simpsons agent")

        println(mappleReturnAgent.execute())
        Quarkus.asyncExit()
    }
}