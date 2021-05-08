package com.tudor.Model.Statements;

import com.tudor.Model.ProgramState;

public class NopStatement implements Statement{

    @Override
    public ProgramState execute(ProgramState state) {

        return state;
    }

}
