package com.OnlineOrderSystem.SOOMS.Controller;

import com.OnlineOrderSystem.SOOMS.dto.AddCartRequest;
import com.OnlineOrderSystem.SOOMS.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping
    public ResponseEntity<String> AddToCart(@AuthenticationPrincipal User user, @RequestBody AddCartRequest addCartRequest){
        return cartService.addtoCart(user,addCartRequest);

    }
}
