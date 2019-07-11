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
            Customer customer = customerDAO.findById(1001);
            System.out.println(customer.getFirstName() + " " + customer.getLastName() + " " +
                    customer.getEmail());

            OrderDAO orderDAO = new OrderDAO(connection);
            Order order = orderDAO.findById(1009);
            System.out.println(order);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
