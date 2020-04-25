package com.aleksandrakrzak.shop.service.impl;

import com.aleksandrakrzak.shop.domain.dao.Basket;
import com.aleksandrakrzak.shop.domain.dao.ClientOrder;
import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.domain.elastic.OrderContent;
import com.aleksandrakrzak.shop.repository.elastic.OrderContentRepository;
import com.aleksandrakrzak.shop.repository.jpa.BasketRepository;
import com.aleksandrakrzak.shop.repository.jpa.ClientOrderRepository;
import com.aleksandrakrzak.shop.repository.jpa.ProductRepository;
import com.aleksandrakrzak.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ClientOrderRepository clientOrderRepository;

    private final OrderContentRepository orderContentRepository;

    private final BasketRepository basketRepository;

    private final ProductRepository productRepository;

    @Override
    public List<ClientOrder> makeOrder() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Basket> basket = basketRepository.findByUserMail(email);

        String orderNumber = UUID.randomUUID().toString(); // generowanie losowego ordernumberu

        List<ClientOrder> orders = basket.stream()
                .filter(entry -> {
                    if (entry.getQuantity() <= entry.getProduct().getQuantity()) {
                        Product product = entry.getProduct();
                        product.setQuantity(product.getQuantity() - entry.getQuantity());
                        productRepository.save(product);
                        return true;
                    } else if (entry.getQuantity() > entry.getProduct().getQuantity()) {
                        Product product = entry.getProduct();
                        entry.setQuantity(product.getQuantity());
                        product.setQuantity(0D);
                        productRepository.save(product);
                        return true;
                    }
                    return false;
                })
                .map(entry -> ClientOrder.builder()
                        .orderNumber(orderNumber)
                        .product(entry.getProduct())
                        .user(entry.getUser())
                        .quantity(entry.getQuantity())
                        .build())
                .collect(Collectors.toList());

        orders = clientOrderRepository.saveAll(orders);

        List<OrderContent> ordersContent = orders.stream()
                .map(order -> new OrderContent(null, orderNumber, order.getProduct().getName(), order.getProduct().getDescription(), order.getProduct().getPrice().doubleValue(), order.getQuantity()))
                .collect(Collectors.toList()); //te ordery zapisujemy do elastica
        orderContentRepository.saveAll(ordersContent);
        return orders;
//        return StreamSupport.stream(orderContentRepository.saveAll(ordersContent).spliterator(), false)
//                .map(orderMapper::orderToOrderDto)
//                .collect(Collectors.toList());
    }

    @Override
    public List<ClientOrder> getDetails(String orderNumber) {
        return null;
    }

}
