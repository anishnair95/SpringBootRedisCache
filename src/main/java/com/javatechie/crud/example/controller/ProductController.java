package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProductController {


    private final IProductService iProductService;

    @Autowired
    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        Product productData = iProductService.saveProduct(product);
        if(productData==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  productData;
    }

    @PostMapping("/bulk/products")
    public List<Product> addProducts(@RequestBody List<Product> products) {
        List<Product> productList = iProductService.saveProducts(products);

        if(productList==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  productList;
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        List<Product> productList = iProductService.getProducts();

        if(productList==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  productList;
    }

    @GetMapping("/products/{id}")
    public Product findProductById(@PathVariable int id) {
        Product productData = iProductService.getProductById(id);

        if(productData==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  productData;

    }

    @GetMapping("/products/name/{name}")
    public Product findProductByName(@PathVariable String name) {
        Product productData = iProductService.getProductByName(name);

        if(productData==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  productData;
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        Product productData = iProductService.updateProduct(product);
        if(productData==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  productData;

    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id) {
        return iProductService.deleteProduct(id);
    }
}
