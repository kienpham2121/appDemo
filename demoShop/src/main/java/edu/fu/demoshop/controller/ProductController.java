package edu.fu.demoshop.controller;

import edu.fu.demoshop.model.ListId;
import edu.fu.demoshop.model.Product;
import edu.fu.demoshop.model.ProductPage;
import edu.fu.demoshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin/api/v1/")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    @PostMapping("/products")
    public  ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
        return new ResponseEntity<Product>(iProductService.saveProduct(product), HttpStatus.CREATED);
    }

    //get du lieu va phan trang
//    @GetMapping("/products/{page}/{pageSize}")
//    public List<Product> getProductPage(@PathVariable int page ,
//                                        @PathVariable int pageSize
//                                        ){
//        return iProductService.findPaginated(page , pageSize);
//    }

    @GetMapping("/products/page")
    public ResponseEntity<Page<Product>> getProductPage(ProductPage productPage){
        return new ResponseEntity<>(iProductService.getProductPage(productPage), HttpStatus.OK);
    }
    @GetMapping("/vaccines")
    public ResponseEntity<List<Product>> getAllVaccine(@RequestParam(defaultValue = "") int page,
                                                       @RequestParam(defaultValue = "") int pageSize) {
        List<Product> product = iProductService.getAllProductPage(page , pageSize);
        if (product.isEmpty()) {
            return new ResponseEntity<List<Product>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Product>>(product, HttpStatus.OK);
    }


    // lay du du lieu
    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return iProductService.findAllProduct();
    }



    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getEmployeeById(@PathVariable("id") long id){
        Product product = iProductService.getProductById(id);
        if (product == null){
            return (ResponseEntity<Product>) ResponseEntity.notFound();
        }
        return new ResponseEntity<Product>(iProductService.getProductById(id) , HttpStatus.OK);

//        return new ResponseEntity<Product>(iProductService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/products/category/{category_id}")
    public List<Product> getCategoryById(@PathVariable("category_id") long category_id){
          return iProductService.getCategoryById(category_id);
    }

    // sua san pham
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id
                                                 ,@Valid @RequestBody Product product){
        return new ResponseEntity<Product>(iProductService.updateProduct(product , id), HttpStatus.OK);
    }

    // xoa theo id
    @DeleteMapping ("/products/{id}")
    public ResponseEntity<Product> updateEmployee(@PathVariable("id") long id){
        return new ResponseEntity<Product>(iProductService.deleteProduct(id), HttpStatus.OK);
    }
//    Xoa nhieu truong

    @PostMapping("/products/munti")
    public String deleteMunti(@RequestBody ListId ids){
        return iProductService.deleteMultiple(ids.getIds());
    }

    //tim kiem theo ten
    @GetMapping("/products/search")
    public List<Product> getProductByName(@RequestParam(defaultValue = "") String nameProduct){
        List<Product> products = iProductService.getProductByName(nameProduct);
        return products;
    }

//    @GetMapping("/products/page")
//    public ResponseEntity<Page<Product>> getProducts(ProductPage productPage){
//        return new ResponseEntity<>(iProductService.getProducts(productPage), HttpStatus.OK);
//    }

}


