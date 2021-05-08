package com.tudor.Repository;

import com.tudor.Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class SingleThreadRepo implements Repo{
    private final List<ProgramState> programStates;
    private String logFilePath;

    @Override
    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }


    public SingleThreadRepo() {
        this.programStates = new ArrayList<>();
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        programStates.add(newProgramState);
    }

    @Override
    public ProgramState getProgramState() {
        return programStates.get(programStates.size() - 1);
    }

    @Override
    public ProgramState popProgramState() {
        return programStates.remove(programStates.size() - 1);
    }

    @Override
    public void logProgramStateExecution() throws IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.write(getProgramState().toString());
        logFile.close();
    }
}
