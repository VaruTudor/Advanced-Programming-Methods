package com.tudor.Exceptions;

public class TypeMismatch extends RuntimeException{
    @Override
    public String toString(){
        return "Tha types don't match";
    }
}
