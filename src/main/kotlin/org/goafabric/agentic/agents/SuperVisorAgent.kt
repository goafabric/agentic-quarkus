package org.goafabric.agentic.agents

import dev.langchain4j.agentic.declarative.SupervisorAgent
import dev.langchain4j.agentic.supervisor.SupervisorResponseStrategy
import dev.langchain4j.service.V
import io.quarkus.runtime.annotations.RegisterForProxy
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForProxy(targets = [SuperVisorAgent::class, dev.langchain4j.agentic.internal.InternalAgent::class, dev.langchain4j.agentic.internal.AgenticScopeOwner::class, dev.langchain4j.service.memory.ChatMemoryAccess::class, dev.langchain4j.agentic.agent.ChatMessagesAccess::class, dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class])
@RegisterForReflection(targets = [SupervisorAgent::class, dev.langchain4j.agentic.Agent::class])
interface SuperVisorAgent {

    @SupervisorAgent(
        subAgents = [CalleeAgent::class, PersonAgent::class, ObservationAgent::class],
        responseStrategy = SupervisorResponseStrategy.LAST
    )
    fun execute(@V("request") request: String): String
}
