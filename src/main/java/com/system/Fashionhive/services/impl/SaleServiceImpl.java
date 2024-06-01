package com.system.Fashionhive.services.impl;

import com.system.Fashionhive.entity.Product;
import com.system.Fashionhive.entity.Sale;
import com.system.Fashionhive.repo.SaleRepo;
import com.system.Fashionhive.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.system.Fashionhive.services.impl.ProductServiceImpl.getImageBase64;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepo saleRepo;
    @Override
    public List<Sale> getSales() {
        List<Sale> sales = saleRepo.saleProducts();
        for (Sale sale : sales) {
            sale.setProduct(
                    Product.builder()
                            .id(sale.getProduct().getId())
                            .product_size(sale.getProduct().getProduct_size())
                            .product_quantity(sale.getProduct().getProduct_quantity())
                            .product_category(sale.getProduct().getProduct_category())
                            .product_image(getImageBase64(sale.getProduct().getProduct_image()))
                            .product_color(sale.getProduct().getProduct_color())
                            .product_name(sale.getProduct().getProduct_name())
                            .product_description(sale.getProduct().getProduct_description())
                            .product_price(sale.getProduct().getProduct_price())
                            .build()
            );
        }
        return sales;
    }
}
