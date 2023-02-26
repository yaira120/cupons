package cupon_project.dbdao;

import cupon_project.beans.Category;
import cupon_project.beans.Company;
import cupon_project.beans.Coupon;
import cupon_project.dao.CompanyDao;
import cupon_project.dbutils.DataBaseManager;
import cupon_project.dbutils.DataBaseUtils;
import cupon_project.exceptions.CompanyLoginExeption;
import cupon_project.facade.CompanyFacade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CompanyDbdao implements CompanyDao {
    private static final String CHECK_COMPANY_NAME_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.companies WHERE name=?)";
    private static final String CHECK_COMPANY_MAIL_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.companies WHERE email=?)";
    public static final String CHECK_COMPANY_ID_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.companies WHERE id=?)";
/*
private Category category;
    private String title;
    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int amount;
    private double price;
 */

    private static final String SELECT_COUPONS_BY_CATEGORY = "SELECT * FROM coupon_phase1.coupons WHERE category=? and company_id=?";
    private static final String SELECT_COUPONS_BY_COMPANY_ID = "SELECT * FROM coupon_phase1.coupons WHERE company_id=?";
    private static final String CUOPON_UNDER_PRICE = "SELECT * FROM coupon_phase1.coupons WHERE company_id=? AND price<=?";
    private static final String CHECK_COMPANY_EXISTS = "SELECT EXISTS(SELECT * FROM coupon_phase1.companies WHERE email= ? AND password=?) ";
    private static final String ADD_COMPANY = "INSERT INTO coupon_phase1.companies " +
            " (name,email,password) " +
            " VALUES(?,?,?);";
    private static final String GET_ALL_COMPAMNIES = "SELECT * FROM coupon_phase1.companies";
    private static final String GET_ID_BY_EMAIL = "SELECT id FROM coupon_phase1.companies WHERE email= ?";

    private static final String UPDATE_COMPANY = "UPDATE coupon_phase1.companies " +
            "SET  email=?,password=? WHERE id=?";
    private static final String DELETE_COMPANY = "DELETE FROM coupon_phase1.companies WHERE id=?";

    private static final String SELECT_COMPANY = "SELECT * FROM coupon_phase1.companies WHERE id=? ";

    private static final String UPDATE_EXISTS_CUOPON = "UPDATE coupon_phase1.companies SET category=?,title=?,description=?,start_date=?,end_date=?,amount=?,price=? WHERE company_id=?";
    private static final String DELETE_COUPON_COMPANY_BY_ID = "DELETE FROM coupon_phase1.coupons WHERE id=? and company_id=?";
//private final String DELETE_COMPANY=""

    @Override
    public boolean isCompanyExists(String email, String password) throws CompanyLoginExeption, SQLException, InterruptedException {
        /*Company company=new Company();
        Map<Integer, Object> params = new HashMap<>();
        params.put(1,company.getEmail());
        ===================================================================
          List<Company>companyList=getAllCompanies();
        for (Company item:getAllCompanies()){
            if(item.getEmail().equals(email)&&item.getPassword().equals(password)){
                System.out.println("The company already exists");
                return true;
            }
        }

        return false;
    }

         */
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COMPANY_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;

    }


    @Override
    public void addCompany(Company company) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        try {
            DataBaseUtils.runQuery(ADD_COMPANY, params);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // DataBaseManager.runSQL(DataBaseManager.CREATE_COMPANY_TABLE);
    }

    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        //params.put(1, company.getName());
        params.put(1, company.getEmail());
        params.put(2, company.getPassword());
        params.put(3, company.getId());
        try {
            DataBaseUtils.runQuery(UPDATE_COMPANY, params);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCompany(int companyID) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        try {
            DataBaseUtils.runQuery(DELETE_COMPANY, params);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<Company> getAllCompanies() throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();

        List<Company> companies = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = (ResultSet) DataBaseUtils.runQueryForResult(GET_ALL_COMPAMNIES, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (resultSet.next()) {
            Company company = new Company();
            company.setId(resultSet.getInt("id"));
            company.setName(resultSet.getString("name"));
            company.setEmail(resultSet.getString("email"));
            company.setPassword(resultSet.getString("password"));
            companies.add(company);
        }


        return companies;
    }

    @Override
    public Company getOneCompany(int companyID) throws SQLException, InterruptedException {

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        //ResultSet resultSet = null;

        Company company = new Company();


        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(SELECT_COMPANY, params);
        resultSet.next();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setEmail(resultSet.getString("email"));
        company.setPassword(resultSet.getString("password"));

        return company;


    }

    public boolean checkNameCompanyExists(Company company) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COMPANY_NAME_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;

    }


    public boolean checkEmailCompanyExists(Company company) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getEmail());
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COMPANY_MAIL_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;

    }


    public int getIdByEmail(String email) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(GET_ID_BY_EMAIL, params);
        resultSet.next();
        return resultSet.getInt("id");


    }


    public boolean checkIdCompanyExists(int company_ID) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company_ID);
        ResultSet resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CHECK_COMPANY_ID_EXISTS, params);
        resultSet.next();
        if (resultSet.getInt(1) == 0) {
            return false;
        }
        return true;


