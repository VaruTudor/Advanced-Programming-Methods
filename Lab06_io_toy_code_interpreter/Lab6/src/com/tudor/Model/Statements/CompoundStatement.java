package com.tudor.Model.Statements;

import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.ProgramState;

public class CompoundStatement implements Statement{
    private final Statement firstStatement;
    private final Statement secondStatement;

    public CompoundStatement(Statement firstStatement, Statement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public String toString(){
        return "Compound(" + firstStatement.toString() + "," + secondStatement.toString() + ")  ";
    }

    @Override
    public String toStringAsCode() {
        return firstStatement.toStringAsCode() + "\n" + secondStatement.toStringAsCode() ;
    }

    /**
     * state is modified as such:
     * secondStatement is pushed onto the stack followed by firstStatement
     * after the stack has been updated, the modified ProgramState is returned
     * @param state (type ProgramState)
     * @return the modified ProgramState
     */
    @Override
    public ProgramState execute(ProgramState state) {
        IStack<Statement> stack = state.getStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        return state;
    }

}
