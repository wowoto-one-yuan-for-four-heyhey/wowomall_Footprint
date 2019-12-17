package com.xmu.wowomall.footprint.service;

import com.xmu.wowomall.footprint.domain.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient("cart")
public interface CartService {

    /**
     * cadd
     * @param cartItemId
     * @return
     */
    @GetMapping("cartItems/{id}")
    public CartItem findCartItemById(@PathVariable Integer cartItemId);
}
