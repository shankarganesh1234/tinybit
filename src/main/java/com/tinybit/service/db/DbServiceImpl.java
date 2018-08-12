package com.tinybit.service.db;

import com.tinybit.constants.Constants;
import com.tinybit.model.Detail;
import com.tinybit.util.TransformerUtil;
import redis.clients.jedis.Jedis;

public class DbServiceImpl implements DbService {

    private Jedis jedis = null;

    public DbServiceImpl(Jedis jedis) {
        this.jedis = jedis;
    }

    /**
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return jedis.exists(key);
    }

    /**
     *
     * @param key
     * @return
     */
    public Detail getRecord(String key) throws Exception {

        if(!exists(key))
            return null;

        String value = jedis.get(key);

        Detail details = null;

        try {
            details = TransformerUtil.txDetails(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return details;
    }


    /**
     *
     * @param key
     * @param value
     * @throws Exception
     */
    public String createRecord(String key, String value) {

        if(value == null || key == null)
            return Constants.JEDIS_ERROR;
        return jedis.set(key, value);
    }
}
