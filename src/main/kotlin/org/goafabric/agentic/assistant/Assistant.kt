package org.goafabric.agentic.assistant

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox

interface Assistant {
    @SystemMessage("""
        You are a helpful assistant that can interact with mcp servers
        Use the callee mcp to say names
        Use the person mcp to find and search for persons
        Use the observation mcp retrieve observation data like laboratory and vital signs for persons
        """)
    @McpToolBox("callee", "person", "observation")
    fun chat(@UserMessage message: String): String
}