package com.aleksandrakrzak.shop.generator.strategy;

import com.aleksandrakrzak.shop.generator.model.FileType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonGenerator extends GeneratorStrategy {

    @Autowired
    private ObjectMapper objectMapper; // do parsowania i rozparsowywania jsonow

    public JsonGenerator() {
        super(FileType.JSON);
    }

    @Override
    public byte[] generateProductsFile() {
        try {
            return objectMapper.writeValueAsBytes(productRepository.findAll()); //tworze tbalice bytow ktora jest reprezntacja pliku jsnoowego
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }
        return new byte[0];
    }

}
