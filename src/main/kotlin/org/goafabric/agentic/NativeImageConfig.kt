package org.goafabric.agentic

import io.quarkus.runtime.annotations.RegisterForProxy
import org.goafabric.agentic.agents.CalleeAgent
import org.goafabric.agentic.agents.PersonAgent
import org.goafabric.agentic.agents.StoryAgent

@RegisterForProxy(targets = [
    CalleeAgent::class,
    dev.langchain4j.agentic.internal.InternalAgent::class,
    dev.langchain4j.agentic.internal.AgenticScopeOwner::class,
    dev.langchain4j.service.memory.ChatMemoryAccess::class,
    dev.langchain4j.agentic.agent.ChatMessagesAccess::class,
    dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class,
])
@RegisterForProxy(targets = [
    PersonAgent::class,
    dev.langchain4j.agentic.internal.InternalAgent::class,
    dev.langchain4j.agentic.internal.AgenticScopeOwner::class,
    dev.langchain4j.service.memory.ChatMemoryAccess::class,
    dev.langchain4j.agentic.agent.ChatMessagesAccess::class,
    dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class,
])
@RegisterForProxy(targets = [
    StoryAgent::class,
    dev.langchain4j.agentic.internal.InternalAgent::class,
    dev.langchain4j.agentic.internal.AgenticScopeOwner::class,
    dev.langchain4j.service.memory.ChatMemoryAccess::class,
    dev.langchain4j.agentic.agent.ChatMessagesAccess::class,
    dev.langchain4j.observability.api.listener.AiServiceResponseReceivedListener::class,
])
class NativeImageConfig
