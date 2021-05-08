package com.tudor.Repository;

import com.tudor.Model.ProgramState;

import java.io.IOException;

public interface Repo {
    void addProgramState(ProgramState newProgramState);
    void setLogFilePath(String filePath);
    ProgramState getProgramState();
    ProgramState popProgramState();
    void logProgramStateExecution() throws IOException;
}
