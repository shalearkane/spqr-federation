package com.chegg.federation.review.query;

import com.chegg.federation.review.model.Product;
import com.chegg.federation.review.model.Review;
import com.chegg.federation.review.model.User;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import org.springframework.stereotype.Component;

@Component
public class ReviewQuery {

    @GraphQLQuery(name = "trivia")
    public Review trivia(@GraphQLArgument(name = "id") String id) {
        return new Review("1","Love it!", new User("1"), new Product("1"));
    }

    @GraphQLQuery(name = "newDummy")
    public String getNewDummy(@GraphQLContext Product dummy) {
        System.out.println(dummy.getUpc());
        return dummy.getUpc();
    }
}
