package edu.fu.demoshop.service;

import edu.fu.demoshop.model.Product;
import edu.fu.demoshop.model.ProductPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);
    Product getProductById(long id);
    List<Product> findAllProduct();

    List<Product> getCategoryById(long category_id);

    List<Product> getAllProductPage(int page ,int pageSize);

    Page<Product> getProductPage(ProductPage productPage);

    Product updateProduct(Product product , long id);
    Product deleteProduct(long id);

    List<Product> getProductByName(String name);

    String deleteMultiple(List<Long> ids);

    List<Product> findPaginated(int page, int pageSize);

}
