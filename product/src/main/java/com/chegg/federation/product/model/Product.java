package com.chegg.federation.product.model;

import directives.FederationKeyDirective;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.Objects;

@FederationKeyDirective(fields = "upc")
public class Product {
    private String upc;
    private String name;
    private Integer price;
    private String artist;
    private String altArtist;
    private String album;
    private String track;
    private String location;
    private String studio;

    public Product() {
    }

    public Product(String upc, String name, Integer price, String artist, String altArtist, String album, String track, String location, String studio) {
        this.upc = upc;
        this.name = name;
        this.price = price;
        this.artist = artist;
        this.altArtist = altArtist;
        this.album = album;
        this.track = track;
        this.location = location;
        this.studio = studio;
    }

    public static Product lookup() {
        return new Product("vand", "aliz", 1, "noth", "ing", "in", "it", ".", "!");
    }

    @GraphQLQuery(name = "upc")
    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    @GraphQLQuery(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @GraphQLQuery(name = "price")
    public Integer getPrice() {
        return price;
    }

    @GraphQLQuery(name = "artist")
    public String getArtist() {
        return artist;
    }

    @GraphQLQuery(name = "altArtist")
    public String getAltArtist() {
        return altArtist;
    }

    @GraphQLQuery(name = "album")
    public String getAlbum() {
        return album;
    }

    @GraphQLQuery(name = "track")
    public String getTrack() {
        return track;
    }

    @GraphQLQuery(name = "location")
    public String getLocation() {
        return location;
    }

    @GraphQLQuery(name = "studio")
    public String getStudio() {
        return studio;
    }



    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(upc, product.upc);

    }

    @Override
    public int hashCode() {
        return upc != null ? upc.hashCode() : 0;
    }
}
