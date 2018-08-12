package com.tinybit.service.db;

import com.tinybit.model.CoinDetail;
import com.tinybit.model.Detail;
import com.tinybit.util.TransformerUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class DbServiceTest {


    private static final String NON_EXISTENT_KEY = "nonExistentKey";
    private static final String KEY = "xxx";

    Jedis jedis = new Jedis();
    private DbService dbService = new DbServiceImpl(jedis);

    @Test
    public void existsInvalidTest() {
        Assert.assertFalse(dbService.exists(NON_EXISTENT_KEY));
    }

    @Test
    public void createRecordtest() {

        Detail details = new Detail("name", "company", getMockCoinDetails());
        try {
            dbService.createRecord(KEY, TransformerUtil.txRecord(details));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void getRecordTest() {

        try {
            Detail detail = dbService.getRecord(KEY);
            Assert.assertNotNull(detail);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    private List<CoinDetail> getMockCoinDetails() {

        List<CoinDetail> coinDetails = new ArrayList<>();
        CoinDetail coinDetail = new CoinDetail("name", "symbol", "image", "alias", "123456");
        coinDetails.add(coinDetail);
        return coinDetails;
    }

    @After
    public void tearDown() {
        //jedis.del(KEY);
    }
}
