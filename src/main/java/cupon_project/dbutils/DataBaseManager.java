package cupon_project.dbutils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseManager {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER_NAME = "root";
    public static final String USER_PASS = "12345678";
    private static Connection connection=null;

    public static final String DROP_DB = "DROP DATABASE coupon_phase1";
    public static final int MAX_CONNECTION = 10;

    public static final String CREATE_DB = "CREATE DATABASE IF NOT EXISTS coupon_phase1";

    //db tables
    public static final String CREATE_COMPANY_TABLE = "CREATE TABLE `coupon_phase1`.`companies` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`));";
    public static final String CREATE_CUSTOMER_TABLE = "CREATE TABLE `coupon_phase1`.`customers` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `first_name` VARCHAR(45) NOT NULL," +
            "  `last_name` VARCHAR(45) NOT NULL," +
            "  `email` VARCHAR(45) NOT NULL," +
            "  `password` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`));";
    public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE `coupon_phase1`.`categories` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`));";
    public static final String CREATE_COUPON_TABLE = "CREATE TABLE `coupon_phase1`.`coupons` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `company_id` INT NOT NULL," +
            "  `category` VARCHAR(45) NOT NULL," +
           // "  `category_id` INT NOT NULL," +
            "  `title` VARCHAR(45) NOT NULL," +
            "  `description` VARCHAR(45) NOT NULL," +
            "  `start_date` DATE NOT NULL," +
            "  `end_date` DATE NOT NULL," +
            "  `amount` INT NOT NULL," +
            "  `price` DOUBLE NOT NULL," +
            "  `image` VARCHAR(45) NOT NULL," +
            "  PRIMARY KEY (`id`)," +
            //"  INDEX `company_id_idx` (`company_id` ASC) VISIBLE," +
            //"  INDEX `category_id_idx` (`category_id` ASC) VISIBLE," +
            "  CONSTRAINT `company_id`" +
            "    FOREIGN KEY (`company_id`)" +
            "    REFERENCES `coupon_phase1`.`companies` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION)";
           // "  CONSTRAINT `category_id`" +
            //"    FOREIGN KEY (`category_id`)" +
           // "    REFERENCES `coupon_phase1`.`categories` (`id`)" +
            //"    ON DELETE NO ACTION" +
            //"    ON UPDATE NO ACTION);";
    public static final String CREATE_CUSTOMERS_VS_COUPONS_TABLE = "CREATE TABLE `coupon_phase1`.`customers_coupons` (" +
            "  `customer_id` INT NOT NULL," +
            "  `coupon_id` INT NOT NULL," +
            "  PRIMARY KEY (`customer_id`, `coupon_id`)," +
            "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE," +
            "  CONSTRAINT `customer_id`" +
            "    FOREIGN KEY (`customer_id`)" +
            "    REFERENCES `coupon_phase1`.`customers` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION," +
            "  CONSTRAINT `coupon_id`" +
            "    FOREIGN KEY (`coupon_id`)" +
            "    REFERENCES `coupon_phase1`.`coupons` (`id`)" +
            "    ON DELETE NO ACTION" +
            "    ON UPDATE NO ACTION);";

    public static void runSQL(String sql) throws SQLException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException | InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }


    }


}
