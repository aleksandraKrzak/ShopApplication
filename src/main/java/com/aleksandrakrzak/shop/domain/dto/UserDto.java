package com.aleksandrakrzak.shop.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
// nie zwraca nullowym wartości; jesli w id mam nullowa wartosc to json wygeneruje sie bez tego pola i danych
public class UserDto { // obiektydto przyjmuje i zwracam w controllerach a z obiektami dao tak nie wolno; dto slozy do komunikacji z userem

    private Long id;
    private String firstName;
    private String lastName;
    private String name;
    private String mail;
    private Integer age;
    private String password;

}
