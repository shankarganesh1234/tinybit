package com.tinybit.model;

public class CryptoDetail {

    private String id;
    private String name;
    private String symbol;
    private String imageUrl;

    public CryptoDetail() {
        this.name = "UNKNOWN";
        this.symbol = "UNKNOWN";
        this.imageUrl = "";
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
