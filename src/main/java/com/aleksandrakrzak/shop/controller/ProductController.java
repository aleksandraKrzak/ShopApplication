package com.aleksandrakrzak.shop.controller;

import com.aleksandrakrzak.shop.domain.dto.ProductDto;
import com.aleksandrakrzak.shop.mapper.ProductMapper;
import com.aleksandrakrzak.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/product") // dodanie prefixu do controllera. api - application programing interface
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductMapper productMapper;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        return productMapper.productToProductDTO(productService.save(productMapper.productDTOToProduct(productDto)));
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productMapper.productToProductDTO(productService.update(productMapper.productDTOToProduct(productDto)));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@RequestParam Long id) {
        productService.deleteById(id);
    }

    @GetMapping
    public Page<ProductDto> loadProductPage(@RequestParam Integer page, @RequestParam Integer size) {
        return productService.loadPage(PageRequest.of(page, size))
                .map(productMapper::productToProductDTO);
    }

    @PostMapping("/file")
    public void processFile(@RequestParam MultipartFile multipartFile) {
        productService.processFile(multipartFile);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productMapper.productToProductDTO(productService.getById(id));
    }

}
