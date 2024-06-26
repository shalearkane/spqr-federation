package com.chegg.federation.review.model;

import directives.FederationExtendsDirective;
import directives.FederationExternalDirective;
import directives.FederationKeyDirective;

import java.util.List;

@FederationKeyDirective(fields = "upc")
@FederationExtendsDirective
public class Product {

    @FederationExternalDirective
    private String upc;
    private List<Review> reviews;

    public Product(final String upc, final List<Review> reviews) {
        this.upc = upc;
        this.reviews = reviews;
    }

    public Product(final String upc) {
        this.upc = upc;
    }

    public Product() {
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(final String upc) {
        this.upc = upc;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(final List<Review> reviews) {
        this.reviews = reviews;
    }
}