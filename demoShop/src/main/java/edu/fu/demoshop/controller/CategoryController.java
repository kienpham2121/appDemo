package edu.fu.demoshop.controller;

import edu.fu.demoshop.model.Product;
import edu.fu.demoshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    @Autowired
    IProductService productService;

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> getCategoryById(@PathVariable(value = "id") Long id) {
        List<Product> category = productService.getCategoryById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(category);
    }
}
