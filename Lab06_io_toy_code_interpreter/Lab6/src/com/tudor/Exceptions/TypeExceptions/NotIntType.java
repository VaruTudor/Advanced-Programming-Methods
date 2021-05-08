package com.tudor.Exceptions.TypeExceptions;

public class NotIntType extends RuntimeException{
    @Override
    public String toString() {
        return "The condition evaluated to not IntType";
    }
}
