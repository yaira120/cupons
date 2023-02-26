package cupon_project.facade;

import cupon_project.beans.Category;
import cupon_project.beans.Coupon;
import cupon_project.beans.Customer;
import cupon_project.dbdao.CouponDbdao;
import cupon_project.dbdao.CustomerDbdao;
import cupon_project.exceptions.CouponException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerFacade extends ClientFacade {
   private Customer customer;
   private CouponDbdao couponDbdao=new CouponDbdao();
   CustomerDbdao customerDbdao=new CustomerDbdao();
    private int id;

    @Override
    /*

     */

    public boolean login(String email, String password) throws SQLException, InterruptedException {
        if (customerDbdao.isCustomerExists(email, password)) {
           id=customerDbdao.getIdCustomerByEmail(email);
            System.out.println("The Login was successful");
            return true;
        }
        return false;
    }

    ///לשאול את זא
    /*
    public void addCoupon(int coupon_id) throws Exception {
        Date date = new Date();
        if (customer.getCoupons().contains(couponDbdao.getOneCoupon(coupon_id)) ||
                    (couponDbdao.getOneCoupon(coupon_id).getAmount() == 0) ||
                         (couponDbdao.getOneCoupon(coupon_id).getEndDate().after(date)))
        {
            throw new Exception("Error purchase");
        }

        couponDbdao.addCouponPurchase(id,coupon_id);
    }

     */




///לשאול את זא

    public void addCoupon(int coupon_id) throws Exception {

        Date date = new Date();

       if (customerDbdao.checkCustomerVsCouponsExists(id,coupon_id)){
           throw new CouponException("This coupon already exists, please select another coupon");
       }
      if (customerDbdao.getAmountOfCoupons(coupon_id)<0){
           throw new CouponException("Coupon sold out");
       }
       if (customerDbdao.checkEndDateOfCoupon(coupon_id).before(date)){
           throw new CouponException("Expired coupon");
       }


        couponDbdao.addCouponPurchase(id,coupon_id);




    }





    public List<Coupon> getAllCouponsCustomer() throws SQLException, InterruptedException {
        Customer customer1=customerDbdao.getOneCustomer(id);
        System.out.println(customer1.getCoupons());

        return null;
    }



    public void getCouponsCustomerByCategory( Category category) throws SQLException, InterruptedException {
        List<Coupon>couponList= getAllCouponsCustomer();
        for (Coupon item:couponList){
            if (item.getCategory().equals(category)){
                System.out.println(item);
            }
        }
    }


    public void getCustomerDetails() throws SQLException, InterruptedException {
        System.out.println(customerDbdao.getOneCustomer(id));
    }






}
