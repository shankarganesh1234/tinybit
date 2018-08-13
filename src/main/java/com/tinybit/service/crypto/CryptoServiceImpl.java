package com.tinybit.service.crypto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinybit.constants.Constants;
import com.tinybit.model.CryptoDetail;
import com.tinybit.service.db.DbService;
import com.tinybit.service.db.DbServiceImpl;
import com.tinybit.util.ObjectMapperUtil;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@DisallowConcurrentExecution
public class CryptoServiceImpl implements CryptoService, Job {

    // init db service
    DbService dbService = new DbServiceImpl();

    OkHttpClient client = new OkHttpClient();

    @Override
    public void load() {

        Request request = new Request.Builder()
                .url(Constants.CC_URL)
                .build();

        List<CryptoDetail> cryptoDetails = new ArrayList<>();

        // call cc and parse response
        try(Response response = client.newCall(request).execute()) {

            if(!response.isSuccessful())
                return;

            String responseStr = response.body().string();
            JSONObject obj = new JSONObject(responseStr);

            // parse obj
            JSONObject data = obj.getJSONObject("Data");

            Iterator<String> keys = data.keys();

            while(keys.hasNext()) {

                JSONObject o = data.getJSONObject(keys.next());

                if(o == null)
                    continue;

                CryptoDetail cryptoDetail = new CryptoDetail();
                cryptoDetail.setSymbol(o.optString("Symbol", "UNKNOWN"));
                cryptoDetail.setName(o.optString("CoinName", "UNKNOWN"));
                cryptoDetail.setImageUrl(Constants.CC_BASE_URL + o.optString("ImageUrl", Constants.BTC_IMG));
                cryptoDetails.add(cryptoDetail);
            }

            // update redis
            if(cryptoDetails == null || cryptoDetails.isEmpty())
                return;

            ObjectMapper mapper = ObjectMapperUtil.getObjectMapper();
            dbService.createRecord(Constants.CC_KEY, mapper.writeValueAsString(cryptoDetails));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CryptoDetail> get() {

        List<CryptoDetail> cryptoDetails = null;

        try {
            ObjectMapper mapper = ObjectMapperUtil.getObjectMapper();
            String ccDetails = dbService.getRecordString(Constants.CC_KEY);
            cryptoDetails = mapper.readValue(ccDetails, new TypeReference<List<CryptoDetail>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cryptoDetails;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        load();
    }
}
