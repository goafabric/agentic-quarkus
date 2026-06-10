package org.goafabric.agents.agentic

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V



interface CalleeAgent {
    /*
    @UserMessage(
        """
            You are a callee agent.
            That can say names
            """
    )

     */

    @Agent(outputKey = "message", description = "Say name")
    fun sayMyName(@V("name") name: String): String
}