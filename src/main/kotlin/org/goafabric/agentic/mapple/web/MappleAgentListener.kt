package org.goafabric.agentic.mapple.web

import dev.langchain4j.agentic.observability.AgentInvocationError
import dev.langchain4j.agentic.observability.AgentListener
import dev.langchain4j.agentic.observability.AgentRequest
import dev.langchain4j.agentic.observability.AgentResponse
import jakarta.enterprise.context.ApplicationScoped

/**
 * Quarkus registers every CDI bean implementing [AgentListener] on the agentic
 * agents, so these callbacks report which agent is currently working.
 */
@ApplicationScoped
class MappleAgentListener(private val registry: MappleRunRegistry) : AgentListener {

    override fun beforeAgentInvocation(agentRequest: AgentRequest) {
        registry.onAgentStarted(agentRequest.agentName())
    }

    override fun afterAgentInvocation(agentResponse: AgentResponse) {
        registry.onAgentFinished(agentResponse.agentName(), agentResponse.output())
    }

    override fun onAgentInvocationError(agentInvocationError: AgentInvocationError) {
        registry.onAgentFailed(agentInvocationError.agentName(), agentInvocationError.error()?.rootMessage())
    }
}
