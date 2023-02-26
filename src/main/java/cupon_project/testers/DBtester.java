package cupon_project.testers;

import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.beans.Customer;
import cupon_project.dao.CompanyDao;
import cupon_project.dbdao.CompanyDbdao;
import cupon_project.dbdao.CouponDbdao;
import cupon_project.dbutils.DataBaseManager;
import cupon_project.dbutils.DataBaseUtils;

import java.sql.SQLException;

public class DBtester {
    public static void main(String[] args) throws Exception {
        //drop existing DATA BASE
        //DataBaseManager.runSQL(DataBaseManager.DROP_DB);

        //create db
        // DataBaseManager.runSQL(DataBaseManager.CREATE_DB);
        //create companies table

        //DataBaseManager.runSQL(DataBaseManager.CREATE_COMPANY_TABLE);
        // DataBaseManager.runSQL(DataBaseManager.CREATE_CUSTOMER_TABLE);
        // DataBaseManager.runSQL(DataBaseManager.CREATE_CATEGORY_TABLE);
        // DataBaseManager.runSQL(DataBaseManager.CREATE_COUPON_TABLE);
        // DataBaseManager.runSQL(DataBaseManager.CREATE_CUSTOMERS_VS_COUPONS_TABLE);


        //add company
/*
        Company nike = new Company("moti", "motig@gmail.com", "1234");

        CompanyDbdao companyDbdao = new CompanyDbdao();
        //companyDbdao.addCompany(nike);


        //update company
        int old = nike.getId();
        nike.setName("sjheek");
        nike.setEmail("kkj");
        nike.setPassword("dsfdsds");
        //  companyDbdao.updateCompany(nike,old);
       // System.out.println(nike);

        //companyDbdao.deleteCompany(2);

        // System.out.println(companyDbdao.getOneCompany(6));
        // System.out.println(companyDbdao.getAllCompanies());
        //  companyDbdao.isCompanyExists("kkdsj","ddssfdsds");


        //create db
        DataBaseManager.runSQL(DataBaseManager.CREATE_DB);
        //create companies table

       DataBaseManager.runSQL(DataBaseManager.CREATE_COMPANY_TABLE);
         DataBaseManager.runSQL(DataBaseManager.CREATE_CUSTOMER_TABLE);
         DataBaseManager.runSQL(DataBaseManager.CREATE_CATEGORY_TABLE);
        DataBaseManager.runSQL(DataBaseManager.CREATE_COUPON_TABLE);
         DataBaseManager.runSQL(DataBaseManager.CREATE_CUSTOMERS_VS_COUPONS_TABLE);

 */

    }
}
