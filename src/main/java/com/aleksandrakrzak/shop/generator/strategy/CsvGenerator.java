package com.aleksandrakrzak.shop.generator.strategy;

import com.aleksandrakrzak.shop.generator.model.FileType;

public class CsvGenerator extends GeneratorStrategy {

    public CsvGenerator() {
        super(FileType.CSV);
    }

    @Override
    public byte[] generateProductsFile() {
        return new byte[0];
    }

}
