package cupon_project.facade;

import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.beans.Customer;
import cupon_project.dbdao.CompanyDbdao;
import cupon_project.dbdao.CouponDbdao;
import cupon_project.dbdao.CustomerDbdao;
import cupon_project.exceptions.CompanyExistsException;
import cupon_project.exceptions.CompanyLoginExeption;
import cupon_project.exceptions.CustomerEmailException;
import cupon_project.exceptions.CustomerLoginExeption;

import java.sql.SQLException;
import java.util.concurrent.CompletionException;

public class AdminFacade extends ClientFacade {
    CompanyDbdao companyDbdao = new CompanyDbdao();
    CustomerDbdao customerDbdao = new CustomerDbdao();
    CouponDbdao couponDbdao = new CouponDbdao();


    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }


    public void addCompany(Company company) throws SQLException, InterruptedException, CompletionException, CompanyLoginExeption {


        if (companyDbdao.checkNameCompanyExists(company) || companyDbdao.checkEmailCompanyExists(company)) {
            throw new CompanyLoginExeption("The name or email already exists");
        }

        companyDbdao.addCompany(company);
        System.out.println("Added a new company called:" + company.getName());
    }


    public Company updateCompany(Company company) throws Exception {
        if (!companyDbdao.checkIdCompanyExists(company.getId())) {
            throw new CompanyExistsException();
        }
        companyDbdao.updateCompany(company);
        System.out.println("Company update was successful");
        return company;
    }


    public void getAllCompanies() throws SQLException, InterruptedException {
        CompanyDbdao companyDbdao = new CompanyDbdao();
        System.out.println(companyDbdao.getAllCompanies());
    }


    public Company getOneCompany(int company_id) throws SQLException, InterruptedException {
        CompanyDbdao companyDbdao = new CompanyDbdao();
        Company company = new Company();
        company = companyDbdao.getOneCompany(company_id);
        //System.out.println(companyDbdao.getOneCompany(company_id));

        return company;
    }


    public void addCustomer(Customer customer) throws SQLException, InterruptedException, CustomerEmailException {

        if (customerDbdao.checkEmailCustomerExsits(customer)) {

            throw new CustomerEmailException();
        }

        customerDbdao.addCustomer(customer);
        System.out.println("A new customer has been created");

    }


    public void updateCustomer(Customer customer) throws SQLException, InterruptedException, CustomerLoginExeption {
        if (!customerDbdao.checkIDlCustomerExsits(customer.getId())) {
            throw new CustomerLoginExeption();
        }
        customerDbdao.updateCustomer(customer);
        System.out.println("Customer update was successful");
    }


    public void getAllCustomers() throws SQLException {
        System.out.println(customerDbdao.getAllCustomer());
    }


    public Customer getOneCustomerByID(int id) throws SQLException, InterruptedException, CustomerLoginExeption {
        Customer customer = new Customer();
        if (customerDbdao.checkIDlCustomerExsits(id)) {
            customer = customerDbdao.getOneCustomer(id);
            return customer;
        }
        throw new CustomerLoginExeption();
    }


    public void deleteCompanyByID(int company_ID) throws SQLException, InterruptedException, CompanyExistsException {
        if (!companyDbdao.checkIdCompanyExists(company_ID)) {
            throw new CompanyExistsException();
        }
        


        for (Coupon coupon : this.companyDbdao.getCouponsByCompany(company_ID)) {
            this.couponDbdao.deleteCouponPurchase(coupon.getId(), company_ID);
            this.couponDbdao.deleteCoupon(coupon.getId());

        }
        companyDbdao.deleteCompany(company_ID);
    }




    public void deleteCustomerByID(int customer_id) throws SQLException, InterruptedException, CustomerLoginExeption {
        if (!customerDbdao.checkIDlCustomerExsits(customer_id)) {
            throw new CustomerLoginExeption();
        }

        customerDbdao.deleteCustomer(customer_id);
        customerDbdao.deleteFromCustomersVSCuoponsByCustomerID(customer_id);
    }
}



