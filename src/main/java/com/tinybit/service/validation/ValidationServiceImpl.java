package com.tinybit.service.validation;

import com.tinybit.enums.ErrorEnum;
import com.tinybit.exception.TinyBitException;
import com.tinybit.model.Detail;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isValid(Detail detail) throws TinyBitException {

        if(detail == null)
            throw new TinyBitException(ErrorEnum.EMPTY_DETAIL_REQUEST);

        if(detail.getName() == null || detail.getName().equals(""))
            throw new TinyBitException(ErrorEnum.EMPTY_NAME);

        if(detail.getCoinDetails() == null || detail.getCoinDetails().isEmpty())
            throw new TinyBitException(ErrorEnum.EMPTY_COIN_DETAILS);

        if(detail.getCoinDetails().size() > 5)
            throw new TinyBitException(ErrorEnum.MAX_LENGTH_EXCEEDED);

        return true;
    }
}
