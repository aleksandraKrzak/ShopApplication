package com.aleksandrakrzak.shop.controller;

import com.aleksandrakrzak.shop.generator.GeneratorFactory;
import com.aleksandrakrzak.shop.generator.model.FileType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final GeneratorFactory generatorFactory;

    @GetMapping
    public ResponseEntity<byte[]> generateProductsFile(FileType fileType) { //
        byte[] arrayOfFile = generatorFactory.getGeneratorByFileType(fileType).generateProductsFile();

        HttpHeaders httpHeaders = new HttpHeaders(); // tworzy headery do responsa
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE); //w odpowiedzi headerach zwracamy plik
        httpHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(arrayOfFile.length)); // warrtoscia lenghta bedzie dlugosc tablicy reperezntowana jako string
        httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=report.".concat(fileType.toString().toLowerCase()));

        return ResponseEntity.ok().headers(httpHeaders).body(arrayOfFile);
    }

}
