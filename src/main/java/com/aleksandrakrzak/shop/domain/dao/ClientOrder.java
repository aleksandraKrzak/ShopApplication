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

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // entitylistener wlacza dany auditing(sprawdzacz)
public class ClientOrder {

    @Id
    @GeneratedValue
    private Long id;
    private String orderNumber;
    private Double quantity;

    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;

    @CreatedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime createdDate;
    @LastModifiedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime lastModifiedDate;

}
