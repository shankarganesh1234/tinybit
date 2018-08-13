package com.tinybit.constants;

public class Constants {

    // hash related
    public static final Integer KEY_LENGTH = 6;

    // startup related
    public static final String RESOURCES_PACKAGE = "com.tinybit.service.core";

    // jedis related
    public static final String JEDIS_OK = "OK";
    public static final String JEDIS_ERROR = "Exception";
    public static final String INCR_KEY = "counter";

    // cc related
    public static final String CC_URL = "https://min-api.cryptocompare.com/data/all/coinlist";
    public static final String CC_BASE_URL = "https://www.cryptocompare.com";
    public static final String BTC_IMG = "/media/19633/btc.png";
    public static final String CC_KEY = "cc";

    // scheduling related
    public static final String TRIGGER_KEY = "cc_job";
}
