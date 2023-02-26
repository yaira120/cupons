package cupon_project.testers;

import cupon_project.beans.Coupon;
import cupon_project.beans.Customer;
import cupon_project.dbdao.CompanyDbdao;
import cupon_project.dbdao.CustomerDbdao;
import cupon_project.exceptions.CouponException;
import cupon_project.exceptions.CustomerLoginExeption;
import cupon_project.facade.AdminFacade;
import cupon_project.facade.CustomerFacade;
import cupon_project.login.ClientType;
import cupon_project.login.LoginManager;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerTester {
    public static void main(String[] args) throws Exception {

        //Trying to log in with a client that does not exist in the system
        // try{
        // CustomerFacade dolev=(CustomerFacade) LoginManager.getInstance().login("ddolevto@gmail.com", "909090", ClientType.CUSTOMER);
        // }catch (CustomerLoginExeption err){
        //  System.out.println(err.getMessage());
        //  }


        //Trying to log in with a client that  exist in the system
        //CustomerFacade dolev = (CustomerFacade) LoginManager.getInstance().login("dolevto@gmail.com", "909090", ClientType.CUSTOMER);
        CustomerFacade dvir = (CustomerFacade) LoginManager.getInstance().login("dvirn@gmail.com", "12121290", ClientType.CUSTOMER);


        //Purchase of a coupon by a customer:
      //  try {

          // dvir.addCoupon(10);
        //}catch (CouponException err){
          //  System.out.println(err.getMessage());
        //}


        //Attempt to purchase a coupon that already exists with the customer:
       // try {
         //   dolev.addCoupon(3);
        //}catch (CouponException err){
          //  System.out.println("You can not purchase a coupon more than once, please select a new coupon");
       // }


     //dvir.getAllCouponsCustomer();
dvir.getCustomerDetails();
        Predicate<Coupon>companyID=coupon -> coupon.getCompanyID()==1;
        List<Coupon>couponList=dvir.getAllCouponsCustomer().stream()
                .filter(companyID)
                .collect(Collectors.toList());
        couponList.forEach(System.out::println);
        }
    }
