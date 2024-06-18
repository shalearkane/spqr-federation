const { ApolloServer } = require("@apollo/server");
const { startStandaloneServer } = require("@apollo/server/standalone");
const { ApolloGateway, RemoteGraphQLDataSource } = require("@apollo/gateway");

const gateway = new ApolloGateway({
    serviceList: [
        // This entire `serviceList` is optional when running in managed federation
        // mode, using Apollo Graph Manager as the source of truth.  In production,
        // using a single source of truth to compose a schema is recommended and
        // prevents composition failures at runtime using schema validation using
        // real usage-based metrics.
        { name: "product", url: "http://localhost:5001/graphql" },
        { name: "review", url: "http://localhost:5002/graphql" },
        { name: "user", url: "http://localhost:5003/graphql" },
    ],
    // Experimental: Enabling this enables the query plan view in Playground.
});


(async () => {
    const server = new ApolloServer({
        gateway,

        // Apollo Graph Manager (previously known as Apollo Engine)
        // When enabled and an `ENGINE_API_KEY` is set in the environment,
        // provides metrics, schema management and trace reporting.
        engine: false,

        // Subscriptions are unsupported but planned for a future Gateway version.
        subscriptions: false,
    });

    const { url } = await startStandaloneServer(server);
    console.log(`🚀  Server ready at ${url}`);
})();
