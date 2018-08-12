package com.tinybit.model;

import java.util.List;

public class Detail {

    private String name;
    private String company;
    private List<CoinDetail> coinDetails;
    private String key;

    public Detail() {

    }

    public Detail(String name, String company, List<CoinDetail> coinDetails) {
        this.name = name;
        this.company = company;
        this.coinDetails = coinDetails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public List<CoinDetail> getCoinDetails() {
        return coinDetails;
    }

    public void setCoinDetails(List<CoinDetail> coinDetails) {
        this.coinDetails = coinDetails;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
