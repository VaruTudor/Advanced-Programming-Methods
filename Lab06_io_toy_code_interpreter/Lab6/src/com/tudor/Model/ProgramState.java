package com.tudor.Model;

import com.tudor.Model.ADTs.*;
import com.tudor.Model.Statements.NopStatement;
import com.tudor.Model.Statements.Statement;
import com.tudor.Model.Values.StringValue;
import com.tudor.Model.Values.Value;

import java.io.BufferedReader;
import java.util.Stack;

public class ProgramState {
    private final IStack<Statement> stackOfStatements;
    private final IDict<String, Value> symbolTable;
    private final IList<Value> outputList;
    private final IDict<StringValue, BufferedReader> fileTable;
    Statement originalProgram; // don't really use this


    public ProgramState(IStack<Statement> stack, IDict<String, Value> dict, IList<Value> list) {
        stackOfStatements = stack;
        symbolTable = dict;
        outputList = list;
        fileTable = new MyFileTable<>();
    }

    public ProgramState(IStack<Statement> stack, IDict<String, Value> dict, IList<Value> list, IDict<StringValue, BufferedReader> fileDict) {
        stackOfStatements = stack;
        symbolTable = dict;
        outputList = list;
        fileTable = fileDict;
    }

    public ProgramState() {
        stackOfStatements = new MyStack<>();
        symbolTable = new MyDict<>();
        outputList = new MyList<>();
        fileTable = new MyFileTable<>();
        originalProgram = new NopStatement();
    }

    public IDict<StringValue, BufferedReader> getFileTable() {
        return fileTable;
    }

    public IStack<Statement> getStack() { return stackOfStatements; }

    public IDict<String, Value> getSymTable() {
        return symbolTable;
    }

    public IList<Value> getList() {
        return outputList;
    }

    /**
     * get all Collections which are part of the ProgramState
     * and return a String of their contents (like a detailed debug)
     * @return output (type String)
     */
    public String toStringAsCode(){
        StringBuilder output = new StringBuilder();
        Stack<Statement> stack = stackOfStatements.getStack();
        for(Statement statement : stack){
            output.append(statement.toStringAsCode());
        }
        return output.toString();
    }

    @Override
    public String toString(){
        String output = "[stackOfStatements]\n";
        output += stackOfStatements.toString();

        output += "\n[symbolTable]\n";
        output += symbolTable.toString();

        output += "\n[outputList]\n";
        output += outputList.toString();

        output += "\n\n";
        return  output;

    }
}
