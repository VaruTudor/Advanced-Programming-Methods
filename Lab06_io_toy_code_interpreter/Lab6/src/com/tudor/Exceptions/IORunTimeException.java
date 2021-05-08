package com.tudor.Exceptions;

public class IORunTimeException extends RuntimeException{
    @Override
    public String toString() {
        return "cannot open file";
    }
}
