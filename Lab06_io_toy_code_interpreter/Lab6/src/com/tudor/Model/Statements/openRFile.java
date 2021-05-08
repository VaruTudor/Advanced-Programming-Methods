package com.tudor.Model.Statements;

import com.tudor.Exceptions.IORunTimeException;
import com.tudor.Exceptions.FilenameAlreadyDeclared;
import com.tudor.Exceptions.TypeExceptions.NotStringType;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.StringType;
import com.tudor.Model.Values.StringValue;
import com.tudor.Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements Statement{
    Expression expression;

    public openRFile(Expression expression) {
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
        if (fileTable.isDefined((StringValue) evaluatedExpression))
        {
            // check if the expression evaluated is declared in the file table
            throw new FilenameAlreadyDeclared();
        }


        BufferedReader bufferedReader;
        try{
            bufferedReader = new BufferedReader(new FileReader(((StringValue) evaluatedExpression).getValue()));
            fileTable.add((StringValue) evaluatedExpression,bufferedReader);
        } catch (FileNotFoundException e) {
            // we used the try catch just to throw a runtime exception
            throw new IORunTimeException();
        }

        return  state;
    }

    @Override
    public String toStringAsCode() {
        return "you should do toStringAsCode for openRFile";
    }
}
