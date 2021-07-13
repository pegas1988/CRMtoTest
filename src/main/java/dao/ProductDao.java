package dao;

import entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    int productID(Product product);

    void create(Product product);

    void deleteFromProduct(Product product);
}
