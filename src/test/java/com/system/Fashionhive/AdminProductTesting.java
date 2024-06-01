package com.system.Fashionhive;
//package com.example.annna_bazar;

//import com.example.annna_bazar.entity.AdminPage;
//import com.example.annna_bazar.entity.User;
//import com.example.annna_bazar.repo.AdminPageRepo;
//import com.example.annna_bazar.services.AdminPageService;
import com.system.Fashionhive.entity.Product;
import com.system.Fashionhive.repo.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminProductTesting {
    @Autowired
    private ProductRepo productRepo;

    @Test

    @Order(1)

    @Rollback(value = false)

    public void saveProductTest(){

        Product product = Product.builder()

                .product_name("tomato")
                .product_price(100)
                .product_quantity(4)
                .product_description("A good tomato")
                .product_size("XL")
                .product_color("Blue")
                .product_image("Not available")

                .build();

        productRepo.save(product);
        Assertions.assertThat(product.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public  void getProductTest(){
        Product prodsent= productRepo.findById(1).get();
        Assertions.assertThat(prodsent.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void getListOfProductTest(){
        List<Product> prod = productRepo.findAll();
        Assertions.assertThat(prod.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateProductPriceTest(){
        Product prod = productRepo.findById(1).get();
        prod.setProduct_price(123);
        Product productUpdated =  productRepo.save(prod);
        Assertions.assertThat(productUpdated.getProduct_price()).isEqualTo(123);
    }
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteProductTest(){
        Product prod = productRepo.findById(1).get();
        productRepo.delete(prod);

        Product prod1 = null;
        Optional<Product> isMsg = productRepo.findById(1);
        if(isMsg.isPresent()){
            prod1 = isMsg.get();
        }
        Assertions.assertThat(prod1).isNull();
    }
}