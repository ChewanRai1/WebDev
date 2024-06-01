package com.system.Fashionhive.services.impl;

import com.system.Fashionhive.entity.User;
import com.system.Fashionhive.exception.AppException;
import com.system.Fashionhive.pojo.UserPojo;
import com.system.Fashionhive.repo.UserRepo;
import com.system.Fashionhive.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public void saveUser(UserPojo userPojo) {
        User user= new User();
        user.setEmail(userPojo.getEmail());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userPojo.getPassword());
        user.setPassword(encodedPassword);

        userRepo.save(user);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));

        //builder

        return user;
    }

    @Override
    public User findUserById(Integer user_id) {
        Optional<User> optionalUser = userRepo.findById(user_id);
        return optionalUser.orElse(null);
    }
}
