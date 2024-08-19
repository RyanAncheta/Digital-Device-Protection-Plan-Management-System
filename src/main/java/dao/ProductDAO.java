package dao;

import model.Product;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product);
    void updateProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(int productId);
	void deleteProduct(int productId);
}
