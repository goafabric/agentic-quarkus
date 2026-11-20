package org.goafabric.agentic.simpsons

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty

@ApplicationScoped
class SimpsonsCLI(@ConfigProperty(name = "agentic.mode") private val mode: String,
                  private val simpsonsCharacterAgent : SimpsonsCharacterAgent,
                  private val simpsonsLocationAgent : SimpsonsLocationAgent,
                  private val simpsonsArtAgent : SimpsonsArtAgent,
                  private val simpsonsSceneAgent: SimpsonsSceneAgent
    ) {

    fun onStart(@Observes ev: StartupEvent) {
        if (mode != "simpsons") return
        println("## executing Simpsons agent")
        /*
        val character = simpsonsCharacterAgent.execute()
        val location = simpsonsLocationAgent.execute()
        println(simpsonsArtAgent.execute(character, location))
         */

        println(simpsonsSceneAgent.execute())
        Quarkus.asyncExit()
    }
}