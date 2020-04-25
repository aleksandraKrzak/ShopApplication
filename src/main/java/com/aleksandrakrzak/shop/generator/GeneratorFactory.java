package com.aleksandrakrzak.shop.generator;

import com.aleksandrakrzak.shop.generator.model.FileType;
import com.aleksandrakrzak.shop.generator.strategy.GeneratorStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GeneratorFactory { //wstrzykuje wszystkie implemntacje gnerator  strategy

    private final List<GeneratorStrategy> generatorStrategyList;

    private Map<FileType, GeneratorStrategy> fileTypeGeneratorStrategyMap;

    @PostConstruct
    public void init() {
        fileTypeGeneratorStrategyMap = generatorStrategyList.stream()
                .collect(Collectors.toMap(GeneratorStrategy::getFileType, element -> element));
    }

    public GeneratorStrategy getGeneratorByFileType(FileType fileType) {
        return fileTypeGeneratorStrategyMap.get(fileType);
    }

}
