package cupon_project.dbdao;

import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.beans.Customer;
import cupon_project.dao.CustomerDao;
import cupon_project.dbutils.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerDbdao implements CustomerDao {
    private static final String CHECK_CUSTOMER_ID_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.customers WHERE id=?) ";
    private static final String CHECK_CUSTOMER_EMAIL_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.customers WHERE email=?) ";
    private static final String CHECK_CUSTOMER_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.customers WHERE email= ? AND password=?) ";
    private static final String ADD_CUSTOMER = "INSERT INTO coupon_phase1.customers" +
            "(first_name, last_name,email,password)" +
            "VALUES(?,?,?,?)";
    //private  final  String COMPANY_EXISTS="SELECT*"
    private static final String UPDATE_CUSTOMER = "UPDATE coupon_phase1.customers SET first_name=?,last_name=?,email=?,password=? WHERE id=?";
    private static final String GET_ID_BY_CUSTOMER_EMAIL = "SELECT id FROM coupon_phase1.customers WHERE email= ?";
    private static final String CHECK_CUSTOMER_VS_COUPONS_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.customers_coupons WHERE customer_id=? AND coupon_id=?)";
    private static final String CHECK_COUPON_AMOUNT="SELECT amount FROM coupon_phase1.coupons WHERE id=?";
    private static final String CHECK_COUPON_END_DATE="SELECT end_date FROM coupon_phase1.coupons WHERE id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM coupon_phase1.customers WHERE id=?";
    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM coupon_phase1.customers";
    private static final String GET_CUSTOMER = "SELECT * FROM coupon_phase1.customers WHERE id=?";
    private static final String DELETE_CUOPONS_BY_COMPANYID_IN_CUSTOMER_VS_CUOPONS = "DELETE FROM coupon_phase1.customers_coupons WHERE company_id=?";
    private static final String DELETE_CUOPONS_BY_CUSTOMERID_IN_CUSTOMER_VS_CUOPONS = "DELETE FROM coupon_phase1.customers_coupons WHERE customer_id=?";

    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);

        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_CUSTOMER_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;

    }


    @Override
    public void addCustomer(Customer customer) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());

        try {
            DataBaseUtils.runQuery(ADD_CUSTOMER, params);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void updateCustomer(Customer customer) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        // params.put(5, oldSn);
        params.put(5, customer.getId());
        try {
            DataBaseUtils.runQuery(UPDATE_CUSTOMER, params);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCustomer(int customerID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        try {
            DataBaseUtils.runQuery(DELETE_CUSTOMER, params);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Customer> getAllCustomer() throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        List<Customer> customers = new ArrayList<>();
        ResultSet resultSet = null;


        try {
            resultSet = (ResultSet) DataBaseUtils.runQueryForResult(GET_ALL_CUSTOMERS, params);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setId(resultSet.getInt("id"));
            customer.setFirstName(resultSet.getString("first_name"));
            customer.setLastName(resultSet.getString("last_name"));
            customer.setEmail(resultSet.getString("email"));
            customer.setPassword(resultSet.getString("password"));
            customers.add(customer);
        }


        return customers;
    }


    @Override
    public Customer getOneCustomer(int customerID) throws SQLException, InterruptedException {

        CouponDbdao couponDbdao=new CouponDbdao();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        //ResultSet resultSet = null;

        Customer customer = new Customer();
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(GET_CUSTOMER, params);
        try {
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customer.setId(resultSet.getInt("id"));
        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setEmail(resultSet.getString("email"));
        customer.setPassword(resultSet.getString("password"));
        customer.setCoupons(couponDbdao.getCouponByCustomerID(customerID));

        return customer;

    }

    public boolean checkEmailCustomerExsits(Customer customer) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getEmail());
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_CUSTOMER_EMAIL_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;

    }


    public boolean checkIDlCustomerExsits(int customer_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer_id);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_CUSTOMER_ID_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;

    }

    public void deleteFromCustomersVSCuoponsByCustomerID(int customer_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer_id);
        DataBaseUtils.runQuery(DELETE_CUOPONS_BY_CUSTOMERID_IN_CUSTOMER_VS_CUOPONS, params);
    }


    public int getIdCustomerByEmail(String email) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(GET_ID_BY_CUSTOMER_EMAIL, params);

        resultSet.next();

        return resultSet.getInt("id");


    }

    public boolean checkCustomerVsCouponsExists(int customer_id, int coupon_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer_id);
        params.put(2, coupon_id);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_CUSTOMER_VS_COUPONS_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;
    }



    public int getAmountOfCoupons(int coupon_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon_id);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COUPON_AMOUNT, params);
        resultSet.next();

        return resultSet.getInt("amount");
    }




    public Date checkEndDateOfCoupon(int coupon_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon_id);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COUPON_END_DATE, params);
        resultSet.next();

        return resultSet.getDate("end_date");

    }
}
