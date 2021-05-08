package com.tudor.Model.ADTs;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {
    private final Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    @Override
    public T pop() {
        return stack.pop();
    }
    @Override
    public void push(T newElement) {
        stack.push(newElement);
    }
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public IStack<T> getStack(){
        return this;
    }

    @Override
    public String toString(){
        String allElements = "";
        for(T element : stack){
            allElements += element.toString();
        }
        return allElements;
    }
}
