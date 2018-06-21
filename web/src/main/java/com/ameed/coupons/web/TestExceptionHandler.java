package com.ameed.coupons.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by ameed_fah
 * 21/06/2018
 */
@ControllerAdvice
public class TestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        return ResponseEntity.ok("ERROR: " + e.getMessage());
    }
}
