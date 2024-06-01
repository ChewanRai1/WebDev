package com.system.Fashionhive.services;

import com.system.Fashionhive.entity.Wishlist;

import java.security.Principal;
import java.util.List;

public interface WishlistService {
    String saveToWishlist(Integer id, Principal principal);

    String deleteFromWishlist(Integer id);

    List<Wishlist> fetchAll(Integer id);
}
