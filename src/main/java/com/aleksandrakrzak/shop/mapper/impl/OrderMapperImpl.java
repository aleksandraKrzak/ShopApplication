package com.aleksandrakrzak.shop.mapper.impl;

import com.aleksandrakrzak.shop.domain.dto.ClientOrderDto;
import com.aleksandrakrzak.shop.domain.elastic.OrderContent;
import com.aleksandrakrzak.shop.mapper.OrderMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public ClientOrderDto orderToOrderDto(OrderContent clientOrder) {
        return null;
    }

}
