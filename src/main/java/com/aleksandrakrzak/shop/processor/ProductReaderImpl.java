package com.aleksandrakrzak.shop.processor;

import com.aleksandrakrzak.shop.domain.dto.ProductDto;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class ProductReaderImpl {

    @StepScope // dajemy po to aby springbatch mogl odpalic readera w krokach
    public ItemReader<ProductDto> reader(String filePath) {
        BeanWrapperFieldSetMapper<ProductDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();

        beanWrapperFieldSetMapper.setTargetType(ProductDto.class);

        return new FlatFileItemReaderBuilder<ProductDto>()
                .name("reader")
                .linesToSkip(1) //w pierwszej linijce plik csv ma nazwe kolumn dlatego skipujemy
                .resource(new FileSystemResource(filePath))
                .delimited() // chodzi o tokenizer czyli separator
                .names("name", "description", "price", "quantity")
                .fieldSetMapper(beanWrapperFieldSetMapper)//implementacja FieldSetMapper<>() ktora umozliwa tworzenie pól na podstawie 23 linijki
                .build();
    }

}
