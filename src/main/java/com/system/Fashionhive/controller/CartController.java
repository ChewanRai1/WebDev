package com.system.Fashionhive.controller;

import com.system.Fashionhive.entity.Cart;
import com.system.Fashionhive.pojo.CartPojo;
import com.system.Fashionhive.services.CartService;
import com.system.Fashionhive.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    private final UserService userService;

    @GetMapping()
    public String displayCart(Principal principal, Model model, CartPojo cartPojo){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<Cart> list = cartService.fetchAll(id);

        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cart", cartPojo);
        model.addAttribute("cartItems", list);

        return "/cart";
    }

    @GetMapping("/add/{id}")
    public String saveToCart(@PathVariable Integer id, Principal principal){
        cartService.saveToCart(id, principal);
        return "redirect:/shop";
    }

    @PostMapping("/updateQuantity/{id}")
    public String updateQuantity(@Valid CartPojo cartPojo){
        Cart cart = cartService.fetchOne(cartPojo.getId());
        cart.setQuantity(cartPojo.getQuantity());
        cartService.updateQuantity(cart);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String deleteCartItem(@PathVariable("id") Integer id){
        cartService.deleteFromCart(id);
        return "redirect:/cart";
    }
}
