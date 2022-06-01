package com.tudor.Model.Statements;

import com.tudor.Exceptions.TypeExceptions.ConditionNotBoolType;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IHeap;
import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.ADTs.MyDict;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.Expressions.NotExpression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.BoolType;
import com.tudor.Model.Types.Type;
import com.tudor.Model.Values.Value;

public class repeatUntilStatement implements Statement{
    Statement statement;
    Expression expression;

    public repeatUntilStatement(Statement statement, Expression expression) {
        this.statement = statement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        IStack<Statement> stack = state.getStack();
        IDict<String, Value> symbolTable = state.getSymTable();
        IHeap<Integer, Value> heap = state.getHeap();

        Value condition = expression.evaluate(symbolTable,heap);
        if (!condition.getType().equals(new BoolType())){
            throw new ConditionNotBoolType();
        }

        stack.push(new CompoundStatement(
                statement,
                new WhileStatement(
                        new NotExpression(expression),
                        statement
                )
        ));

        return null;
    }

    @Override
    public String toStringAsCode() {
        return "repeat (" + statement.toString() + ") until (" + expression.toString() +");\n}";
    }

    @Override
    public String toString() {
        return "While("
                + expression.toString() + ") "
                + " (" + statement.toString() + ")";
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnvironment) {
        Type typeExpression = expression.typecheck(typeEnvironment);
        if(typeExpression.equals(new BoolType())){
            statement.typecheck(new MyDict<>((MyDict<String, Type>) typeEnvironment));
            return typeEnvironment;
        }else{
            throw new ConditionNotBoolType();
        }
    }
}
