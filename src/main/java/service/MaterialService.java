package service;

import dao.impl.MaterialDaoImpl;
import entity.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {
    MaterialDaoImpl materialDao = new MaterialDaoImpl();

    public void createMaterial(Material material) {
        materialDao.createNewMaterial(material);
    }

    public void deleteFromMaterial(Material material) {
        materialDao.deleteFromMaterialByName(material);
    }

    public List<Material> findAllMaterials() {
        return materialDao.findAll();
    }

    public List<Material> findByType(String type) {
        return materialDao.findByType(type);
    }

    public void updateMaterial(Material material, int id) {
        materialDao.updateMaterial(material, id);
    }

    public int getMaterialIDByName(Material material) {
        return materialDao.materialID(material);
    }
}
