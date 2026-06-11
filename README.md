# run native image
container stop calle-service-quarkus ; container run --name calle-service-quarkus --rm -p 50900:50900 goafabric/callee-service-quarkus:3.35.4

# description

- can run agentic codes in 2 flavours
  - assistant with ai services, that simply plugs in callee and person mcp
  - agents with agentic services, which achieves the same by faning out to multiple agents
- model and mcp client configuration is entirely inside application.properties, leveraging quarkus autoconfig for langchain4j
- agents and assistant also follow a declarative approach, by ditching manual builder code with annotations, examples can be find in doc foler



