package com.tudor.Model;

import com.tudor.Model.ADTs.*;
import com.tudor.Model.Statements.Statement;
import com.tudor.Model.Values.Value;

public class ProgramState {
    private final IStack<Statement> stackOfStatements;
    private final IDict<String, Value> symbolTable;
    private final IList<Value> outputList;
    Statement originalProgram;


    public ProgramState(IStack<Statement> stack, IDict<String, Value> dict, IList<Value> list, Statement originalProgram) {
        this.stackOfStatements = stack;
        this.symbolTable = dict;
        this.outputList = list;
        this.originalProgram = originalProgram;
    }

    public IStack<Statement> getStack() { return stackOfStatements; }

    public IDict<String, Value> getSymTable() {
        return symbolTable;
    }

    public IList<Value> getList() {
        return outputList;
    }

    @Override
    public String toString(){
        return stackOfStatements.toString();
    }
}
