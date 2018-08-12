package com.tinybit.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinybit.model.Detail;

import java.io.IOException;

public class TransformerUtil {

    /**
     *
     * @param value
     * @return
     * @throws IOException
     */
    public static Detail txDetails(String value) throws IOException {

        if(value == null)
            return null;

        ObjectMapper mapper = ObjectMapperUtil.getObjectMapper();
        Detail details  = mapper.readValue(value, Detail.class);
        return details;
    }

    /**
     *
     * @param details
     * @return
     * @throws JsonProcessingException
     */
    public static String txRecord(Detail details) throws JsonProcessingException {

        ObjectMapper mapper = ObjectMapperUtil.getObjectMapper();
        return mapper.writeValueAsString(details);

    }
}
