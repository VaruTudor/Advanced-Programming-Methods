package com.tudor.Model.Statements;

import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.ProgramState;

import java.util.Collections;
import java.util.List;

public class CompoundListStatement implements Statement{
    private final List<Statement> allStatements;

    public CompoundListStatement(List<Statement> allStatements) {
        this.allStatements = allStatements;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(Statement statement : allStatements){
            s.append(statement.toString()).append("; ");
        }
        return s.toString();
    }

    @Override
    public String toStringAsCode() {
        StringBuilder s = new StringBuilder();
        for(Statement statement : allStatements){
            s.append(statement.toString()).append("\n");
        }
        return s.toString();
    }

    /**
     * Elements of allStatements are pushed in reverse order onto state's stackOfStatements.
     * @param state (type ProgramState)
     * @return the modified state
     */
    @Override
    public ProgramState execute(ProgramState state) {
        IStack<Statement> stack = state.getStack();

        Collections.reverse(allStatements);
        for (Statement statement : allStatements)
            stack.push(statement);
        Collections.reverse(allStatements);
        return state;
    }
}
