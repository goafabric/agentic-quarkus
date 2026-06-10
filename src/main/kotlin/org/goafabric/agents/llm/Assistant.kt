package org.goafabric.agents.llm

import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage

interface Assistant {
    @SystemMessage("You are a helpful assistant that can interact with mcp servers")
    //@McpToolBox("person")
    fun chat(@UserMessage message: String): String
}