package com.tudor.Exceptions;

public class ConditionalNotBoolean extends RuntimeException{
    @Override
    public String toString(){
        return "The condition evaluated to not BoolType";
    }
}
