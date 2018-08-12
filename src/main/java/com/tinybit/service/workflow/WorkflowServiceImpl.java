package com.tinybit.service.workflow;

import com.tinybit.constants.Constants;
import com.tinybit.enums.ErrorEnum;
import com.tinybit.exception.TinyBitException;
import com.tinybit.model.Detail;
import com.tinybit.service.db.DbService;
import com.tinybit.service.hash.HashService;
import com.tinybit.service.hash.HashServiceImpl;
import com.tinybit.service.validation.ValidationService;
import com.tinybit.service.validation.ValidationServiceImpl;
import com.tinybit.util.TransformerUtil;

import java.util.concurrent.locks.ReentrantLock;

public class WorkflowServiceImpl implements WorkflowService {

    private DbService dbService;
    private HashService hashService;
    private ValidationService validationService;

    public WorkflowServiceImpl(DbService dbService) {
        this.dbService = dbService;
        this.hashService = new HashServiceImpl();
        this.validationService = new ValidationServiceImpl();
    }

    @Override
    public String createRecord(Detail detail) throws Exception {

        ReentrantLock lock = new ReentrantLock();

        if (detail == null)
            throw new TinyBitException(ErrorEnum.EMPTY_DETAIL_REQUEST);

        if (!validationService.isValid(detail))
            throw new TinyBitException(ErrorEnum.INVALID_DETAIL_REQUEST);

        String key = null;
        // critical section
        lock.lock();

        try {

            String value = TransformerUtil.txRecord(detail);
            key = hashService.generateKey(value);

            // generate unique key in case of collision
            while (dbService.exists(key)) {
                key = hashService.generateKey(value);
            }

            String result = dbService.createRecord(key, value);

            if (!result.equals(Constants.JEDIS_OK))
                throw new TinyBitException(ErrorEnum.COULD_NOT_CREATE_RECORD);

            // incr counter
            dbService.increment();
        } catch (Exception e) {
            throw e;
        } finally {
            lock.unlock();
        }
        // end critical section

        return key;
    }

    @Override
    public Detail getRecord(String key) throws TinyBitException {

        Detail detail = null;
        try {
            detail = dbService.getRecord(key);

            if(detail == null)
                throw new TinyBitException(ErrorEnum.INVALID_KEY);

            // incr counter
            dbService.increment();
        } catch (Exception e) {
            e.printStackTrace();
            throw new TinyBitException(ErrorEnum.INVALID_KEY);
        }
        return detail;
    }
}
