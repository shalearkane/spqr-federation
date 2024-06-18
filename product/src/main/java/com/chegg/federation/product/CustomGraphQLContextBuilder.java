package com.chegg.federation.product;

import com.chegg.federation.product.model.Product;
import com.chegg.federation.product.repository.ProductRepository;
import graphql.kickstart.execution.context.DefaultGraphQLContext;
import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.servlet.context.DefaultGraphQLWebSocketContext;
import graphql.kickstart.servlet.context.GraphQLServletContextBuilder;
import java.util.concurrent.CompletableFuture;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.Session;
import jakarta.websocket.server.HandshakeRequest;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomGraphQLContextBuilder implements GraphQLServletContextBuilder {

    private final ProductRepository productRepository;

    public CustomGraphQLContextBuilder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public DefaultGraphQLContext build(HttpServletRequest req, HttpServletResponse response) {
        return DefaultGraphQLServletContext.createServletContext(buildDataLoaderRegistry())
                .with(req).with(response)
                .build();
    }

    @Override
    public DefaultGraphQLContext build() {
        return new DefaultGraphQLContext(buildDataLoaderRegistry(), null);
    }

    @Override
    public DefaultGraphQLContext build(Session session, HandshakeRequest request) {
        return DefaultGraphQLWebSocketContext.createWebSocketContext(buildDataLoaderRegistry())
                .with(session)
                .with(request).build();
    }

    private DataLoaderRegistry buildDataLoaderRegistry() {
        DataLoaderRegistry dataLoaderRegistry = new DataLoaderRegistry();
        dataLoaderRegistry.register("productDataLoader",
                new DataLoader<String, Product>(productIds ->
                        CompletableFuture.supplyAsync(() ->
                                productRepository.getProductForIds(productIds))));
        return dataLoaderRegistry;
    }
}