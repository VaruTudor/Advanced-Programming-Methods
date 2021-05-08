package com.tudor.Model.Statements;

import com.tudor.Exceptions.TypeExceptions.NotBoolType;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.BoolType;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.Value;

public class IfStatement implements Statement{
    Expression expression;
    Statement thenStatement;
    Statement elseStatement;

    public IfStatement(Expression expression, Statement thenStatement, Statement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "If("
                + expression.toString() + ") "
                + " (" + thenStatement.toString() + ")"
                + " else (" + elseStatement.toString() + ")  ";
    }


    @Override
    public String toStringAsCode() {
        return "If("
                + expression.toString() + "){ "
                + "\n\t" + thenStatement.toStringAsCode()
                + "\n}Else{ \n\t" + elseStatement.toStringAsCode() + "\n}";
    }

    /**
     * state is modified as such:
     * if expression is evaluated as true then thenStatement is pushed onto the stack
     * otherwise elseStatement is pushed onto the stack
     * after the stack has been updated, the modified ProgramState is returned
     * @param state (type ProgramState)
     * @return the modified ProgramState
     * @throws RuntimeException if expression does not evaluate as a BoolValue (type ConditionalNotBoolean)
     */
    @Override
    public ProgramState execute(ProgramState state)  {
        IStack<Statement> stack = state.getStack();
        IDict<String, Value> symbolTable = state.getSymTable();

        Value condition = expression.evaluate(symbolTable);
        if (!condition.getType().equals(new BoolType())){
            throw new NotBoolType();
        }

        BoolValue boolCondition = (BoolValue) condition;
        if(boolCondition.getValue())
        {
            //true
            stack.push(thenStatement);
        }
        else{
            //false
            stack.push(elseStatement);
        }

        return state;
    }
}
