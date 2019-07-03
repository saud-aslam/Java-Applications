package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {

    public static void main(String... args) {
        DataConnectionManager dcm = new DataConnectionManager("localhost",
                "hplussport", "postgres", "password");

        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("ma");
            customer.setLastName("Washington");
            customerDAO.create(customer);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
