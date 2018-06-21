package com.ameed.coupons.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ameed_fah
 * 21/06/2018
 */
@ResponseStatus(value = HttpStatus.OK)
public class TestException extends RuntimeException {
    public TestException() {
        super("Dummy exception");
    }
}
