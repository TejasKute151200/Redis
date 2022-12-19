package com.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redis.entity.Product;
import com.redis.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {
	
    @Autowired
    private ProductRepo productRepo;

    @PostMapping("/")
    public Product save(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @GetMapping("/{id}")
    public Product findProduct(@PathVariable Long id) {
        return productRepo.findProductById(id);
    }
    @DeleteMapping("/{id}")
    public String remove(@PathVariable Long id)   {
    	return productRepo.deleteProduct(id);
	}
}