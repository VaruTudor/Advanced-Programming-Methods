package com.company.tudor;

public class Zoo {

    public void feedAnimals(Animal[] all_animals){
        for(Animal a : all_animals){
            a.eat();
            a.age();
        }
    }
}