/*
 private Category category;
    private String title;
    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int amount;
    private double price;
    private String image;
 */

    }

    public void updateCuoponCompany(Coupon coupon) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCategory().toString());
        params.put(2, coupon.getTitle());
        params.put(3, coupon.getDescription());
        params.put(4, coupon.getStartDate());
        params.put(5, coupon.getEndDate());
        params.put(6, coupon.getPrice());
        params.put(7, coupon.getImage());
        params.put(8, coupon.getCompanyID());
        try {
            DataBaseUtils.runQuery(UPDATE_EXISTS_CUOPON, params);
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public List<Coupon> couponUnderPrice(double maxPrice, int company_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company_id);
        params.put(2, maxPrice);

        List<Coupon> couponList = new ArrayList<>();
        ResultSet resultSet = null;

        resultSet = (ResultSet) DataBaseUtils.runQueryForResult(CUOPON_UNDER_PRICE, params);
        while (resultSet.next()) {
            Coupon coupon = new Coupon();
            coupon.setId(resultSet.getInt("id"));
            coupon.setCompanyID(resultSet.getInt("company_id"));
            coupon.setCategory(Category.valueOf(resultSet.getString("category")));
            coupon.setTitle(resultSet.getString("title"));
            coupon.setDescription(resultSet.getString("description"));
            coupon.setStartDate(resultSet.getDate("start_date"));
            coupon.setEndDate(resultSet.getDate("end_date"));
            coupon.setAmount(resultSet.getInt("amount"));
            coupon.setPrice(resultSet.getDouble("price"));
            coupon.setImage(resultSet.getString("image"));

            couponList.add(coupon);
        }
        return couponList;
    }

    public List<Coupon> getCouponsByCompany(int company_id) throws SQLException, InterruptedException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company_id);

        CouponDbdao couponDbdao = new CouponDbdao();
        List<Coupon> couponList = new ArrayList<>();
        ResultSet resultSet = null;

        resultSet = (ResultSet) DataBaseUtils.runQueryForResult(SELECT_COUPONS_BY_COMPANY_ID, params);
        while (resultSet.next()) {
            Coupon coupon = new Coupon();
            coupon.setId(resultSet.getInt("id"));
            coupon.setCompanyID(resultSet.getInt("company_id"));
            coupon.setCategory(Category.valueOf(resultSet.getString("category")));
            coupon.setTitle(resultSet.getString("title"));
            coupon.setDescription(resultSet.getString("description"));
            coupon.setStartDate(resultSet.getDate("start_date"));
            coupon.setEndDate(resultSet.getDate("end_date"));
            coupon.setAmount(resultSet.getInt("amount"));
            coupon.setPrice(resultSet.getDouble("price"));
            coupon.setImage(resultSet.getString("image"));

            couponList.add(coupon);
        }
        return couponList;
    }


    public List<Coupon> getCouponCompanyByCategory(Category category, int company_id) throws SQLException, InterruptedException {

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, category.toString());
        params.put(2, company_id);

        CouponDbdao couponDbdao = new CouponDbdao();
        List<Coupon> couponList = new ArrayList<>();
        ResultSet resultSet = null;

        resultSet = (ResultSet) DataBaseUtils.runQueryForResult(SELECT_COUPONS_BY_CATEGORY, params);
        while (resultSet.next()) {
            Coupon coupon = new Coupon();
            coupon.setId(resultSet.getInt("id"));
            coupon.setCompanyID(resultSet.getInt("company_id"));
            coupon.setCategory(Category.valueOf(resultSet.getString("category")));
            coupon.setTitle(resultSet.getString("title"));
            coupon.setDescription(resultSet.getString("description"));
            coupon.setStartDate(resultSet.getDate("start_date"));
            coupon.setEndDate(resultSet.getDate("end_date"));
            coupon.setAmount(resultSet.getInt("amount"));
            coupon.setPrice(resultSet.getDouble("price"));
            coupon.setImage(resultSet.getString("image"));

            couponList.add(coupon);
        }
        return couponList;


    }

    public void deleteCouponCompany(Coupon coupon) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getId());
        params.put(2, coupon.getCompanyID());

        try {
            DataBaseUtils.runQuery(DELETE_COUPON_COMPANY_BY_ID, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}




