package org.goafabric.agents.llm

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import io.quarkiverse.langchain4j.RegisterAiService
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox

@RegisterAiService
interface Assistant {
    @SystemMessage("You are a helpful assistant that can interact with mcp servers")
    @McpToolBox("callee")
    fun chat(@UserMessage message: String): String
}