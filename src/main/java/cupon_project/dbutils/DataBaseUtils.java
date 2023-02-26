package cupon_project.dbutils;

import com.mysql.cj.protocol.Resultset;
import cupon_project.beans.Category;
import cupon_project.beans.Company;
import cupon_project.beans.Customer;

import java.sql.*;
import java.util.Map;

public class DataBaseUtils {
    private static Connection connection;
    static PreparedStatement statement;

    public static void runQuery(String query, Map<Integer, Object> params) throws SQLException, InterruptedException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            statement = javaToSql(query, params, connection);
            javaToSql(query, params, connection);
            statement.execute();

        } catch (SQLException | InterruptedException err) {
            System.out.println(err.getMessage());
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public static Resultset runQueryForResult(String query, Map<Integer, Object> params) throws SQLException, InterruptedException {
        connection = ConnectionPool.getInstance().getConnection();
        Resultset resultset = null;
        try {
            statement = javaToSql(query, params, connection);
            resultset = (Resultset) statement.executeQuery();
        } catch (SQLException | InterruptedException err) {
            System.out.println(err.getMessage());
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
        return resultset;
    }

    public static PreparedStatement javaToSql(String query, Map<Integer, Object> params, Connection connection) throws InterruptedException {
        try {
            statement = connection.prepareStatement(query);
            params.forEach((key, value) -> {
                try {
                    if (value instanceof Integer) {
                        statement.setInt(key, (Integer) value);
                    } else if (value instanceof String) {
                        statement.setString(key, String.valueOf(value));
                    } else if (value instanceof Date) {
                        statement.setDate(key, (Date) value);
                    } else if (value instanceof Double) {
                        statement.setDouble(key, (Double) value);
                    } else if (value instanceof Boolean) {
                        statement.setBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        statement.setFloat(key, (Float) value);
                   // }else if (value instanceof Category){
                    //  statement.setString(key,String.valueOf(value));
                    }

                } catch (SQLException err) {
                    System.out.println(err.getMessage());
                }
            });
            return statement;
        } catch (SQLException err) {
            System.out.println(err.getMessage());
        }
        return null;
    }
}

