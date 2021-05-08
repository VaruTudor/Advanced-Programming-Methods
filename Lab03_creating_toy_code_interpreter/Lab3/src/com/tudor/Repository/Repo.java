package com.tudor.Repository;

import com.tudor.Model.ProgramState;

public interface Repo {
    void addProgramState(ProgramState newProgramState);
    ProgramState getProgramState();
}
