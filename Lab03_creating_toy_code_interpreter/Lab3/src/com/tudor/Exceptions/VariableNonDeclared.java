package com.tudor.Exceptions;

public class VariableNonDeclared extends RuntimeException{
    @Override
    public String toString(){
        return "Tha variable has not been declared";
    }
}
