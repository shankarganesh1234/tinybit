package com.tinybit.model;

public class KeyDetail {
    private String key;

    public KeyDetail() {

    }

    public KeyDetail(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "KeyDetail{" +
                "key='" + key + '\'' +
                '}';
    }
}
