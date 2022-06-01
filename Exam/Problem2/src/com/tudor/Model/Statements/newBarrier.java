package com.tudor.Model.Statements;

import com.tudor.Exceptions.TypeExceptions.ConditionNotBoolType;
import com.tudor.Model.ADTs.IBarrierTable;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IHeap;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.IntType;
import com.tudor.Model.Types.Type;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.Value;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class newBarrier implements Statement{
    String variableId;
    Expression expression;

    public newBarrier(String variableId, Expression expression) {
        this.variableId = variableId;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDict<String, Value> symbolTable = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();
        IBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();


        Value condition = expression.evaluate(symbolTable,heap);
        if (!condition.getType().equals(new IntType())){
            throw new RuntimeException("result is not int\n");
        }

        int nextFreeLocation = barrierTable.getNextFreeLocation();
        IntValue nr = (IntValue)condition;

        barrierTable.add(
                nr.getValue(),
                new Pair<>(nextFreeLocation, new ArrayList<>())
        );

        // if var exists in SymTable1 and has type int
        if(symbolTable.isDefined(variableId) && symbolTable.lookup(variableId).getType().equals(new IntType())){
            symbolTable.update(variableId,new IntValue(nextFreeLocation));
        }else{
            throw new RuntimeException("Variable does not exits or type mismatch!");
        }

        return null;
    }

    @Override
    public String toStringAsCode() {
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnvironment) {
        Type typeExpression = expression.typecheck(typeEnvironment);
        if(typeExpression.equals(new IntType())){
            return typeEnvironment;
        }else{
            throw new ConditionNotBoolType();
        }
    }
}
