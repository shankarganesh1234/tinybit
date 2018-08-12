package com.tinybit.service.hash;

import org.junit.Assert;
import org.junit.Test;

public class HashServiceTest {

    private HashService hashService = new HashServiceImpl();

    @Test
    public void generateKeyNullTest() {
        try {
            Assert.assertNull(hashService.generateKey(null));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void generateKeyTest() {
        try {
            String key = hashService.generateKey("testValue");
            Assert.assertNotNull(key);
            Assert.assertEquals(6, key.length());
            System.out.println(key);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
