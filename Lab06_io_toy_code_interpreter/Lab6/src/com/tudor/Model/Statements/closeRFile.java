package com.tudor.Model.Statements;

import com.tudor.Exceptions.FilenameNotDeclared;
import com.tudor.Exceptions.TypeExceptions.NotStringType;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.StringType;
import com.tudor.Model.Values.StringValue;
import com.tudor.Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements Statement{
    Expression expression;

    public closeRFile(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDict<String, Value> symbolTable = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();

        Value evaluatedExpression = expression.evaluate(symbolTable);
        if (!evaluatedExpression.getType().equals(new StringType())){
            // check if the expression evaluates to StringType
            throw new NotStringType();
        }

        if (!fileTable.isDefined((StringValue) evaluatedExpression)){
            // check if the file name is defined in the file table
            throw new FilenameNotDeclared();
        }
        BufferedReader bufferedReader = fileTable.lookup((StringValue) evaluatedExpression);

        try {
            bufferedReader.close();
            fileTable.remove((StringValue) evaluatedExpression);
        } catch (IOException e) {
            throw new RuntimeException("cannot close file");
        }



        return state;
    }

    @Override
    public String toStringAsCode() {
        return "should do toStringAsCode for closeRFile";
    }
}
