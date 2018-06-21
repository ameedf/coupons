package com.ameed.coupons.service;

import com.ameed.coupons.dao.CompanyDao;
import com.ameed.coupons.dao.CouponDao;
import com.ameed.coupons.dao.CustomerDao;
import com.ameed.coupons.entities.Company;
import com.ameed.coupons.entities.Coupon;
import com.ameed.coupons.entities.CouponType;
import com.ameed.coupons.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * Created by ameed_fah
 * 21/06/2018
 */
@Service
public class TestService {
    private final CustomerDao customerDao;
    private final CouponDao couponDao;
    private final CompanyDao companyDao;

    private Random random = new Random();

    @Autowired
    public TestService(CustomerDao customerDao, CouponDao couponDao, CompanyDao companyDao) {
        this.customerDao = customerDao;
        this.couponDao = couponDao;
        this.companyDao = companyDao;
    }

    public Customer testCustomer(int couponsNumber) {
        Set<Coupon> coupons = getRandomCoupons(couponsNumber);
        Customer customer = createCustomer(null);
        customer.getCoupons().addAll(coupons);
        customerDao.save(customer);
        final Optional<Customer> customerFromDB = customerDao.findById(customer.getId());
        return customerFromDB.get();
    }

    public Company testCompany(int couponsNumber) {
        Set<Coupon> coupons = getRandomCoupons(couponsNumber);
        Company company = createCompany(null);
        company.getCoupons().addAll(coupons);
        companyDao.save(company);
        final Optional<Company> companyFromDB = companyDao.findById(company.getId());
        return companyFromDB.get();
    }

    private Set<Coupon> getRandomCoupons(int couponsNumber) {
        final List<Coupon> all = couponDao.findAll();
        Set<Integer> numbers = new HashSet<>(couponsNumber);
        while (numbers.size() < couponsNumber) {
            numbers.add(random.nextInt(all.size()));
        }
        Set<Coupon> coupons = new HashSet<>(couponsNumber);
        numbers.forEach(i -> coupons.add(all.get(i)));
        return coupons;
    }

    private Company createCompany(Coupon coupon) {
        final Company company = new Company();
        company.setName(createString("company"));
        company.setPassword(createPassword());
        if (coupon != null) {
            company.getCoupons().add(coupon);
        }
        return company;
    }

    private Customer createCustomer(Coupon coupon) {
        Customer customer = new Customer();
        customer.setName(createString("customer"));
        customer.setPassword(createPassword());
        if (coupon != null) {
            customer.getCoupons().add(coupon);
        }
        return customer;
    }

    private Coupon createCoupon() {
        final Coupon coupon = new Coupon();
        coupon.setStartDate(LocalDate.now().minusDays(random.nextInt(100)));
        coupon.setEndDate(LocalDate.now().plusDays(random.nextInt(100)));
        coupon.setTitle(createString("title"));
        final CouponType[] values = CouponType.values();
        coupon.setCouponType(values[random.nextInt(values.length)]);
        return coupon;
    }

    private String createPassword() {
        return String.valueOf(random.nextInt(1000000) + 10000);
    }

    private String createString(String prefix) {
        return prefix + "-" + random.nextInt(100000);
    }

    public Set<Coupon> generateCoupons() {
        for (int i = 0; i < 100; i++) {
            couponDao.save(createCoupon());
        }
        return new HashSet<>(couponDao.findAll());
    }
}
