package com.tudor.Model.Statements;

import com.tudor.Exceptions.VariableAlreadyDeclared;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.Type;
import com.tudor.Model.Values.Value;

public class VariableDeclarationStatement implements Statement{
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString(){

        return type + " " + name +"  ";
    }

    @Override
    public String toStringAsCode() {
        return type + " " + name +"; ";
    }

    /**
     * creates key name in current ProgramState symbol table
     * and assigns is the default value of type
     * @param state (type ProgramState)
     * @return the modified ProgramState
     */
    @Override
    public ProgramState execute(ProgramState state) {
        IDict<String, Value> symTable = state.getSymTable();

        if (symTable.isDefined(name) ){
            throw new VariableAlreadyDeclared();
        }
        else{
            symTable.add(name,type.defaultValue());
        }

        return state;
    }
}
