package org.goafabric.agents.agentic.agents

import dev.langchain4j.agentic.Agent
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V

interface PersonAgent {
    @UserMessage("""
        Use the find tool to search for persons matching the given name.
        Use {{firstName}} for firstName and {{lastName}} for lastName
        Use page=0 and size=10 for pagination.
        Return the results as a readable list.
    """)
    @Agent(outputKey = "persons", description = "Finds persons by name via MCP tool")
    fun findPerson(@V("firstName") firstName: String, @V("lastName") lastName: String?): String
}