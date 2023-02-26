package cupon_project.facade;

import cupon_project.dao.CompanyDao;
import cupon_project.dao.CouponDao;
import cupon_project.dao.CustomerDao;

import java.sql.SQLException;

public abstract class ClientFacade {
    protected CustomerDao customerDao;
    protected CompanyDao companyDao;
    protected CouponDao couponDao;

    public  boolean login(String email,String password) throws SQLException, InterruptedException, Exception {
        return true;
    }


}
