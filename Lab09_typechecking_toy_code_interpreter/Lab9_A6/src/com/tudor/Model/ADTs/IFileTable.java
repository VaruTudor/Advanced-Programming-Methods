package com.tudor.Model.ADTs;

public interface IFileTable<T1,T2> {
    void add(T1 v1, T2 v2);
    void remove(T1 v1);
    T2 lookup(T1 id);
    boolean isDefined(T1 id);
}
