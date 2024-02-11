package com.example.springproject4.exception.product;

public class AddProductException extends RuntimeException{
    public AddProductException(String message){
        super(message);
    }
}
