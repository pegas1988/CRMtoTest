package service;

import dao.impl.OrderDaoImpl;
import entity.Order;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService {
    OrderDaoImpl orderDao = new OrderDaoImpl();

    public void createOrder(Order order, List<String> products) {
        orderDao.create(order, products);
    }

    public void deleteOrder(Order order) {
        order.setLifeCycle("deleted");
        orderDao.delete(order);
    }

    public List<Order> findAllOrders() {
        return orderDao.findAll();
    }

    public Order order(Integer id) {
        return orderDao.findById(id);
    }

    public List<Order> findByProduct(String product) {
        return orderDao.findByProduct(product);
    }

    public List<Order> findByCreating(Date date) {
        return orderDao.findByCreatingDate(date);
    }
}
