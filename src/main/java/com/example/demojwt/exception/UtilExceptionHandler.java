package com.example.demojwt.exception;

import com.example.demojwt.Utils.ErrorResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class UtilExceptionHandler {

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseWrapper> exception(UsernameNotFoundException e) {
        ErrorResponseWrapper wrapper = new ErrorResponseWrapper();
        wrapper.statusCode = HttpStatus.NOT_FOUND.value();
        wrapper.message = e.getMessage();
        wrapper.setTimestamp(Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorResponseWrapper> exception(NullPointerException e) {
        ErrorResponseWrapper wrapper = new ErrorResponseWrapper();
        wrapper.statusCode = HttpStatus.BAD_REQUEST.value();
        wrapper.message = e.getStackTrace();
        wrapper.setTimestamp(Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    @ExceptionHandler(value = LockedException.class)
    public ResponseEntity<ErrorResponseWrapper> exception(LockedException e) {
        ErrorResponseWrapper error = new ErrorResponseWrapper();
        error.statusCode = HttpStatus.FORBIDDEN.value();
        error.message = e.getMessage();
        error.setTimestamp(Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<>(error,HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseWrapper> exception(Exception e) {
        ErrorResponseWrapper wrapper = new ErrorResponseWrapper();
        wrapper.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        wrapper.message = e.getMessage();
        wrapper.setTimestamp(Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseWrapper> exception(MethodArgumentNotValidException e) {
        Map<String,Object> errors = new HashMap<>();
        for (FieldError error: e.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(),error.getDefaultMessage());
        }
        for (ObjectError error: e.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(),error.getDefaultMessage());
        }
        ErrorResponseWrapper wrapper = new ErrorResponseWrapper();
        wrapper.statusCode = HttpStatus.BAD_REQUEST.value();
        wrapper.message = errors;
        wrapper.setTimestamp(Calendar.getInstance().getTimeInMillis());

        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

}
