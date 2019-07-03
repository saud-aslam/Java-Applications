package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {


    private static final String sqlStatement = "SELECT c.first_name, c.last_name, c.email, o.order_id, o.creation_date, " +
            "o.total_due, o.status, s.first_name, s.last_name, s.email, ol.quantity, p.code, p.name, p.size, " +
            "p.variety, p.price FROM orders o JOIN customer c on o.customer_id = c.customer_id JOIN salesperson s " +
            "on o.salesperson_id = s.salesperson_id JOIN order_item ol on ol.order_id = o.order_id JOIN product p " +
            "on ol.product_id = p.product_id where o.order_id = ?";

    public OrderDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Order findById(long id) {
        Order order = new Order();
        try (PreparedStatement statement = this.connection.prepareStatement(sqlStatement);) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                order.setCustFN(resultSet.getString(1));
                order.setCustLN(resultSet.getString(2));
                order.setCust_email(resultSet.getString(3));
                order.setId(resultSet.getLong(4));
                order.setOrderDate(new Date(resultSet.getDate(5).getTime()));
                order.setOrderTotalDue(resultSet.getInt(6));
                order.setOrderStatus(resultSet.getString(7));
                order.setSalespersonFN(resultSet.getString(8));
                order.setSalespersonLN(resultSet.getString(9));
                order.setSalespersonEmail(resultSet.getString(10));
                order.setOrderQuantity(resultSet.getInt(11));
                order.setProdCode(resultSet.getString(12));
                order.setProdName(resultSet.getString(13));
                order.setProdVar(resultSet.getString(15));
                order.setProdSize(resultSet.getInt(14));
                order.setProdPrice(resultSet.getInt(16));
                order.setOrderID(id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return order;

    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order update(Order dto) {
        return null;
    }

    @Override
    public Order create(Order dto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}