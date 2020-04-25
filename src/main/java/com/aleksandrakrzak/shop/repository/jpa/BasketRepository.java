package com.aleksandrakrzak.shop.repository.jpa;

import com.aleksandrakrzak.shop.domain.dao.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    void deleteByProductId(Long productId); // usuwamy basket po productId

    List<Basket> findByUserId(Long userId);

    List<Basket> findByUserMail(String mail);

    Optional<Basket> findByUserMailAndProductId(String mail, Long productId); //spawdzamczy dla podanego userid istnieje wpis w baskecie dlapodaneo productid
    /*select * from
    user u join basket b on u.id = b.user_id
    join product p on b.product_id = p.id
    where u.id = 5 and p.id = 3;*/

}
