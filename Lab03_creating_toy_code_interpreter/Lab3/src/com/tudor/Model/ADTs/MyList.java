package com.tudor.Model.ADTs;

import java.util.ArrayList;

public class MyList<T> implements IList<T> {
    private final ArrayList<T> list;

    public MyList() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T v) {
        list.add(v);
    }

    @Override
    public T pop() {
        return list.remove(list.size()-1);
    }
}
