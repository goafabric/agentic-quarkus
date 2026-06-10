# docker compose
go to /src/deploy/docker and do "./stack up"

# run native image
container stop calle-service-quarkus ; container run --name calle-service-quarkus --rm -p 50900:50900 goafabric/callee-service-quarkus:3.35.4




--name calle-service-quarkus --rm -p 50900:50900 goafabric/callee-service-quarkus:3.35.4