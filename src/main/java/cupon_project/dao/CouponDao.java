package cupon_project.dao;

import cupon_project.beans.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface CouponDao {
    void addCoupon(Coupon coupon) throws SQLException, InterruptedException;

    void updateCoupon(Coupon coupon) throws InterruptedException;

    void deleteCoupon(int couponID);

    List<Coupon> getAllCoupon() throws SQLException, InterruptedException;

    Coupon getOneCoupon(int couponID) throws InterruptedException, SQLException;

    void addCouponPurchase(int customerID, int couponId) throws SQLException, InterruptedException;

    void deleteCouponPurchase(int customerID, int couponId) throws SQLException, InterruptedException;


}
