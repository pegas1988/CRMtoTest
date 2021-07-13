package dao;

import entity.Order;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void create(Order order, List<String> products);

    void delete(Order order);

    List<Order> findAll();

    Order findById(Integer id);

    List<Order> findByProduct(String product);

    List<Order> findByCreatingDate(Date date);

    void ordersCreating(List<Order> orders, PreparedStatement preparedStatement) throws SQLException;
}
