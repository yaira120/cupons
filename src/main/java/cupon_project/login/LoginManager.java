package cupon_project.login;

import cupon_project.exceptions.AdminLoginExeption;
import cupon_project.exceptions.CompanyLoginExeption;
import cupon_project.exceptions.CustomerLoginExeption;
import cupon_project.facade.AdminFacade;
import cupon_project.facade.ClientFacade;
import cupon_project.facade.CompanyFacade;
import cupon_project.facade.CustomerFacade;
import cupon_project.utils.DateUtils;

import javax.xml.crypto.Data;
import java.sql.SQLException;

public class LoginManager {
    private static LoginManager instance = null;
    private Object LoginExeption;

    private LoginManager() {
    }

    public static LoginManager getInstance() {
        if (instance == null) {
            synchronized (LoginManager.class) {
                if (instance == null) {
                    instance = new LoginManager();
                }
            }
        }
        return instance;
    }

    public ClientFacade login(String email, String password, ClientType clientType) throws Exception {
        switch (clientType) {
            case ADMINISTRATOR:
                AdminFacade adminFacade = new AdminFacade();
                if (!adminFacade.login(email, password)) {
                    throw new AdminLoginExeption();
                }
                System.out.println(DateUtils.beutufulyDateAndTimeNow() + email + "was logged");

                return adminFacade;

            case CUSTOMER:
                CustomerFacade customerFacade = new CustomerFacade();
                if (!customerFacade.login(email, password)) {
                    throw new CustomerLoginExeption();
                }
                System.out.println(DateUtils.beutufulyDateAndTimeNow() + email + "was logged");
                return customerFacade;
            case COMPANY:
                CompanyFacade companyFacade = new CompanyFacade();
                if (!companyFacade.login(email, password)) {
                    throw new CompanyLoginExeption();
                }
                System.out.println(DateUtils.beutufulyDateAndTimeNow() + email + "was logged");
                return companyFacade;
        }


        return null;

    }
}

