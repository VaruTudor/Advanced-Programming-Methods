package com.tudor.Model.Expressions;

import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.Values.Value;

public interface Expression {
    Value evaluate(IDict<String, Value> symTable) ;
}
