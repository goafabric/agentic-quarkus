# run native image
container stop calle-service-quarkus ; container run --name calle-service-quarkus --rm -p 50900:50900 goafabric/callee-service-quarkus:3.35.4

# description

- can run agentic codes in 2 flavours
  - assistant with ai services, that simply plugs in callee and person mcp (https://github.com/goafabric/callee-service-quarkus, https://github.com/goafabric/person-service-quarkus)
  - agents with agentic services, which achieves the same by fanning out to multiple agents
  - can by set by "agentic.mode" inside application.properties

- model and mcp client configuration is entirely inside application.properties, leveraging quarkus autoconfig for langchain4j
- agents and assistant also follow a declarative approach, by ditching manual builder code with annotations, examples can be find in doc foler

# links
https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html

https://github.com/goafabric/agentic-quarkus/blob/main/src/main/kotlin/org/goafabric/agentic/assistant/Assistant.kt
https://github.com/goafabric/agentic-quarkus/tree/main/src/main/kotlin/org/goafabric/agentic/agents
https://github.com/goafabric/agentic-quarkus/blob/main/src/main/resources/application.properties
       
# container

container image pull goafabric/agentic-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)
"${(@z)${CRUNTIME:-docker run --pull always}}" --name agentic-quarkus --rm \
-e ANTHROPIC_FOUNDRY_API_KEY -e ANTHROPIC_FOUNDRY_RESOURCE -e ANTHROPIC_DEFAULT_SONNET_MODEL \
-e quarkus.langchain4j.mcp.callee.url=http://203.0.113.113:50900/mcp -e quarkus.langchain4j.mcp.person.url=http://203.0.113.113:50800/mcp \
goafabric/agentic-quarkus:$(grep '^version=' gradle.properties | cut -d'=' -f2)