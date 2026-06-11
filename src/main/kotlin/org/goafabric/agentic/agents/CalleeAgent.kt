package org.goafabric.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox

interface CalleeAgent {
    @Agent(outputKey = "message", description = "Says a name via callee mcp")

    @UserMessage("Use the sayMyName tool to say the following name: {{name}}")
    @McpToolBox("callee")
    fun sayMyName(@V("name") name: String): String
}