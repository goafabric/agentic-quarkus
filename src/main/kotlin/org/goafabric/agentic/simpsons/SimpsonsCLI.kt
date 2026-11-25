package org.goafabric.agentic.simpsons

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.goafabric.agentic.simpsons.masteragent.SimpsonsSceneParallelAgent
import org.goafabric.agentic.simpsons.masteragent.SimpsonsSceneSequenceAgent
import org.goafabric.agentic.simpsons.masteragent.SimpsonsSceneSupervisorAgent

@ApplicationScoped
class SimpsonsCLI(@ConfigProperty(name = "agentic.mode") private val mode: String,
                  private val simpsonsSceneSequenceAgent: SimpsonsSceneSequenceAgent,
                  private val simpsonsSceneSupervisorAgent: SimpsonsSceneSupervisorAgent,
                  private val simpsonsSceneParallelAgent: SimpsonsSceneParallelAgent,

) {

    fun onStart(@Observes ev: StartupEvent) {
        if (mode != "simpsons") return
        println("## executing Simpsons agent")

        //println(simpsonsSceneSequenceAgent.execute())
        //println(simpsonsSceneSupervisorAgent.execute("Generate a random Simpsons scene"))
        println(simpsonsSceneParallelAgent.execute())
        Quarkus.asyncExit()
    }
}