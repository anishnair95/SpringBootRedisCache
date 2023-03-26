package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Product;

import java.util.List;

public interface IProductService {

    public Product saveProduct(Product product);

    public List<Product> saveProducts(List<Product> products);

    public List<Product> getProducts();

    public Product getProductById(int id);

    public Product getProductByName(String name);

    public String deleteProduct(int id);

    public Product updateProduct(Product product);

}

