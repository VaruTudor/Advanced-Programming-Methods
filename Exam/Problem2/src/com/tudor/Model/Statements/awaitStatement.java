package com.tudor.Model.Statements;

import com.tudor.Model.ADTs.IBarrierTable;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IHeap;
import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.IntType;
import com.tudor.Model.Types.Type;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.Value;
import javafx.util.Pair;

import java.util.List;

public class awaitStatement implements Statement{
    String variableId;

    public awaitStatement(String variableId) {
        this.variableId = variableId;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        IDict<String, Value> symbolTable = state.getSymTable();
        IStack<Statement> stack = state.getStack();
        IHeap<Integer, Value> heap = state.getHeap();
        IBarrierTable<Integer, Pair<Integer, List<Integer>>> barrierTable = state.getBarrierTable();

        //if it's not in the symbolTable or has not the type int -> error
        if(symbolTable.isDefined(variableId) && symbolTable.lookup(variableId).getType().equals(new IntType())) {
            // we can cast because we checked it has int type
            IntValue foundIndex = (IntValue) symbolTable.lookup(variableId);

            if(barrierTable.isDefined(foundIndex.getValue())){
                //retrieve the entry
                Pair<Integer, List<Integer>> barrierTableEntry = barrierTable.lookup(foundIndex.getValue());
                int length = barrierTableEntry.getValue().size();

                // N1>NL
                if (barrierTableEntry.getKey() > length){
                    // if the identifier of the currentProgramState is in L1
                    if(barrierTableEntry.getValue().contains(state.getId())){
                        stack.push(new awaitStatement(variableId));
                    }else{
                        // add the id of the current program state to L1
                        barrierTableEntry.getValue().add(state.getId());
                        // push back await
                        stack.push(new awaitStatement(variableId));
                    }
                }
            }else {
                throw new RuntimeException("foundIndex not in barrierTable!");
            }
        }else{
            throw new RuntimeException("Variable does not exits or type mismatch!");
        }

        return null;
    }

    @Override
    public String toStringAsCode() {
        return null;
    }

    @Override
    public IDict<String, Type> typecheck(IDict<String, Type> typeEnvironment) {
        return null;
    }
}
