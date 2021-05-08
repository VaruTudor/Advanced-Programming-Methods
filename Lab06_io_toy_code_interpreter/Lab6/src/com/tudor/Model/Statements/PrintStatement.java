package com.tudor.Model.Statements;

import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IList;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Values.Value;

public class PrintStatement implements Statement{
    private final Expression expression;

    public PrintStatement(Expression v) {
        this.expression = v;
    }

    @Override
    public String toString(){
        return  "print(" + expression.toString() + ")  ";
    }

    public String toStringAsCode() {
        return  "print(" + expression.toString() + ");";
    }

    /**
     * the expression is "printed" (is added to the out list of the ProgramState which will the be returned)
     * @return a modified ProgramState
     */
    @Override
    public ProgramState execute(ProgramState state)  {
        IDict<String,Value> symbolTable = state.getSymTable();
        IList<Value> out = state.getList();

        out.add(expression.evaluate(symbolTable));
        return state;
    }
}
