package com.tudor.Model.Expressions;

import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.Values.Value;

public class VarExpression implements Expression{
    private final String id;

    public VarExpression(String id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "@" + id;
    }

    @Override
    public Value evaluate(IDict<String, Value> symTable) {
        return symTable.lookup(id);
    }
}
