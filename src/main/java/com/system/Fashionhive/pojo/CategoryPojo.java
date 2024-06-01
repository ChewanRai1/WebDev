package com.system.Fashionhive.pojo;


import com.system.Fashionhive.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPojo {

    private Integer id;

    private String category_name;

    public CategoryPojo(Category category){
        this.id=category.getId();
        this.category_name=category.getCategory_name();
    }
}
