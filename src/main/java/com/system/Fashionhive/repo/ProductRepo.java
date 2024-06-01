package com.system.Fashionhive.repo;

import com.system.Fashionhive.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.beans.Transient;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM products WHERE product_category = ?1", nativeQuery = true)
    List<Product> findByProduct_category(Integer category);

    @Query(value = "SELECT * FROM products ORDER BY id DESC LIMIT 4", nativeQuery = true)
    List<Product> findNew();

    @Modifying
    @Query(value = "UPDATE products set product_quantity = ?1 where id = ?2", nativeQuery = true)
    void updateQuantity(double newQuantity, Integer id);

}
