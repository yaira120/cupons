package cupon_project.dao;

import cupon_project.beans.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {
    boolean isCompanyExists(String email, String password) throws Exception;

    void  addCompany(Company company) throws SQLException;

    void updateCompany(Company company);

    void deleteCompany(int companyID) throws SQLException;

    List<Company> getAllCompanies() throws SQLException, InterruptedException;

    Company getOneCompany(int companyID) throws SQLException, InterruptedException;

     boolean checkEmailCompanyExists(Company company) throws SQLException, InterruptedException;

     boolean checkNameCompanyExists(Company company) throws SQLException, InterruptedException;
}
