package com.aleksandrakrzak.shop.processor;

import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.domain.dto.ProductDto;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductProcessorImpl implements ItemProcessor<ProductDto, Product> {

    @Override
    public Product process(ProductDto productDto) throws Exception {
        return Product.builder()
                .description(productDto.getDescription())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .build();
    }

}
