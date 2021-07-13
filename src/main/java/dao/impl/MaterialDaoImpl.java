package dao.impl;

import dao.MaterialDao;
import org.springframework.stereotype.Repository;
import utility.PostgresUtil;
import entity.Material;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MaterialDaoImpl implements MaterialDao {
    private static final String FIND_ALL = "select * from material";
    private static final String FIND_BY_TYPE = "select * from material where type = ?";
    private static final String CREATE_MATERIAL = "insert into material (quantity, colour, type, price, material_name) values (?,?,?,?,?)";
    private static final String DELETE_FROM_MATERIAL_BY_NAME = "delete from material where material_name = ?";
    private static final String GET_MATERIAL_ID_BY_NAME = "select material_id from material where material_name = ? and colour = ? and type  = ? and price = ?";
    private static final String UPDATE_MATERIAL = "update material set quantity = ?, colour = ?, type = ?, price = ?, material_name = ? where material_id = ?";

    public MaterialDaoImpl() {
    }

    private DataSource dataSource;

    public MaterialDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void createNewMaterial(Material material) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MATERIAL)) {
            preparedStatement.setInt(1, material.getQuantity());
            preparedStatement.setString(2, material.getColour());
            //preparedStatement.setString(3, material.getDensity());
            preparedStatement.setString(3, material.getType());
            preparedStatement.setInt(4, material.getPrice());
            preparedStatement.setString(5, material.getMaterialName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromMaterialByName(Material material) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_MATERIAL_BY_NAME)) {
            preparedStatement.setString(1, material.getMaterialName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int materialID(Material material) {
        int materialID = 0;
        ResultSet resultSet = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MATERIAL_ID_BY_NAME)) {
            preparedStatement.setString(1, material.getMaterialName());
            preparedStatement.setString(2, material.getColour());
            preparedStatement.setString(3, material.getType());
            preparedStatement.setInt(4, material.getPrice());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                materialID = resultSet.getInt("material_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materialID;
    }

    @Override
    public List<Material> findAll() {
        List<Material> materials = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL)
        ) {
            makeMaterial(materials, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    @Override
    public List<Material> findByType(String type) {
        List<Material> materials = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_TYPE);
        ) {
            preparedStatement.setString(1, type);
            ResultSet resultSet = preparedStatement.executeQuery();
            makeMaterial(materials, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materials;
    }

    @Override
    public void makeMaterial(List<Material> materials, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Material material = new Material();
            material.setColour(resultSet.getString("colour"));
            material.setDensity(resultSet.getString("density"));
            material.setPrice(resultSet.getInt("price"));
            material.setMaterialName(resultSet.getString("material_name"));
            material.setType(resultSet.getString("type"));
            material.setQuantity(resultSet.getInt("quantity"));
            materials.add(material);
        }
    }

    @Override
    public void updateMaterial(Material material, int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MATERIAL)) {
            preparedStatement.setInt(1, material.getQuantity());
            preparedStatement.setString(2, material.getColour());
            //preparedStatement.setString(3, material.getDensity());
            preparedStatement.setString(3, material.getType());
            preparedStatement.setInt(4, material.getPrice());
            preparedStatement.setString(5, material.getMaterialName());
            preparedStatement.setInt(6, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
