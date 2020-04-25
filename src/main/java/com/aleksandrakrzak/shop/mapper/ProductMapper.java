package com.aleksandrakrzak.shop.mapper;

import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.domain.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductMapper {

    Product productDTOToProduct(ProductDto productDTO);

    ProductDto productToProductDTO(Product product);

    Page<ProductDto> pageProductToPageProductDTO(Page<Product> productPage);

    List<ProductDto> listProductToListProductDTO(List<Product> productList);

}
