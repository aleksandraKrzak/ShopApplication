package com.aleksandrakrzak.shop.mapper;

import com.aleksandrakrzak.shop.domain.dao.Basket;
import com.aleksandrakrzak.shop.domain.dto.BasketDto;

import java.util.List;

public interface BasketMapper {

    Basket basketDTOToBasket(BasketDto basketDTO);

    BasketDto basketToBasketDTO(Basket basket);

    List<BasketDto> listBasketToListBasketDTO(List<Basket> basketList);

}
