package dao.impl;

import dao.ProductDao;
import dao.impl.MaterialDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import utility.PostgresUtil;
import entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {
    private static final String SELECT_FROM_PRODUCT = "select * from product";
    private static final String CREATE_PRODUCT = "insert into product (product_name) values (?)";
    private static final String DELETE_FROM_PRODUCT = "delete from product where product_name = ?";
    private static final String GET_PRODUCT_ID_BY_NAME = "select product_id from product where product_name = ?";
    private static final String INSERT_INTO_PRODUCT_MATERIAL_PRODUCT_ID_MATERIAL_ID_VALUES = "insert into product_material (product_id, material_id) values (?, ?)";

    @Autowired
    DataSource dataSource;

    public ProductDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ProductDaoImpl() {
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_FROM_PRODUCT)
        ) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setTimeToProduce(resultSet.getInt("production_time"));
                product.setPriceOfAllMaterials(resultSet.getInt("material_price"));
                product.setProductName(resultSet.getString("product_name"));
                product.setId(resultSet.getInt("product_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int productID(Product product) {
        int productID = 0;
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_ID_BY_NAME)) {
            preparedStatement.setString(1, product.getProductName());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productID = resultSet.getInt("product_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productID;
    }

    @Override
    public void create(Product product) {
        //MaterialDaoImpl materialDaoImpl = new MaterialDaoImpl();
        try (Connection connection = dataSource.getConnection();
             //PreparedStatement preparedStatementProductID = connection.prepareStatement(INSERT_INTO_PRODUCT_MATERIAL_PRODUCT_ID_MATERIAL_ID_VALUES);
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT)) {
            //preparedStatement.setInt(1, product.getPriceOfAllMaterials());
            //preparedStatement.setInt(2, product.getTimeToProduce());
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.execute();
            /*for (int e = 0; e < product.getMaterialList().size(); e++) {
                preparedStatementProductID.setInt(1, productID(product));
                preparedStatementProductID.setInt(2, materialDao.materialID(product.getMaterialList().get(e)));
                preparedStatementProductID.execute();
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteFromProduct(Product product) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_PRODUCT)) {
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
