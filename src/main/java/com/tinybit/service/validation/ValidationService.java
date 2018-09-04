package com.tinybit.service.validation;

import com.tinybit.exception.TinyBitException;
import com.tinybit.model.Detail;

public interface ValidationService {

    boolean isValid(Detail detail) throws TinyBitException;
}
