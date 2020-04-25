package com.aleksandrakrzak.shop.mapper;

import com.aleksandrakrzak.shop.domain.dto.ClientOrderDto;
import com.aleksandrakrzak.shop.domain.elastic.OrderContent;

public interface OrderMapper {
    //mapuje z listyordercontent na listeorderdto

    ClientOrderDto orderToOrderDto(OrderContent clientOrder);

}
//kafka-server-start.bat server.properties
//zookeeper-server-start.bat zookeeper.properties