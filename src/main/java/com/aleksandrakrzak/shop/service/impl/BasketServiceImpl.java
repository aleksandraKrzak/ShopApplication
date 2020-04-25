package com.aleksandrakrzak.shop.service.impl;

import com.aleksandrakrzak.shop.domain.dao.Basket;
import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.domain.dao.User;
import com.aleksandrakrzak.shop.repository.jpa.BasketRepository;
import com.aleksandrakrzak.shop.repository.jpa.ProductRepository;
import com.aleksandrakrzak.shop.repository.jpa.UserRepository;
import com.aleksandrakrzak.shop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public void deleteByProductId(Long productId) {
        basketRepository.deleteByProductId(productId);
    } // usuwamy basket po product id

    @Override
    public Basket addProduct(Basket basket) {
        Optional<Product> optionalProduct = productRepository.findById(basket.getProduct().getId()); // musimy pobracc basketu product id i userid bo tym obiektem basketdto komunikujemy sie z uzytkownikiem; uztkownik podaje w basketdto productid i ilosc
        Optional<User> optionalUser = userRepository.findByMail(SecurityContextHolder.getContext().getAuthentication().getName());

        if (optionalUser.isPresent() && optionalProduct.isPresent()) {
            User user = optionalUser.get();
            Product product = optionalProduct.get();

            if (basket.getQuantity() <= product.getQuantity()) { // ilosc produktu w baskecie w bd jest =< od ilosci kotra chce doda uzytkownik(basketDTO.getQuantity())

                return basketRepository.save(Basket.builder()
                        .quantity(basket.getQuantity())
                        .product(product)
                        .user(user)
                        .build());
            } else {
                return basketRepository.save(Basket.builder()
                        .quantity(product.getQuantity())
                        .product(product)
                        .user(user)
                        .build());
            }
        }
        throw new EntityNotFoundException();
    }

    @Override
    public Basket updateProduct(Basket basket) {
        Optional<Basket> byUserIdAndProductId = basketRepository.findByUserMailAndProductId(SecurityContextHolder.getContext().getAuthentication().getName(), basket.getProduct().getId());

        return byUserIdAndProductId.map(basketDb -> {
            Product product = basketDb.getProduct();

            if (basketDb.getQuantity() <= product.getQuantity()) {
                basketDb.setQuantity(basketDb.getQuantity());
            } else if (basketDb.getQuantity() > product.getQuantity()) {
                basketDb.setQuantity(product.getQuantity());
            }

            return basketRepository.save(basketDb);
        }).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Basket> getBasketByUser() {
        return basketRepository.findByUserMail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
