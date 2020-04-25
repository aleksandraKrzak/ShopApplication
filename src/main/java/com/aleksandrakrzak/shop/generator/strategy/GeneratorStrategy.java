package com.aleksandrakrzak.shop.generator.strategy;

import com.aleksandrakrzak.shop.generator.model.FileType;
import com.aleksandrakrzak.shop.repository.jpa.ProductRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GeneratorStrategy { // storzylismy te klase aby kazda implementacja tej klasy generowala rozny format pliku

    @Getter
    private FileType fileType;

    @Autowired
    protected ProductRepository productRepository;

    public GeneratorStrategy(FileType fileType) {
        this.fileType = fileType;
    }

    public abstract byte[] generateProductsFile();

}
