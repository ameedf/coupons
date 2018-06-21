package com.ameed.coupons.web;

import com.ameed.coupons.entities.Company;
import com.ameed.coupons.entities.Coupon;
import com.ameed.coupons.entities.Customer;
import com.ameed.coupons.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by ameed_fah
 * 21/06/2018
 */
@RestController
public class CouponsController {
    private final TestService testService;

    @Autowired
    public CouponsController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/test-generate-coupons")
    public Set<Coupon> generateCoupons() {
        return testService.generateCoupons();
    }

    @GetMapping("/test-company")
    public Company testCompany() {
        return testService.testCompany(3);
    }

    @GetMapping("/test-customer")
    public Customer testCustomer() {
        return testService.testCustomer(5);
    }
}
