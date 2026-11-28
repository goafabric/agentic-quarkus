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

        val caseId = "case-01-homer"
        println("## executing Mapple return agent for $caseId")
        println(mappleReturnAgent.execute(loadCase(caseId)))
        Quarkus.asyncExit()
    }

    private fun loadCase(caseId: String): String {
        val resource = "mapple-returns/testdata/cases/$caseId.md"
        return Thread.currentThread().contextClassLoader.getResourceAsStream(resource)
            ?.bufferedReader()
            ?.use { it.readText() }
            ?: throw IllegalArgumentException("Mapple test case '$caseId' not found at classpath:$resource")
    }

}
