package com.tudor.Model.ADTs;

public interface IStack<T> {
    T pop();
    void push(T newElement);
    boolean isEmpty();
    String toString();
}
