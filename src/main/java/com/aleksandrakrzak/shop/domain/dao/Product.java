package com.aleksandrakrzak.shop.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class) // entitylistener wlacza dany auditing(sprawdzacz)
public class Product implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double quantity;

    @CreatedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime createdDate;
    @LastModifiedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime lastModifiedDate;

}
