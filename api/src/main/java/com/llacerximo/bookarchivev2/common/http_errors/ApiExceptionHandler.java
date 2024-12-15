package com.llacerximo.bookarchivev2.common.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.llacerximo.bookarchivev2.common.exception.ResourceNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    @ResponseBody
    public ErrorMessage notFoundRequest(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            HttpMessageNotReadableException.class
    })
    @ResponseBody
    public ErrorMessage httpMessageNotReadable(Exception exception) {
        return new ErrorMessage(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({
            Exception.class
    })
    @ResponseBody
    public ErrorMessage exception(Exception exception) {
        exception.printStackTrace();
        return new ErrorMessage("Internal error", HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

}
