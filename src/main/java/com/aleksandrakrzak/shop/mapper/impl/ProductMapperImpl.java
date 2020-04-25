package com.aleksandrakrzak.shop.mapper.impl;

import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.domain.dto.ProductDto;
import com.aleksandrakrzak.shop.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component // nie mam logiki binzesowej
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productDTOToProduct(ProductDto productDTO) {
        return Product.builder()
                .description(productDTO.getDescription())
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .build();
    }

    @Override
    public ProductDto productToProductDTO(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public Page<ProductDto> pageProductToPageProductDTO(Page<Product> productPage) {
        //1. pobieram content z productpage i zwraca list<Product>
        //productPage.getPageable() - pobieranie paeable
        //productPage.getTotalElements() - pobieranie ilosci elelmentow z zapytania bazodanowego
        return new PageImpl<>(listProductToListProductDTO(productPage.getContent()), productPage.getPageable(), productPage.getTotalElements());
    }

    @Override
    public List<ProductDto> listProductToListProductDTO(List<Product> productList) {
        return productList.stream()
                //.map(product -> this.productToProductDTO(product))
                .map(this::productToProductDTO) // jezeli wywoluje w metodzie referencyjnej metode z klasy w ktorej jestem to daje this::nazwametody
                .collect(Collectors.toList());
    }

}
