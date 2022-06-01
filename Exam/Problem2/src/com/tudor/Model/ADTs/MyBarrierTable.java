package com.tudor.Model.ADTs;

import java.util.HashMap;
import java.util.Map;

public class MyBarrierTable<T1,T2> implements IBarrierTable<T1,T2>{
    protected HashMap<T1,T2> dict;
    private int nextFreeLocation;

    public MyBarrierTable() {
        this.dict = new HashMap<>();
        nextFreeLocation=1;
    }

    public int getNextFreeLocation() {
        return nextFreeLocation;
    }

    @Override
    public void add(T1 v1, T2 v2) {
        synchronized (dict){
            dict.put(v1,v2);
            nextFreeLocation++;
        }
    }

    @Override
    public void update(T1 v1, T2 v2) {
        synchronized (dict){
            dict.replace(v1, v2);
        }
    }

    @Override
    public T2 lookup(T1 id) {
        synchronized (dict){
            return dict.get(id);
        }
    }

    @Override
    public boolean isDefined(T1 id) {
        synchronized (dict){
            return dict.containsKey(id);
        }
    }

    @Override
    public Map<T1, T2> getContent() {
        return dict;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for (Map.Entry<T1, T2> entry : dict.entrySet()) {
            T1 key = entry.getKey();
            output.append(key.toString());
            output.append("=");
            T2 value = entry.getValue();
            output.append(value.toString());
            output.append("  ");
        }
        return output.toString();
    }

}
