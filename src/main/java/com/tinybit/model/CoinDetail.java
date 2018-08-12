package com.tinybit.model;

public class CoinDetail {

    private String name;
    private String symbol;
    private String imageUrl;
    private String alias;
    private String walletAddress;

    public CoinDetail() {

    }

    public CoinDetail(String name, String symbol, String imageUrl, String alias, String walletAddress) {
        this.name = name;
        this.symbol = symbol;
        this.imageUrl = imageUrl;
        this.alias = alias;
        this.walletAddress = walletAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWalletAddress() {
        return walletAddress;
    }

    public void setWalletAddress(String walletAddress) {
        this.walletAddress = walletAddress;
    }


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
