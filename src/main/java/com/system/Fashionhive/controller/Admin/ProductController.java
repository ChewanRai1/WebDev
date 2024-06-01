package com.system.Fashionhive.controller.Admin;

import com.system.Fashionhive.entity.Category;
import com.system.Fashionhive.pojo.ProductPojo;
import com.system.Fashionhive.services.CategoryService;
import com.system.Fashionhive.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/addproduct")
    public String getaddProduct(Model model){

        List<Category> categories = categoryService.fetchAll();
        model.addAttribute("categories",categories);
        return "Admin/add_product";

    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid ProductPojo productPojo) throws Exception {
        productService.saveProduct(productPojo);
        return "redirect:/admin/addproduct";
    }
}
