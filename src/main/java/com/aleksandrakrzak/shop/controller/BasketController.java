package com.aleksandrakrzak.shop.controller;

import com.aleksandrakrzak.shop.domain.dto.BasketDto;
import com.aleksandrakrzak.shop.mapper.BasketMapper;
import com.aleksandrakrzak.shop.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/basket")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    private final BasketMapper basketMapper;

    @DeleteMapping
    public void deleteByProductID(@RequestParam Long id) {
        basketService.deleteByProductId(id);
    }

    @PostMapping
    public BasketDto addProduct(@RequestBody BasketDto basketDto) {
        return basketMapper.basketToBasketDTO(basketService.addProduct(basketMapper.basketDTOToBasket(basketDto)));
    }

    @PutMapping
    public BasketDto updateProduct(@RequestBody BasketDto basketDto) {
        return basketMapper.basketToBasketDTO(basketService.updateProduct(basketMapper.basketDTOToBasket(basketDto)));
    }

    @GetMapping
    public List<BasketDto> getBasketByUser() {
        return basketMapper.listBasketToListBasketDTO(basketService.getBasketByUser());
    }

}