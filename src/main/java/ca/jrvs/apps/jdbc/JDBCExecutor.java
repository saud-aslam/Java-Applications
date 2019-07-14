package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCExecutor {

    public static void main(String... args) {
        DataConnectionManager dcm = new DataConnectionManager("localhost",
                "hplussport", "postgres", "password");

        try {

            Connection connection = dcm.getConnection();
            Customer customer;
                    switch (args[0]) {
                case "create":
                    customer = new Customer();
                    customer.setFirstName(args[1]);
                    customer.setLastName(args[2]);
                    customer.setEmail(args[3]);
                    customer.setPhone(args[4]);
                    customer.setAddress(args[5]);
                    customer.setCity(args[6]);
                    customer.setState(args[7]);
                    customer.setZipCode(args[8]);
                    System.out.println(new CustomerDAO(connection).create(customer));
                    break;
                case "update":
                    customer = new Customer();
                    customer.setId(Long.getLong(args[1]));
                    customer.setFirstName(args[2]);
                    customer.setLastName(args[3]);
                    customer.setEmail(args[4]);
                    customer.setPhone(args[5]);
                    customer.setAddress(args[6]);
                    customer.setCity(args[7]);
                    customer.setState(args[8]);
                    customer.setZipCode(args[9]);
                    System.out.println(new CustomerDAO(connection).update(customer));
                    break;
                case "read":
                    System.out.println(new CustomerDAO(connection).findById(Long.getLong(args[1])));
                    break;
                case "order":
                    System.out.println(new OrderDAO(connection).findById(Integer.valueOf(args[1])));
                    break;
                case "delete":
                    new CustomerDAO(connection).delete(Long.getLong(args[1]));
                 break;
                default:
                    System.out.println("Usage: create|update|read|order|delete");
            } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
