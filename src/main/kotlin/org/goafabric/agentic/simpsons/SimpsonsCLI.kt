package org.goafabric.agentic.controller

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.goafabric.agentic.simpsons.SimpsonsCharacterAgent

@ApplicationScoped
class SimpsonsCLI(@ConfigProperty(name = "agentic.mode") private val mode: String,
                  private val simpsonsCharacterAgent : SimpsonsCharacterAgent) {

    fun onStart(@Observes ev: StartupEvent) {
        if (mode != "simpsons") return
        println("## executing Simpsons agent")
        println(simpsonsCharacterAgent.execute())
    }
}