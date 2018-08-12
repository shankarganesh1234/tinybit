package com.tinybit.service.workflow;

import com.tinybit.exception.TinyBitException;
import com.tinybit.model.Detail;

public interface WorkflowService {

    String createRecord(Detail detail) throws Exception;

    Detail getRecord(String key) throws TinyBitException;
}
