package com.tudor.Tests;

import com.tudor.Exceptions.AddressNotExistent;
import com.tudor.Exceptions.TypeExceptions.TypeMismatch;
import com.tudor.Exceptions.TypeExceptions.ValueNotRefType;
import com.tudor.Exceptions.VariableNotDeclared;
import com.tudor.Model.ADTs.*;
import com.tudor.Model.Expressions.ValueExpression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Statements.AllocateStatement;
import com.tudor.Model.Statements.Statement;
import com.tudor.Model.Statements.VariableDeclarationStatement;
import com.tudor.Model.Statements.WriteToHeapStatement;
import com.tudor.Model.Types.IntType;
import com.tudor.Model.Types.RefType;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.StringValue;
import com.tudor.Model.Values.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

public class TestHeapStatements {

    // AllocateStatement
    @Test
    public void shouldExecuteAllocateStatement(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement declareA = new VariableDeclarationStatement("A",new RefType(new IntType()));
        Statement allocateA = new AllocateStatement("A",new ValueExpression(new IntValue(15)));

        ProgramState programState1 = declareA.execute(myProgramState);
        allocateA.execute(programState1);

        Assertions.assertEquals(heap.lookup(1),new IntValue(15));
    }

    @Test
    public void shouldFailExecutingAllocateStatement__VariableNotDeclared(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement allocateA = new AllocateStatement("A",new ValueExpression(new IntValue(15)));

        Assertions.assertThrows(VariableNotDeclared.class, ()->allocateA.execute(myProgramState),"VariableNotDeclared not thrown");
    }

    @Test
    public void shouldFailExecutingAllocateStatement__TypeMismatch__VariableIsNotRefType(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement declareA = new VariableDeclarationStatement("A",new IntType());
        Statement allocateA = new AllocateStatement("A",new ValueExpression(new IntValue(15)));

        ProgramState programState1 = declareA.execute(myProgramState);

        Assertions.assertThrows(ValueNotRefType.class, ()->allocateA.execute(programState1),"TypeMismatch not thrown");
    }

    @Test
    public void shouldFailExecutingAllocateStatement__TypeMismatch__NotRespectingInnerType(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement declareA = new VariableDeclarationStatement("A",new RefType(new IntType()));
        Statement allocateA = new AllocateStatement("A",new ValueExpression(new BoolValue(true)));

        ProgramState programState1 = declareA.execute(myProgramState);

        Assertions.assertThrows(TypeMismatch.class, ()->allocateA.execute(programState1),"TypeMismatch not thrown");
    }

    //WriteToHeapStatement
    @Test
    public void shouldExecuteWriteToHeapStatement(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement declareA = new VariableDeclarationStatement("A",new RefType(new IntType()));
        Statement allocateA = new AllocateStatement("A",new ValueExpression(new IntValue(15)));
        Statement writeA = new WriteToHeapStatement("A",new ValueExpression(new IntValue(20)));

        ProgramState programState1 = declareA.execute(myProgramState);
        ProgramState programState2 = allocateA.execute(programState1);
        writeA.execute(programState2);

        Assertions.assertEquals(heap.lookup(1),new IntValue(20));
    }

    @Test
    public void shouldFailExecutingWriteToHeapStatement__VariableNotDeclared(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement writeA = new WriteToHeapStatement("A",new ValueExpression(new IntValue(20)));

        Assertions.assertThrows(VariableNotDeclared.class, ()->writeA.execute(myProgramState),"VariableNotDeclared not thrown");
    }

    @Test
    public void shouldFailExecutingWriteToHeapStatement__TypeMismatch__VariableFromSymTableNotRefType(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement declareA = new VariableDeclarationStatement("A",new IntType());
        Statement writeA = new WriteToHeapStatement("A",new ValueExpression(new IntValue(20)));

        ProgramState programState1 = declareA.execute(myProgramState);

        Assertions.assertThrows(ValueNotRefType.class, ()->writeA.execute(programState1),"TypeMismatch not thrown");
    }

    @Test
    public void shouldFailExecutingWriteToHeapStatement__TypeMismatch__NotDefinedInHeap(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement declareA = new VariableDeclarationStatement("A",new RefType(new IntType()));
        Statement writeA = new WriteToHeapStatement("A",new ValueExpression(new IntValue(20)));

        ProgramState programState1 = declareA.execute(myProgramState);

        Assertions.assertThrows(AddressNotExistent.class, ()->writeA.execute(programState1),"VariableNotDeclared not thrown");
    }

    @Test
    public void shouldFailExecutingWriteToHeapStatement__TypeMismatch__DifferentTypeStoredInHeap(){
        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();
        IDict<StringValue, BufferedReader>fileTable = new MyDict<>();
        IHeap<Integer,Value> heap = new MyHeap<>();
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, fileTable, heap);

        Statement declareA = new VariableDeclarationStatement("A",new RefType(new IntType()));
        Statement allocateA = new AllocateStatement("A",new ValueExpression(new IntValue(15)));
        Statement writeA = new WriteToHeapStatement("A",new ValueExpression(new BoolValue(false)));

        ProgramState programState1 = declareA.execute(myProgramState);
        ProgramState programState2 = allocateA.execute(programState1);

        Assertions.assertThrows(TypeMismatch.class, ()->writeA.execute(programState2),"TypeMismatch not thrown");
    }
}
