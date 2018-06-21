package com.ameed.coupons.dao;

import com.ameed.coupons.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ameed_fah
 * 21/06/2018
 */
public interface CouponDao extends JpaRepository<Coupon, Long> {

}
