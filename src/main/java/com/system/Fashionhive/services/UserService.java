package com.system.Fashionhive.services;

import com.system.Fashionhive.entity.User;
import com.system.Fashionhive.pojo.UserPojo;

public interface UserService {
    void saveUser(UserPojo userPojo);
    User findByEmail(String email);

    User findUserById(Integer user_id);
}
