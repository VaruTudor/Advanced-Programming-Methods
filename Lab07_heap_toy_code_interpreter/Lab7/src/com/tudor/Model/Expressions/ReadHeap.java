package com.tudor.Model.Expressions;

import com.tudor.Exceptions.TypeExceptions.TypeMismatch;
import com.tudor.Exceptions.VariableNotDeclared;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IHeap;
import com.tudor.Model.Values.RefValue;
import com.tudor.Model.Values.Value;

public class ReadHeap implements Expression {
    Expression expression;

    public ReadHeap(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(IDict<String, Value> symTable, IHeap<Integer, Value> heap) {

        Value result = expression.evaluate(symTable, heap);
        if (result instanceof RefValue){
            int address = ((RefValue) result).getAddress();
            if (heap.isDefined(address)){
                return heap.lookup(address);
            }else{
                throw new VariableNotDeclared();
            }
        }else{
            throw new TypeMismatch();
        }

    }

    @Override
    public String toString() {
        return "rH(" + expression + ')';
    }
}
