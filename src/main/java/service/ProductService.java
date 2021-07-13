package service;

import dao.impl.ProductDaoImpl;
import entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    ProductDaoImpl productDaoImpl = new ProductDaoImpl();

    public List<Product> findAllProducts() {
        return productDaoImpl.findAll();
    }

    public void createProduct(Product product) {
        productDaoImpl.create(product);

    }

    public void deleteByName(Product product) {
        productDaoImpl.deleteFromProduct(product);
    }

    public int getIDByName(Product product) {
        return productDaoImpl.productID(product);
    }
}
