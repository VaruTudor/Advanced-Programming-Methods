package com.tudor.Controller;

import com.tudor.Exceptions.NoStatementsInProgramState;
import com.tudor.Model.ADTs.IStack;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Statements.Statement;
import com.tudor.Model.Values.RefValue;
import com.tudor.Model.Values.Value;
import com.tudor.Repository.Repo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private final Repo repository;

    public Controller(Repo repository) { this.repository = repository; }

    public void addProgram(ProgramState newProgramState) {
        repository.addProgramState(newProgramState);
    }


    List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValues){
        return  symbolTableValues.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> {RefValue valueFromSymbolTable = (RefValue)value; return valueFromSymbolTable.getAddress();})
                .collect(Collectors.toList());
    }

    List<Integer> getAddressesFromHeap(Collection<Value> heapValues){
        return heapValues.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> {RefValue valueFromHeap = (RefValue)value; return valueFromHeap.getAddress();})
                .collect(Collectors.toList());
    }

    Map<Integer, Value> garbageCollector(List<Integer> addressesFromSymbolTable, List<Integer> addressesFromHeapValues, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(entry -> addressesFromSymbolTable.contains(entry.getKey()) || addressesFromHeapValues.contains(entry.getKey()) )
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
        repository.setLogFilePath("C:\\Users\\Tudor\\Desktop\\D\\faculta\\SemIII\\MAP\\Labs\\week 7 (9-14 November)\\Lab7\\output.txt");
        ProgramState program = repository.getProgramState();
        repository.logProgramStateExecution();
        System.out.println("[code state]\n" + program.toStringAsCode());
        System.out.println("\n[first state]\n" + program.toString());
        while (!program.getStack().isEmpty()) {
            oneStep(program);
            repository.logProgramStateExecution();
            System.out.println("\n[new state]\n" + program.toString());
            program.getHeap().setContent(garbageCollector(
                    getAddressesFromSymbolTable(program.getSymTable().getContent().values()),
                    getAddressesFromHeap(program.getHeap().getContent().values()),
                    program.getHeap().getContent()
            ));
            repository.logProgramStateExecution();
            System.out.println("\n[new state(after using garbageCollector)]\n" + program.toString());
        }
    }
}
