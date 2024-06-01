package com.system.Fashionhive.services;

import com.system.Fashionhive.entity.Product;
import com.system.Fashionhive.pojo.ProductPojo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductService {

    String saveProduct(ProductPojo productPojo) throws IOException;
    List<Product> fetchAll();
    Product getSingle(Integer id);
    List<Product> fetchByCategory(Integer id);
    List<Product> fetchNew();
    List<Product> trending();
    List<Product> mostPopular();
    List<Product> bestSeller();
    Map<Integer, Double> comparePrice(List<Product> products);
}
