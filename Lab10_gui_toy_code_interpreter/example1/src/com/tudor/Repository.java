package com.tudor;

import java.util.List;

public class Repository {
    public List<Person> personList;

    public Repository(List<Person> personList) {
        this.personList = personList;
    }

    public void add(Person person){
        personList.add(person);
    }
}
