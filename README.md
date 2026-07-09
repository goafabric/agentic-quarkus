# description

Small program that shows how to do simple agentic workflows with Quarkus' Langchain4j abstraction.
Integrates and calls out to multiple MCP "servers" (https://github.com/goafabric/callee-service-quarkus, https://github.com/goafabric/person-service-quarkus, https://github.com/goafabric/observation-service-quarkus)
All configuration inside application.proprties

Can ran in two modes configured via "agentic.mode"
- "assistant" => simple prompt that integrates the MCP directly
- "agentic" => orchestration via a SuperVisorAgent, yet the results will be similar

# prerequisites

- provide an anthropic (aka claude) model via environment variables found in application.properties
- or use a local model via ollama and openai properties
- start required containers inside src/deploy/docker => stack up

# chat
- start the application locally inside your IDE and open port 50500, or use the container one on port 50600
- inside the chat try "say Homer" "find Homer" "observe Homer"                                                                                                              

# code
- connected mcp "servers" are auto configured via application.properties
- Asistant mode lives solely inside package "Assistant" => One class with a simple propmpt and MCP connection via "MCPToolBox"
- Agentic mode lives inside package "agents" => One Subagent per Usecase, orchestrated by the SuperVisoragent, everything just Annotations


# apple container run
container image pull goafabric/agentic-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)
"${(@z)${CRUNTIME:-docker run --pull always}}" --name agentic-quarkus --rm \
-e ANTHROPIC_FOUNDRY_API_KEY -e ANTHROPIC_FOUNDRY_RESOURCE -e ANTHROPIC_DEFAULT_SONNET_MODEL \
-e quarkus.langchain4j.mcp.callee.url=http://203.0.113.113:50900/mcp -e quarkus.langchain4j.mcp.person.url=http://203.0.113.113:50800/mcp \
goafabric/agentic-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)