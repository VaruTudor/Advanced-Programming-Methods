package com.company.tudor;

public class Tiger extends Animal implements Printable{

    public void print(){
        System.out.println("printing...");
    }

    // overwriting an abstract method
    public void eat(){
        System.out.println("Tiger is eating...");
    }

    // overwriting an already-implemented method
    public void age(){
        System.out.println("Tiger is fancier and doesn't want his age to be known by others");
    }
}
