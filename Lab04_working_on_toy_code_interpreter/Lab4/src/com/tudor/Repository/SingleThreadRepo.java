package com.tudor.Repository;

import com.tudor.Model.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadRepo implements Repo{
    private final List<ProgramState> programStates;

    public SingleThreadRepo() {
        this.programStates = new ArrayList<>();
    }

    @Override
    public void addProgramState(ProgramState newProgramState) {
        programStates.add(newProgramState);
    }

    @Override
    public ProgramState getProgramState() {
        return programStates.remove(programStates.size() - 1);
    }
}
