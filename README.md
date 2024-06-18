# GraphQL Federation
#### graphql-spqr + federation-jvm + graphql-java-kickstart + Apollo Router
This simple project demonstrates how to integrate SPQR, Apollo Federation and GraphQL Java Kickstart.
This also shows how to generate a `supergraph.graphql` and serve it with a custom Apollo Router with a plugin.

# How to Run
1. Use **Gradle 8.5** and **JDK 17** to build and run the project.
2. Make sure the `product`, `review` and `user` services are running on ports `5001`, `5002` and `5003` respectively.
3. Then run either Apollo Router or Gateway

## Apollo Router
### Using pre-compiled binary
```shell
cd apollo-graphql-gateway/router/
make serve
```

### Using a custom router with plugin
#### Generate supergraph using `Rover`
```shell
curl -sSL https://rover.apollo.dev/nix/latest | sh
rover supergraph compose --output ./supergraph.graphql --config ./supergraph-config.yaml
```
#### Compile the router and run with the supergraph file
Make sure you have **Rust 1.76** installed
```shell
git clone https://github.com/shalearkane/router-plugins
cd router-plugins
cargo run -- --hot-reload --supergraph supergraph.graphql --config router.yaml
```

## Apollo Gateway
```shell
cd apollo-graphql-gateway/gateway
npm i 
npm run start-gateway
```
## How to run the Router Dockerfile
`docker build --tag router -f apollo-router.dockerfile . && docker run -p 4000:4000 router`
