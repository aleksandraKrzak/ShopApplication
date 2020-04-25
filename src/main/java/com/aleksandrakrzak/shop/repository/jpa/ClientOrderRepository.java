package com.aleksandrakrzak.shop.repository.jpa;

import com.aleksandrakrzak.shop.domain.dao.ClientOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

}
