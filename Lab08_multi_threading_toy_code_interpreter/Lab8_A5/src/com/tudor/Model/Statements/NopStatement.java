package com.tudor.Model.Statements;

import com.tudor.Model.ProgramState;

@SuppressWarnings("unused")
public class NopStatement implements Statement{

    /**
     * @param state (type ProgramState)
     * @return null
     */
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public String toStringAsCode() {
        return "Nop Statement  ";
    }

}
