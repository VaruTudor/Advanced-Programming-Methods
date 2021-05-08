package com.tudor.Exceptions.TypeExceptions;

public class NotBoolType extends RuntimeException{
    @Override
    public String toString(){
        return "The condition evaluated to not BoolType";
    }
}
