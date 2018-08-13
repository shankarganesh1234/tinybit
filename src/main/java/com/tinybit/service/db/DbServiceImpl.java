package com.tinybit.service.db;

import com.tinybit.constants.Constants;
import com.tinybit.model.Detail;
import com.tinybit.util.TransformerUtil;
import redis.clients.jedis.Jedis;

public class DbServiceImpl implements DbService {

    private Jedis jedis = null;

    public DbServiceImpl() {
        this.jedis = new Jedis();
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

    @Override
    public String getRecordString(String key) {
        return jedis.get(key);
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

    /**
     *
     */
    public Long increment() {
        return jedis.incr(Constants.INCR_KEY);
    }

    @Override
    public Long getCount() {

        String count = jedis.get(Constants.INCR_KEY);

        if(count == null)
            return 0L;

        Long currentCount = 0L;
        try {
            currentCount = Long.valueOf(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentCount;
    }


}
