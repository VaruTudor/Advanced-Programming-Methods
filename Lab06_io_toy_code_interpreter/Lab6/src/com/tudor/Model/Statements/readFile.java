package com.tudor.Model.Statements;

import com.tudor.Exceptions.FilenameNotDeclared;
import com.tudor.Exceptions.IORunTimeException;
import com.tudor.Exceptions.TypeExceptions.NotStringType;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.StringType;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.StringValue;
import com.tudor.Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements Statement{
    Expression expression;
    String variableName;

    public readFile(Expression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
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
            String valueAsString = bufferedReader.readLine();
            int value;
            if (valueAsString.equals("")){
                value = 0;
            } else {
                value = Integer.parseInt(valueAsString);
            }

            symbolTable.add(variableName,new IntValue(value));
        } catch (IOException e) {
            throw new IORunTimeException();
        }

        return state;
    }

    @Override
    public String toStringAsCode() {
        return "you should do toStringAsCode for openRFile";
    }
}
