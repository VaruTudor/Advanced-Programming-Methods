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

    public Stack<T> getStack(){
        return this.stack;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for(T element : stack){
            output.append(element.toString());
        }
        return output.toString();
    }
}
