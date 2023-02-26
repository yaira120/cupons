package cupon_project.dao;

import cupon_project.beans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {
    boolean isCustomerExists(String email, String password) throws SQLException, InterruptedException;

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerID);

    List<Customer> getAllCustomer() throws SQLException;

    Customer getOneCustomer(int customerID) throws SQLException, InterruptedException;
}
