package com.aleksandrakrzak.shop.service;

import com.aleksandrakrzak.shop.domain.dao.ClientOrder;

import java.util.List;

public interface OrderService {

    List<ClientOrder> makeOrder();

    List<ClientOrder> getDetails(String orderNumber);

}
