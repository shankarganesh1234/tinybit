package com.tinybit.service.core;

import com.tinybit.enums.ErrorEnum;
import com.tinybit.exception.TinyBitException;
import com.tinybit.model.Error;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CoreExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        if(e instanceof TinyBitException) {

            TinyBitException t = (TinyBitException) e;
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(getError(t.getErrorEnum()))
                    .build();

        } else {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(getError(null))
                    .build();
        }
    }

    /**
     *
     * @param e
     * @return
     */
    private Error getError(ErrorEnum e) {

        Error error = null;

        if(e == null)
         error = new Error(ErrorEnum.INTERNAL_ERROR.getCode(), ErrorEnum.INTERNAL_ERROR.getMessage());
        else
            error = new Error(e.getCode(), e.getMessage());

        return error;
    }
}
