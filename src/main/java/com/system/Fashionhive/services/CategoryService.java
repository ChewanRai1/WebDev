package com.system.Fashionhive.services;

import com.system.Fashionhive.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> fetchAll();
    Optional<Category> findById(Integer id);
}
