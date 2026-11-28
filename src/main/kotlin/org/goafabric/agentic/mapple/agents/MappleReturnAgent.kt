package org.goafabric.agentic.mapple.agents

import dev.langchain4j.agentic.declarative.SequenceAgent
import org.goafabric.agentic.mapple.MappleGuaranteeAgent
import org.goafabric.agentic.mapple.MappleIntakeAgent
import org.goafabric.agentic.mapple.MappleOutputAgent

interface MappleReturnAgent {
    @SequenceAgent(outputKey = "output",
        description = "Orchestrates the Mapple guarantee return flow (intake, guarantee, output)",
        subAgents = [MappleIntakeAgent::class,
            MappleGuaranteeAgent::class,
            MappleOutputAgent::class])
    fun execute(): String

}
