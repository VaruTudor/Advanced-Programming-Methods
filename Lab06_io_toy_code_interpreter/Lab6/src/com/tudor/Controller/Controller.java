package com.tudor.Controller;

import com.tudor.Exceptions.NoStatementsInProgramState;
import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Statements.Statement;
import com.tudor.Repository.Repo;

import java.io.IOException;

public class Controller {
    private final Repo repository;

    public Controller(Repo repository) { this.repository = repository; }

    public void addProgram(ProgramState newProgramState) {
        repository.addProgramState(newProgramState);
    }

    public ProgramState oneStep(ProgramState state) {
        IStack<Statement> stack = state.getStack();
        if(stack.isEmpty()){
            throw new NoStatementsInProgramState();
        }
        Statement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void allStep() throws IOException {
        repository.setLogFilePath("C:\\Users\\Tudor\\Desktop\\D\\faculta\\SemIII\\MAP\\Labs\\week 6 (3- 6November)\\Lab6\\output.txt");
        ProgramState program = repository.getProgramState();
        repository.logProgramStateExecution();
        System.out.println("[code state]\n" + program.toStringAsCode());
        System.out.println("\n[first state]\n" + program.toString());
        while (!program.getStack().isEmpty()) {
            oneStep(program);
            repository.logProgramStateExecution();
            System.out.println("\n[new state]\n" + program.toString());
        }
    }
}
