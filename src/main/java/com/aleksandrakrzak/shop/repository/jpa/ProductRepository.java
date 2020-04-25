package com.aleksandrakrzak.shop.repository.jpa;

import com.aleksandrakrzak.shop.domain.dao.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
