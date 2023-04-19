package com.example.stores.Service;

import com.example.stores.DAO.CartDTO;
import com.example.stores.Entity.Cart;
import com.example.stores.Repository.CartRepository;
import com.example.stores.Repository.ProductRepository;
import com.example.stores.Repository.UserRep.CartRep;
import com.example.stores.Repository.UsersRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CartService implements CartRep {

    private final CartRepository cartRepository;
    private  final ProductRepository productRepository;
    private final UsersRepository usersRepository;
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
            cart.setUser(usersRepository.findUsersModelById(cartDTO.getUser_id()));
            cart.setQty(1);
            cartRepository.save(cart);
            status = 1;
        }

        return status;

    }
    @Override
    public Long countCart(Long user_id){
        return cartRepository.findCart(user_id);

    }






}
