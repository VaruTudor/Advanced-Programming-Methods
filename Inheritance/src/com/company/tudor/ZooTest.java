package com.company.tudor;

import org.junit.jupiter.api.Test;

public class ZooTest {

    @Test
    public void shouldFeedAnimals(){
        Zoo my_zoo = new Zoo();
        Animal[] my_animals = {new Cat(), new Dog(), new Tiger(), new Dog()};
        my_zoo.feedAnimals(my_animals);
    }

}