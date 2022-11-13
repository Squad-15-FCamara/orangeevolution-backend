package com.orange_evolution_backend.exception;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.orange_evolution_backend.commons.Utils;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    @Value("${enable.trace:#{false}}")
    private boolean enableTracer;


    private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest webRequest){
        return buildErrorResponse(exception,exception.getMessage(), httpStatus, webRequest);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception exception,String message, HttpStatus httpStatus, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);

        if(enableTracer)
            errorResponse.setStackStrace(Utils.getStackTracer(exception));
        return ResponseEntity.status(httpStatus).body(errorResponse);

    }
}
