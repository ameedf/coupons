package com.ameed.coupons.entities;

/**
 * Created by ameed_fah
 * 21/06/2018
 */
public enum CouponType {
    RESTAURANT("rest"),
    ELECTRICITY("elec"),
    FOOD("food");

    private final String code;

    CouponType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
