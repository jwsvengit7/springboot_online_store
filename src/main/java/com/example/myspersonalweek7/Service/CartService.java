package com.example.myspersonalweek7.Service;

import com.example.stores.DAO.CartDTO;
import com.example.stores.Entity.Cart;
import com.example.stores.Repository.CartRepository;
import com.example.stores.Repository.UserRep.CartRep;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CartService implements CartRep {

    private final CartRepository cartRepository;
    @Override
    public int increaseCartOrAddCart(CartDTO cartDTO){
        int status =0;
        List<Cart> existingCart = cartRepository.findQueryForCart(cartDTO.getUser_id(), cartDTO.getProduct_id());
        if (!existingCart.isEmpty()) {
            Cart cart = existingCart.get(0);
            cart.setQty(cart.getQty() + 1);
            cartRepository.save(cart);
            status = 1;
        } else {
            Cart cart = new Cart();
            cart.setProduct_id(cartDTO.getProduct_id());
            cart.setUser_id(cartDTO.getUser_id());
            cart.setQty(1);
            cartRepository.save(cart);
            status = 1;
        }

        return status;

    }
    @Override
    public long countCart(int user_id){
        return cartRepository.findCart(user_id);

    }



}
