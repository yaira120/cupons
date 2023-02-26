package cupon_project.facade;

import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.beans.Customer;
import cupon_project.dbdao.CompanyDbdao;
import cupon_project.exceptions.AdminLoginExeption;
import cupon_project.exceptions.CompanyLoginExeption;
import cupon_project.exceptions.CustomerEmailException;
import cupon_project.login.ClientType;
import cupon_project.login.LoginManager;

import java.sql.SQLException;

public class Tester {
    public static void main(String[] args) throws Exception {
        CompanyDbdao companyDbdao = new CompanyDbdao();


        //Attempt to connect to the administrator with incorrect data
        // AdminFacade adminFacade=(AdminFacade) LoginManager.getInstance().login("lkjh","876",ClientType.ADMINISTRATOR);
        //adminFacade.addCustomer(new Customer("Dani","Zuko","jramil@gmail.com","08765"));


        //Proper login with the login manager
        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
        //adminFacade.getAllCompanies();


        //Creating companies:
        // adminFacade.addCompany(new Company("Denya", "denyasib@gmail.com", "1234567890"));
        //adminFacade.addCompany(new Company("Tidhar", "tida@gmail.com", "24680"));
        //adminFacade.addCompany(new Company("Electra", "electra@gmail.com", "13579"));
        //adminFacade.addCompany(new Company("Shikun_Binuy", "shikunbin@gmail.com", "7777777"));
        // adminFacade.addCompany(new Company("Afcon", "afcon@gmail,com", "101010"));


        //Acceptance of all companies:
        //adminFacade.getAllCompanies();


        //Receiving a company according to id:
      //  System.out.println(adminFacade.getOneCompany(3));


        //Creating customers:
      // adminFacade.addCustomer(new Customer("Dvir","Nagar","dvirn@gmail.com","12121290"));
        //adminFacade.addCustomer(new Customer("Dolev","Turgeman","dolevto@gmail.com","909090"));
        //adminFacade.addCustomer(new Customer("Daniel","Yahud","daniy@gmailcom","90210"));
        //adminFacade.addCustomer(new Customer("Ronen","Shlush","ronsh@gmail.com","890890"));
        //system.out.println("new customer ronen shlush was created successfuly");
        //adminFacade.addCustomer(new Customer("Rami","Levi","ramil@gmail.com","1000000"));


        //try to add in a new customer with an existing email:
         /*
        try {
            adminFacade.addCustomer(new Customer("Dani", "Zuko", "ramil@gmail.com", "08765"));
        } catch (CustomerEmailException err){
            System.out.println("\ncaught error message: tried to create a new customer with same email");
            System.out.println("exception: "+err.getMessage());
        }
        */


      //  Update the company password;
        //Company company=adminFacade.getOneCompany(4);
       //company.setPassword("75465");
        //adminFacade.updateCompany(company);



        //Deleting a company
        //adminFacade.deleteCompanyByID(11);




        //update customer last name:
        /*
        try {
            Customer customer = adminFacade.getOneCustomerByID(2);
            customer.setFirstName("Dvir");
            customer.setLastName("Nagar");
            customer.setEmail("dvirnag@gmail.com");
            customer.setPassword("101010");
            adminFacade.updateCustomer(customer);
        }catch (SQLException err){
            System.out.println(err.getMessage());
        }
         */



        //Receiving a customer according to id:
        // System.out.println(adminFacade.getOneCustomerByID(2));



        //Delete an existing client:
       // adminFacade.deleteCustomerByID(7);


        //View all customers:
       //adminFacade.getAllCustomers();














    }

}
