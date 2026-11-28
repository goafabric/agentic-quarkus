package org.goafabric.agentic.mapple.agents

import dev.langchain4j.agentic.declarative.SequenceAgent

interface MappleReturnAgent {
    @SequenceAgent(outputKey = "output",
        description = "Orchestrates the Mapple guarantee return flow (intake, guarantee, output)",
        subAgents = [MappleIntakeAgent::class,
            MappleGuaranteeAgent::class,
            MappleOutputAgent::class])
    fun execute(): String

}
