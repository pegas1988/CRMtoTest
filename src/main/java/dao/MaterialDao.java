package dao;

import entity.Material;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MaterialDao {
    void createNewMaterial(Material material);

    void deleteFromMaterialByName(Material material);

    int materialID(Material material);

    List<Material> findAll();

    List<Material> findByType(String type);

    void makeMaterial(List<Material> materials, ResultSet resultSet) throws SQLException;

    void updateMaterial(Material material, int id);
}
