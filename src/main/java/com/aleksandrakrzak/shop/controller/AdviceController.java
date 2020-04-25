package com.aleksandrakrzak.shop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
//jesli jakis blad zostanie wywolany w kontrolerze to ten blad zostanie przekierowany do tego kontrollera i ten kontroller sprawdzi czy oblsugujemy blad ktory wystapil
public class AdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND) // dajemy zeby zwrocic inny status niz 200, 400 i 500
    @ExceptionHandler(EntityNotFoundException.class)
    //metoda zostanie wywolana wtedy kiedy wystapi entity not found exception
    public void handleEntityNotFoundException(EntityNotFoundException e) {

    }

}
