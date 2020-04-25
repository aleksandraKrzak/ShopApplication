package com.aleksandrakrzak.shop.service;

import com.aleksandrakrzak.shop.domain.dao.Basket;

import java.util.List;

public interface BasketService {

    void deleteByProductId(Long productId);

    Basket addProduct(Basket basket);

    Basket updateProduct(Basket basket);

    List<Basket> getBasketByUser(); // usuwamy parametr bo bedziemy go usuwac z security contextu

}
