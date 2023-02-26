package cupon_project.dbdao;

import cupon_project.beans.Category;
import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.beans.Customer;
import cupon_project.dao.CouponDao;
import cupon_project.dbutils.DataBaseUtils;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CouponDbdao implements CouponDao {

    private static final String CUOPON_UNDER_PRICE = "SELECT * FROM coupon_phase1.companies WHERE company_id=? AND price<=?";


    private static final String ADD_COUPON = "INSERT INTO coupon_phase1.coupons" +
            "(company_id,category,title,description,start_date,end_date,amount,price,image)" +
            "VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_COUPON = "UPDATE coupon_phase1.coupons SET category=?,title=?,description=?,start_date=?,end_date=?,amount=?,price=?,image=? WHERE id=?";

    private static final String DELETE_COUPON = "DELETE FROM coupon_phase1.coupons WHERE id=?";

    private static final String CHECK_COUPON_EXSITS_BY_ID = "SELECT EXISTS(SELECT * FROM coupon_phase1.coupons WHERE id=?)";

    private static final String CHECK_COUPON_EXSITS_BY_TITLE = "SELECT EXISTS(SELECT * FROM coupon_phase1.coupons WHERE title=?)";

    private static final String CHECK_COUPON_TITLE = "SELECT EXISTS(SELECT * FROM coupon_phase1.coupons WHERE title=? and company_id=?)";

    private static final String GET_ALL_COUPONS = "SELECT * FROM coupon_phase1.coupons";

    private static final String SELECT_COUPON = "SELECT * FROM coupon_phase1.coupons WHERE id=? ";
    private static final String SELECT_COUPON_BY_CUSTOMERID = "SELECT * FROM coupon_phase1.customers_coupons WHERE customer_id=?";

    private static final String UPDATE_AMOUNT_OF_COUPON_AFTER_PURCHASE_COUPON_FROM_COUPON_TABLE = "UPDATE coupon_phase1.coupons SET amount=amount-1 WHERE id=?";

    private static final String ADD_COUPON_TO_CUSTOMER = "INSERT INTO  coupon_phase1. customers_coupons (customer_id,coupon_id) VALUES(?,?)";

    private static final String DELETE_CUOPON_BY_COUOPON_ID = "DELETE FROM coupon_phase1.coupons WHERE id=?";

    private static final String DELETE_CUOPONS_BY_COMPANYID_IN_CUSTOMER_VS_CUOPONS = "DELETE FROM coupon_phase1.customers_coupons WHERE coupon_id = ?";

    private static final String DAILY_JOB = "DELETE FROM coupon_phase1.coupons WHERE end_date<?";

    @Override
    public void addCoupon(Coupon coupon) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyID());
        params.put(2, coupon.getCategory().toString());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());

        try {
            try {
                DataBaseUtils.runQuery(ADD_COUPON, params);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void updateCoupon(Coupon coupon) throws InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        // params.put(1,coupon.getId());
        // params.put(2,coupon.getCompanyID());
        params.put(1, coupon.getCategory().toString());
        params.put(2, coupon.getTitle());
        params.put(3, coupon.getDescription());
        params.put(4, coupon.getStartDate());
        params.put(5, coupon.getEndDate());
        params.put(6, coupon.getAmount());
        params.put(7, coupon.getPrice());
        params.put(8, coupon.getImage());

        try {
            DataBaseUtils.runQuery(UPDATE_COUPON, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @SneakyThrows
    @Override
    public void deleteCoupon(int couponID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        try {
            DataBaseUtils.runQuery(DELETE_COUPON, params);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Coupon> getAllCoupon() throws SQLException, InterruptedException {
        List<Coupon> coupons = new ArrayList<>();

        ResultSet resultSet = null;

        try {
            resultSet = (ResultSet) DataBaseUtils.runQueryForResult(GET_ALL_COUPONS, null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (resultSet.next()) {
            Coupon coupon = new Coupon();
            coupon.setId(resultSet.getInt("id"));
            coupon.setCompanyID(resultSet.getInt("companyID"));
            coupon.setCategory(Category.valueOf((String) resultSet.getString("category")));
            coupon.setTitle(resultSet.getString("title"));
            coupon.setDescription(resultSet.getString("description"));
            coupon.setStartDate(resultSet.getDate("startDate"));
            coupon.setStartDate(resultSet.getDate("endDate"));
            coupon.setAmount(resultSet.getInt("amount"));
            coupon.setPrice(resultSet.getDouble("price"));
            coupon.setImage(resultSet.getString("image"));

            coupons.add(coupon);


        }


        return coupons;
    }

    @Override
    public Coupon getOneCoupon(int couponID) throws InterruptedException, SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);
        Coupon coupon = new Coupon();
        ResultSet resultSet = null;

        resultSet = (ResultSet) DataBaseUtils.runQueryForResult(SELECT_COUPON, params);
        try {
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        coupon.setId(resultSet.getInt("id"));
        coupon.setCompanyID(resultSet.getInt("company_id"));
        coupon.setCategory(Category.valueOf((String) resultSet.getString("category")));
        coupon.setTitle(resultSet.getString("title"));
        coupon.setDescription(resultSet.getString("description"));
        coupon.setStartDate(resultSet.getDate("start_date"));
        coupon.setStartDate(resultSet.getDate("end_date"));
        coupon.setAmount(resultSet.getInt("amount"));
        coupon.setPrice(resultSet.getDouble("price"));
        coupon.setImage(resultSet.getString("image"));


        return coupon;
    }


    @Override
    public void addCouponPurchase(int customerID, int couponID) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponID);

        DataBaseUtils.runQuery(UPDATE_AMOUNT_OF_COUPON_AFTER_PURCHASE_COUPON_FROM_COUPON_TABLE, params);
        Map<Integer, Object> params2 = new HashMap<>();
        params2.put(1, customerID);
        params2.put(2, couponID);
        DataBaseUtils.runQuery(ADD_COUPON_TO_CUSTOMER, params2);


    }


    @Override
    public void deleteCouponPurchase(int couponID, int customerID) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);

        DataBaseUtils.runQuery(DELETE_CUOPONS_BY_COMPANYID_IN_CUSTOMER_VS_CUOPONS, params);

    }


    public void deleteCuoponByCouponID(int coupon_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon_id);
        DataBaseUtils.runQuery(DELETE_CUOPON_BY_COUOPON_ID, params);
    }


    public void deleteCuoponsByCouponIDInCustomersVSCuopons(int coupon_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon_id);
        DataBaseUtils.runQuery(DELETE_CUOPONS_BY_COMPANYID_IN_CUSTOMER_VS_CUOPONS, params);
    }


    public boolean checkCuoponExistsByID(int coupon_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon_id);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COUPON_EXSITS_BY_ID, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;


    }


    // public void deleteCuoponByCompanyID(int company_id)
    public boolean checkCuoponExistsByTitle(Coupon coupon) throws SQLException, InterruptedException {
        //Coupon coupon=new Coupon();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getTitle());
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COUPON_EXSITS_BY_TITLE, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;


    }

    public boolean checkCuoponExistsitle(Coupon coupon) throws SQLException, InterruptedException {
        //Coupon coupon=new Coupon();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getTitle());
        params.put(2, coupon.getCompanyID());
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COUPON_TITLE, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;

    }


    public void jobDeleteCoupons() throws SQLException, InterruptedException {
        Date date1 = new Date();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, date1);
        DataBaseUtils.runQuery(DAILY_JOB, params);


    }

    public List<Coupon> getCouponByCustomerID(int customet_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customet_id);

        ResultSet resultSet = null;

        try {
            resultSet = (ResultSet) DataBaseUtils.runQueryForResult(SELECT_COUPON_BY_CUSTOMERID, params);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
        List<Coupon> couponList = new ArrayList<>();
        while (resultSet.next()) {
            couponList.add(getOneCoupon(resultSet.getInt("coupon_id")));
        }
        return couponList;

    }

}




