package dao.impl;

import dao.OrderDao;
import entity.Product;
import org.springframework.stereotype.Repository;
import utility.PostgresUtil;
import entity.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final String SELECT_ALL = "select * from order";
    private static final String FIND_BY_CREATING_DATE = "select * from order where creating_date = ?";
    private static final String FIND_BY_PRODUCT = "select * from order where product = ?";
    private static final String FIND_BY_ID = "select * from order where order_id = ?";
    private static final String CREATE_ORDER = "insert into \"order\" (client_fio, comment, order_stage, responsible_person) values (?,?,?,?)";
    private static final String DELETE = "update order set client_fio = ?";
    private static final String ORDER_PRODUCT = "insert into order_product (order_id, product_name) values (?,?)";

    public OrderDaoImpl() {
    }

    private DataSource dataSource;
    //DELETE will work as i marker in line with other parameters (was deleted / alive)

    public OrderDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Order order, List<String> products) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementForProducts = connection.prepareStatement(ORDER_PRODUCT);
        ) {
            //preparedStatement.setDate(1, Date.valueOf(order.getDateCreating().toString()));
            preparedStatement.setString(1, order.getClient().getFirstName());
            preparedStatement.setString(2, order.getComments());
            preparedStatement.setString(3, order.getStage());
            preparedStatement.setString(4, order.getResponsibleUser());
            preparedStatement.execute();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            while (generatedKeys.next()) {
                order.setOrderID(generatedKeys.getInt("order_id"));
            }
            for (int i = 0; i < products.size(); i++) {
                preparedStatementForProducts.setInt(1, order.getOrderID());
                preparedStatementForProducts.setString(2, products.get(i));
                preparedStatementForProducts.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        ) {
            preparedStatement.setString(1, order.getLifeCycle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        ) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderID(resultSet.getInt("order_id"));
                order.setLifeCycle(resultSet.getString("life_cycle"));
                order.setComments(resultSet.getString("comment"));
                order.setStage(resultSet.getString("order_stage"));
                order.setDateCreating(resultSet.getTimestamp("date_of_birth"));
                order.setResponsibleUser(resultSet.getString("responsible_person"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        Order order = new Order();
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.setOrderID(resultSet.getInt("order_id"));
                order.setLifeCycle(resultSet.getString("life_cycle"));
                order.setComments(resultSet.getString("comment"));
                order.setStage(resultSet.getString("order_stage"));
                order.setDateCreating(resultSet.getTimestamp("creating_date"));
                order.setResponsibleUser(resultSet.getString("responsible_person"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findByProduct(String product) {
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PRODUCT)) {
            preparedStatement.setString(1, product);
            ordersCreating(orders, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findByCreatingDate(Date date) {
        List<Order> orders = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CREATING_DATE)) {
            preparedStatement.setDate(1, date);
            ordersCreating(orders, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void ordersCreating(List<Order> orders, PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet;
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Order order = new Order();
            order.setOrderID(resultSet.getInt("order_id"));
            order.setLifeCycle(resultSet.getString("life_cycle"));
            order.setComments(resultSet.getString("comment"));
            order.setStage(resultSet.getString("order_stage"));
            order.setDateCreating(resultSet.getTimestamp("creating_date"));
            order.setResponsibleUser(resultSet.getString("responsible_person"));
            orders.add(order);
        }
    }
}
