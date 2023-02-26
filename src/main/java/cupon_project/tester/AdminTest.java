package cupon_project.tester;

import cupon_project.facade.AdminFacade;
import cupon_project.login.ClientType;
import cupon_project.login.LoginManager;
import lombok.Builder;

import java.util.Calendar;
import java.util.Date;

public class AdminTest {
    public static void main(String[] args) throws Exception {

        //Attempt to connect to the administrator with incorrect data
        // AdminFacade adminFacade=(AdminFacade) LoginManager.getInstance().login("lkjh","876",ClientType.ADMINISTRATOR);
        //adminFacade.addCustomer(new Customer("Dani","Zuko","jramil@gmail.com","08765"));


        //Proper login with the login manager
        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
        //adminFacade.getAllCompanies();


        //Creating companies:
        //adminFacade.addCompany(new Company("Denya", "denyasib@gmail.com", "1234567890"));
        //adminFacade.addCompany(new Company("Tidhar","tida@gmail.com","24680"));
        // adminFacade.addCompany(new Company("Electra","electra@gmail.com","13579"));
        // adminFacade.addCompany(new Company("Shikun_Binuy","shikunbin@gmail.com","7777777"));
        // adminFacade.addCompany(new Company("Afcon","afcon@gmail,com","101010"));


        //Acceptance of all companies:
     //adminFacade.getAllCompanies();


        //Receiving a company according to id:
        //adminFacade.getOneCompany(12);


        //Creating customers:
        //adminFacade.addCustomer(new Customer("Dvir","Nagar","dvirn@gmail.com","12121290"));
        //adminFacade.addCustomer(new Customer("Dolev","Turgeman","dolevto@gmail.com","909090"));
        //adminFacade.addCustomer(new Customer("Daniel","Yahud","daniy@gmailcom","90210"));
        //adminFacade.addCustomer(new Customer("Ronen","Shlush","ronsh@gmail.com","890890"));
        //adminFacade.addCustomer(new Customer("Rami","Levi","ramil@gmail.com","1000000"));


        //try to add in a new customer with an existing email:
        //adminFacade.addCustomer(new Customer("Dani","Zuko","ramil@gmail.com","08765"));






    }
}
