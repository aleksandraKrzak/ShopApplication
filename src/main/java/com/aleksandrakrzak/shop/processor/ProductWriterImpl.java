package com.aleksandrakrzak.shop.processor;


import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.repository.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductWriterImpl implements ItemWriter<Product> {

    private final ProductRepository productRepository;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        productRepository.saveAll(products);
    }

}
