package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V

interface CalleeAgent {
    @UserMessage("Use the sayMyName tool to say the following name: {{name}}")
    @Agent(outputKey = "message", description = "Says a name via MCP tool")
    fun sayMyName(@V("name") name: String): String
}