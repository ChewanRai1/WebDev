package com.system.Fashionhive.controller;

import com.system.Fashionhive.entity.Cart;
import com.system.Fashionhive.pojo.CartPojo;
import com.system.Fashionhive.services.CartService;
import com.system.Fashionhive.services.EmailService;
import com.system.Fashionhive.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class CheckoutController {
    private final UserService userService;
    private final CartService cartService;
    private final EmailService emailService;

    @GetMapping("/checkout")
    public String displayCart(Principal principal, Model model){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<Cart> list = cartService.fetchAll(id);

        double total = 0.0;
        for(Cart totalCalc:list){
            if (totalCalc.getProduct().getProduct_quantity()>0) total += totalCalc.getQuantity()*totalCalc.getProduct().getProduct_price();
        }

        model.addAttribute("total", total);
        model.addAttribute("cartItems", list);
        model.addAttribute("checkout", new CartPojo());

        return "/checkout";
    }

    @PostMapping("/checkout/confirm")
    public String checkoutProcess(Principal principal, @Valid CartPojo pojo){
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<Cart> list = cartService.fetchAvailable(id);

        for (Cart cartItem: list){
            System.out.println("cartItem: "+cartItem.getProduct().getProduct_quantity());
//            cartService.updateProduct(cartItem.getProduct().getProduct_quantity()-cartItem.getQuantity(),cartItem.getProduct().getId());
        }

        for (Cart cartItem: list){
            System.out.println("cartItem: "+cartItem.getProduct().getProduct_quantity());
            cartService.updateProduct(cartItem.getProduct().getProduct_quantity()-cartItem.getQuantity(),cartItem.getProduct().getId());
        }
        sendEmail(principal);
        cartService.checkout(id, pojo, list);
        return "redirect:/dashboard";
    }

    @Async
    void sendEmail(Principal principal) {
        try {


            String to = principal.getName();
            System.out.println("email: "+to);
            String subject = "Order Confirmation";
            String text = "Your order has been Received. \n \n We will contact you soon. \nThank you for shopping with us. \n\n Regards, \n FashionHive";
            if (emailService == null) System.out.println("emailService is null");
            else {
                emailService.sendEmail(to, subject, text);}}
        catch (Exception e) {
            System.out.println("error in sending email: "+e);
        }
    }

}
