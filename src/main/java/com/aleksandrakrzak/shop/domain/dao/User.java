package com.aleksandrakrzak.shop.domain.dao;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class) // entitylistener wlacza dany auditing(sprawdzacz)
public class User { // reprezentacja tabelki bazodanowej; do komunikacji z repozytorium

    @Id
    @GeneratedValue // autoinkrementacja
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true) // daje nam to ze wartosc w tej kolumnie nie moze sie powtorzyc
    private String name;
    @Column(unique = true)
    private String mail;
    private Integer age;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
// dajemy zeby miec polaczenie wiele do wielu; fetch: ma dwie wartosci lazy i eager. jak mam eager to gdy pobieram usera to pobieram jego relacje
    @JoinTable// dajemy po to aby sie wygenerowala tabela laczaca
    private List<Role> role;

    @CreatedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime createdDate;
    @LastModifiedDate // dziala kiedy damy w adnotacji (AuditingEntityListener.class
    private LocalDateTime lastModifiedDate;

}
