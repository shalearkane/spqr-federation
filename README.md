# SPQR + Kickstart + Federation
This simple project demonstrates how to integrate SPQR, Apollo Federation and GraphQL Java Kickstart

# How to Run
1. Use Gradle 8.5 and JDK 17 to build and run the project.
2. Make sure the `product`, `review` and `user` services are running on ports `5001`, `5002` and `5003` respectively.
3. Then run either Apollo Router or Gateway

## Apollo Router
```shell
cd apollo-graphql-gateway/router/
make serve
```

## Apollo Gateway
```shell
cd apollo-graphql-gateway/gateway
npm i 
npm run start-gateway
```
## How to run the Router Dockerfile
`docker build --tag router -f apollo-router.dockerfile . && docker run -p 4000:4000 router`
