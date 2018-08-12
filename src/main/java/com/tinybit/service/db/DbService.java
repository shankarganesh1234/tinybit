package com.tinybit.service.db;

import com.tinybit.model.Detail;

public interface DbService {

    boolean exists(String key);
    Detail getRecord(String key) throws Exception;
    String createRecord(String key, String value) throws Exception;
    Long increment();
    Long getCount();
}
