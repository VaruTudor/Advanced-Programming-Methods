package com.tudor.Model.Statements;

import com.tudor.Model.ProgramState;

public interface Statement {
    /**
     * the ProgramState received as a parameter is modified in a specific way and then returned
     * @return a modified ProgramState
     */
    ProgramState execute(ProgramState state) ;
}
