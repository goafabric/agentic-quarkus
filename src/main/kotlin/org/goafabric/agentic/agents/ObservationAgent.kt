package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox
import io.quarkus.runtime.annotations.RegisterForProxy

@RegisterForProxy(targets = [ObservationAgent::class, dev.langchain4j.agentic.internal.InternalAgent::class, dev.langchain4j.agentic.internal.AgenticScopeOwner::class, dev.langchain4j.service.memory.ChatMemoryAccess::class, dev.langchain4j.agentic.agent.ChatMessagesAccess::class, dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class])
interface ObservationAgent {
    @Agent(outputKey = "observations", description = "Find observations via the observation mcp")
    @McpToolBox("observation")

    @UserMessage("""
        Use the getVitalSignsByPatientName tool to search for vital signs matching the patientName {{firstName}}
        Use the getLaboratoryDataByPatientName tool to search for laboratory data matching the patientName {{firstName}}
    """)
    fun findObservation(@V("firstName") firstName: String): String
}