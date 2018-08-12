package com.tinybit.model;

public class Counter {

    private Long currentCount;

    public Counter() {

    }

    public Counter(Long currentCount) {
        this.currentCount = currentCount;
    }

    public Long getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Long currentCount) {
        this.currentCount = currentCount;
    }
}
