package edu.fu.demoshop.service.impl;

import edu.fu.demoshop.model.Product;
import edu.fu.demoshop.model.ProductPage;
import edu.fu.demoshop.exception.ResourceNotFoundException;
import edu.fu.demoshop.reponsitory.ProductRepository;
import edu.fu.demoshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(long id) {
        Product pd = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Prodcut", "Id", id));

        boolean checkRole = pd.isRuleDrop();
        if (checkRole){
            return pd;
        }else {
            System.out.println("San Pham da bi xoa!");
           throw new ResourceNotFoundException("Prodcut: ", "Id", id);
        }
    }

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAllProduct();
    }

    @Override
    public List<Product> getCategoryById(long category_id) {
        List<Product> category = productRepository.findAllProductByCategoryId(category_id);
        return category;
    }

    @Override
    public List<Product> getAllProductPage(int page, int pageSize) {
        return productRepository.getAllProductPage(page , pageSize);
    }

    @Override
    public Page<Product> getProductPage(ProductPage productPage){
        Sort sort = Sort.by(productPage.getSortDirection(), productPage.getSortBy());
        Pageable pageable = PageRequest.of(productPage.getPageNumber(), productPage.getPageSize(), sort);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProduct(Product product, long id) {
        Product pd = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "Id", id));
        pd.setDescription(product.getDescription());
        pd.setImage(product.getImage());
        pd.setName(product.getName());
        pd.setQuantity(product.getQuantity());
        productRepository.save(pd);
        return pd;
    }

    @Override
    public Product deleteProduct(long id) {
        Product pd = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product", "Id", id));
        boolean checkRole = pd.isRuleDrop();
        if (checkRole){
            pd.setRuleDrop(false);
            productRepository.save(pd);
            return pd;
        }else {
            System.out.println("San Pham da bi xoa!");
            throw new ResourceNotFoundException("Prodcut: ", "Id", id);
        }
    }

    @Override
    public List<Product> getProductByName(String name) {
       return productRepository.search(name);
    }


    @Override
    public String deleteMultiple(List<Long> ids) {
        System.out.println(ids.get(0));
        for (long item : ids) {
            Product pd = productRepository.findById(item).orElseThrow(
                    () -> new ResourceNotFoundException("Product", "Id", item));
            pd.setRuleDrop(false);
            productRepository.save(pd);
        }
        return "done!";
    }

    @Override
    public List<Product> findPaginated(int page, int pageSize) {
        Pageable paging = PageRequest.of(page , pageSize);
        Page<Product> productPage = productRepository.findAll(paging);
        int totalPage = productPage.getTotalPages();
        return productPage.toList();
    }
}
