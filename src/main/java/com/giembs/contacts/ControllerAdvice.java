package com.giembs.contacts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by Wilson
 * on Thu, 19/08/2021.
 */
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({Exception.class,RuntimeException.class})
    public ResponseEntity<APIResponseJSON<String>> exceptionHandler(Exception exception){
        APIResponseJSON<String> responseJSON = new APIResponseJSON<>("Failed: "+exception.getMessage());
        return new ResponseEntity<>(responseJSON, HttpStatus.BAD_REQUEST);
    }

}
