package com.aleksandrakrzak.shop.mapper.impl;

import com.aleksandrakrzak.shop.domain.dao.Basket;
import com.aleksandrakrzak.shop.domain.dto.BasketDto;
import com.aleksandrakrzak.shop.mapper.BasketMapper;
import com.aleksandrakrzak.shop.mapper.ProductMapper;
import com.aleksandrakrzak.shop.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BasketMapperImpl implements BasketMapper {

    private final ProductMapper productMapper;

    private final UserMapper userMapper;

    @Override
    public Basket basketDTOToBasket(BasketDto basketDTO) {
        return Basket.builder()
                .id(basketDTO.getId())
                .product(productMapper.productDTOToProduct(basketDTO.getProductDTO()))
                .user(userMapper.userDTOToUser(basketDTO.getUserDTO()))
                .quantity(basketDTO.getQuantity())
                .build();
    }

    @Override
    public BasketDto basketToBasketDTO(Basket basket) {
        return BasketDto.builder().id(basket.getId())
                .quantity(basket.getQuantity())
                .productDTO(productMapper.productToProductDTO(basket.getProduct()))
                .userDTO(userMapper.userToUserDTO(basket.getUser()))
                .build();
    }

    @Override
    public List<BasketDto> listBasketToListBasketDTO(List<Basket> basketList) {
        return basketList.stream()
                //.map(basket -> basketToBasketDTO(basket))
                .map(this::basketToBasketDTO)
                .collect(Collectors.toList());
    }

}
