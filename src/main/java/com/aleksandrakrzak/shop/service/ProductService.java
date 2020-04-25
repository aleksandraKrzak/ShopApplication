package com.aleksandrakrzak.shop.service;

import com.aleksandrakrzak.shop.domain.dao.Product;
import com.aleksandrakrzak.shop.domain.dto.ProductDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    @CachePut(cacheNames = "product", key = "#result.id")
    Product save(Product productDTO);

    @CachePut(cacheNames = "product", key = "#result.id")
    Product update(Product productDTO);

    @CacheEvict(cacheNames = "product", key = "#id")
    void deleteById(Long id);

    Page<Product> loadPage(Pageable pageable);

    void processFile(MultipartFile multipartFile);

    @Cacheable(cacheNames = "product", key = "#id")
    Product getById(Long id);

}
