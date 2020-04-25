package com.aleksandrakrzak.shop.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class) // entitylistener wlacza dany auditing(sprawdzacz)
public class Basket {

    @Id
    @GeneratedValue
    private Long id;
    private Double quantity;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    @CreatedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime createdDate;
    @LastModifiedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime lastModifiedDate;

}
