package cupon_project.testers;

import cupon_project.beans.Category;
import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.dbdao.CompanyDbdao;
import cupon_project.dbdao.CouponDbdao;
import cupon_project.dbdao.CustomerDbdao;
import cupon_project.dbutils.DataBaseUtils;
import cupon_project.exceptions.CouponException;
import cupon_project.facade.CompanyFacade;
import cupon_project.login.ClientType;
import cupon_project.login.LoginManager;
import cupon_project.utils.DateUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyTester {
    public static void main(String[] args) throws Exception {
        CouponDbdao couponDbdao = new CouponDbdao();
        //Trying to log in with a company that does not exist in the system:
        // CompanyFacade companyFacade =(CompanyFacade) LoginManager.getInstance().login("Shukizikri@gmail.com","123", ClientType.COMPANY);


        //Trying to log in with a company that  exist in the system:
        CompanyFacade denya = (CompanyFacade) LoginManager.getInstance().login("denyasib@gmail.com", "1234567890", ClientType.COMPANY);
        //CompanyFacade tidhar=(CompanyFacade) LoginManager.getInstance().login("tida@gmail.com","24680",ClientType.COMPANY);


        // denya.addNewCoupon(new Coupon(Category.Electricity,"Smart TV","Smart TV with amazing viewing quality", DateUtils.getStartDate(),DateUtils.getEndDate(),20,2500,"******"));
        // denya.addNewCoupon(new Coupon(Category.Food,"Pizza Macpiza","The best pizza in the country",DateUtils.getStartDate(),DateUtils.getEndDate(),80,40,"@@@@@@@@"));
        // denya.addNewCoupon(new Coupon(Category.SportGame,"El Clasico","fc barcelona vs real madrid",DateUtils.getStartDate(),DateUtils.getEndDate(),60,1500,"&&&&&&"));
        //denya.addNewCoupon(new Coupon(Category.Vacation,"Club hotel Eilat","Hacol calul",DateUtils.getStartDate(),DateUtils.getEndDate(),100,800,"#######"));
        //denya.addNewCoupon(new Coupon(Category.Restaurant,"El Gaucho","The best meat restaurant in town",DateUtils.getStartDate(),DateUtils.getEndDate(),90,200,"++++++++"));
        //denya.addNewCoupon(new Coupon(Category.Restaurant,"Alfredo","Pampering breakfasts for the whole family",DateUtils.getStartDate(),DateUtils.getEndDate(),90,150,"+_+_+_+_+"));


        //tidhar.addNewCoupon(new Coupon(Category.Electricity,"Apple MacBook","I ve Got The power", DateUtils.getStartDate(),DateUtils.getEndDate(),90,7000,"$$$$$$$"));
        // tidhar.addNewCoupon(new Coupon(Category.Restaurant," Meat and Wine","restaurant with the highest quality meat", DateUtils.getStartDate(),DateUtils.getEndDate(),40,350,"%%%%%%%"));
        //tidhar.addNewCoupon(new Coupon(Category.Vacation,"Beresheet by Isrotel Exclusive","special design and desert landscape",DateUtils.getStartDate(),DateUtils.getEndDate(),60,500,"^^^^^^^"));
        //tidhar.addNewCoupon(new Coupon(Category.SportGame,"chicago bulls vs utah","NBA final- game number 7", DateUtils.getStartDate(),DateUtils.getEndDate(),120,180,"!@$%$^&" ));
        //tidhar.addNewCoupon(new Coupon(Category.Food,"Flafel Hatuka","falafel that everyone loves",DateUtils.getStartDate(),DateUtils.getEndDate(),200,10,"(*)(*)(*)(*)"));

        //Experience of a company inserting a coupon with a title that it has:

        // try {
//            denya.addNewCoupon(new Coupon(Category.SportGame, "Eldfggdfds Clasico", "fc barcelona vs real madrid", DateUtils.getStartDate(), DateUtils.getEndDate(), 60, 1500, "&&&&&&"));

        //      } catch (CouponException err) {
        //        System.out.println(err.getMessage());
        //  }


        //Update existing coupon:
        // TODO: 13/02/2022

        //CompanyDbdao companyDbdao = new CompanyDbdao();
        //Coupon coupon1 = new Coupon();
        //coupon1 = couponDbdao.getOneCoupon(3);
        //coupon1.setPrice(1250);
        //denya.updateCoupon(coupon1);


        //get coupons by company_id:
        //denya.getCouponsByCompany();


        //get coupons company by category;
     // denya.getCouponsCompanyByCategory(Category.Food);



        //get coupons company under price;
      // denya.getCuoponsUnderPrice(900);


        // get company details:
        // denya.getCompanyDetails();


        //delete coupon:
 //  denya.deleteExistingCoupon(couponDbdao.getOneCoupon(25));

        // denya.addNewCoupon(new Coupon(Category.Food,"Pizza Macpiza","The best pizza in the country",DateUtils.getStartDate(),DateUtils.getEndDate(),80,40,"@@@@@@@@"));
      //  couponDbdao.deleteCuoponsByCouponIDInCustomersVSCuopons(20);
//couponDbdao.deleteCuoponsByCouponIDInCustomersVSCuopons(20);
    }
}