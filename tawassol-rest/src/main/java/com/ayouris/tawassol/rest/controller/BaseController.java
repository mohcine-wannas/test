package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.reporting.helper.BuildReport;
import com.ayouris.tawassol.rest.exception.ErrorResponse;
import com.ayouris.tawassol.rest.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public abstract class BaseController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    protected BuildReport buildReport;

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> exceptionHandler(HttpServletRequest request, Exception e) throws IOException {
        GlobalException globalException = new GlobalException(e, messageSource);

        ErrorResponse errorResponse = new ErrorResponse(globalException.getStatus(), e, globalException.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorResponse, globalException.getStatus());
    }

}