package com.tudor.Model.Expressions;

import com.tudor.Exceptions.TypeExceptions.TypeMismatch;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IHeap;
import com.tudor.Model.Types.BoolType;
import com.tudor.Model.Types.Type;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.Value;

public class NotExpression implements Expression{
    Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value evaluate(IDict<String, Value> symTable, IHeap<Integer, Value> heap) {
        Value value;
        value = expression.evaluate(symTable, heap);


        if(value.getType().equals(new BoolType() )){
            BoolValue boolValue = (BoolValue) value;
            return new BoolValue(!boolValue.getValue());
        }else{
            throw new RuntimeException("Value not bool!");
        }
    }

    @Override
    public Type typecheck(IDict<String, Type> typeEnvironment) {
        Type typeExpression ;
        typeExpression = expression.typecheck(typeEnvironment);

        if(typeExpression.equals(new BoolType())){
            return new BoolType();
        }else{
            throw new TypeMismatch();
        }

    }

    @Override
    public String toString() {
        return "! " + expression.toString() + ";";
        }
}

