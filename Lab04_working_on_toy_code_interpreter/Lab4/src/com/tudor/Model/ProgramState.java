package com.tudor.Model;

import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IList;
import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.Statements.Statement;
import com.tudor.Model.Values.Value;

import java.util.Stack;

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
        String output = "stackOfStatements: ";
        output += stackOfStatements.toString();

        output += "\nsymbolTable: ";
        output += symbolTable.toString();

        output += "\noutputList: ";
        output += outputList.toString();

        return  output;
    }
}
