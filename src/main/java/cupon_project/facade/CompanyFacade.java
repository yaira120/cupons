package cupon_project.facade;

import cupon_project.beans.Category;
import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.dao.CompanyDao;
import cupon_project.dbdao.CompanyDbdao;
import cupon_project.dbdao.CouponDbdao;
import cupon_project.dbutils.DataBaseUtils;
import cupon_project.exceptions.CompanyExistsException;
import cupon_project.exceptions.CompanyLoginExeption;
import cupon_project.exceptions.CouponException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyFacade extends ClientFacade {
    private CompanyDbdao companyDbdao;
    private Company company;
    private Coupon coupon;
    private CouponDbdao couponDbdao = new CouponDbdao();
    private int id;

    public CompanyFacade() {
        companyDbdao = new CompanyDbdao();
    }

    @Override
    public boolean login(String email, String password) throws CompanyLoginExeption, SQLException, InterruptedException {
        if (companyDbdao.isCompanyExists(email, password)) {
            id = companyDbdao.getIdByEmail(email);
            return true;
        }
        return false;
    }

    /*  public boolean createCompany(Company company){
          try{
              companyDbdao.addCompany(company);
              return true;
          } catch (SQLException throwables) {
              System.out.println(throwables.getMessage());
              return false;
          }

      }*/


    public void addNewCoupon(Coupon coupon) throws SQLException, InterruptedException, CouponException {
        CouponDbdao couponDbdao = new CouponDbdao();
        List<Coupon>couponList=new ArrayList<>();
        Company company=new Company();
        coupon.getCategory().toString();
        if (couponDbdao.checkCuoponExistsByTitle(coupon)) {
            throw new CouponException("please select coupon with another title");
        }
        coupon.setCompanyID(id);
        couponDbdao.addCoupon(coupon);

    }





/*
    public List<Coupon> getAllCouponOfCompany(int company_ID) throws SQLException, InterruptedException {
        CouponDbdao couponDbdao=new CouponDbdao();


        List<Coupon> allTheCoupon = new ArrayList<>();
        List<Coupon> couponList = couponDbdao.getAllCoupon();
        for (Coupon item : couponList) {
            if (item.getCompanyID() == company_ID)
                System.out.println(item);
            allTheCoupon.add(item);

        }
        return allTheCoupon;
*/


    public void getAllCouponByCategory(Category category, int company_id) throws SQLException, InterruptedException {
        // List<Coupon> couponList = getAllCouponOfCompany(company_id);
        // for (Coupon item : couponList) {
        // if (item.getCategory().equals(category))
        //  System.out.println(item);

        // }

    }


    public void getCompanyDetails() throws SQLException, InterruptedException, CompanyExistsException, CompanyLoginExeption {

        System.out.println(companyDbdao.getOneCompany(id));
    }


    public void updateCoupon(Coupon coupon) throws SQLException, InterruptedException, CouponException {
        if (!couponDbdao.checkCuoponExistsByID(coupon.getId())) {
            throw new CouponException("coupon not exists ");
        }
        //couponDbdao.updateCoupon(coupon);
        couponDbdao.updateCoupon(coupon);
        System.out.println("Coupon successfully updated");

    }


    public List<Coupon> getAllCouponsUnderPrice(double maxPrice) throws SQLException, InterruptedException {

        List<Coupon> couponList = companyDbdao.couponUnderPrice(maxPrice, id);

        return couponList;
    }





    public void getCouponsCompanyByCategory(Category category) throws SQLException, InterruptedException {
        List<Coupon> couponList = new ArrayList<>();
        // coupon= (getCouponByCompany(id));
        couponList = companyDbdao.getCouponCompanyByCategory(category,id);
        System.out.println(couponList);


    }


    public void getCuoponsUnderPrice(double price) throws SQLException, InterruptedException {
        List<Coupon> couponList = new ArrayList<>();
        couponList = companyDbdao.couponUnderPrice(price, id);
        System.out.println(couponList);

    }

    public void deleteExistingCoupon(Coupon coupon) throws SQLException, InterruptedException, CouponException {
        if (!couponDbdao.checkCuoponExistsByID(coupon.getId()) || coupon.getCompanyID() != id) {
           throw new CouponException("coupon not exists or check company_id");
      }
      // companyDbdao.deleteCouponCompany(coupon);
      // couponDbdao.deleteCuoponsByCouponIDInCustomersVSCuopons(id);
        couponDbdao.deleteCuoponsByCouponIDInCustomersVSCuopons(coupon.getId());
     couponDbdao.deleteCuoponByCouponID(coupon.getId());


    }

   //public Company getCompanyById(int company_id) throws SQLException, InterruptedException {
       // Map<Integer, Object> params = new HashMap<>();
       // params.put(1, company_id);
        //ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(GET_ID_BY_EMAIL, params);

         //resultSet.next();
      // List<Coupon>couponList=new ArrayList<>();
        //Company company=new Company();
      //  company=companyDbdao.getOneCompany(id);
      //  couponList.add(getCouponsByCompany());
     //  companyDbdao.getOneCompany(id).setCoupons(couponList);


       // return company;
         //}



    }




