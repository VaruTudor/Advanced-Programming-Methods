package com.tudor.Tests;

import com.tudor.Controller.Controller;
import com.tudor.Model.ADTs.*;
import com.tudor.Model.Expressions.ArithExpression;
import com.tudor.Model.Expressions.ValueExpression;
import com.tudor.Model.Expressions.VarExpression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Statements.*;
import com.tudor.Model.Types.IntType;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.Value;
import com.tudor.Repository.Repo;
import com.tudor.Repository.SingleThreadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestController {

    @Test
    public void shouldUpdateOutTable() throws Exception {
        Repo repository = new SingleThreadRepo();
        Controller controller = new Controller(repository);

        Statement originalProgram =
                new PrintStatement(new ValueExpression(new IntValue(50)));

        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        executionStack.push(originalProgram);

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);
        controller.allStep();

        Assertions.assertEquals(out.pop(),new IntValue(50));
    }

    @Test
    public void shouldUpdateSymbolTable() throws Exception {
        Repo repository = new SingleThreadRepo();
        Controller controller = new Controller(repository);
        Statement originalProgram =
            new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new IfStatement(
                    new ValueExpression(new BoolValue(true)),
                    new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(5))),
                        new PrintStatement(
                                new ArithExpression(
                                    new VarExpression("v"),
                                    new ValueExpression(new IntValue(3)),
                                    1
                        ) ) ) ,
                    new PrintStatement(new ValueExpression(new IntValue(100) ) ) )
            );

        IStack<Statement> executionStack = new MyStack<>();
        executionStack.push(originalProgram);

        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);

        controller.allStep();

        Assertions.assertEquals(symbolTable.lookup("v"), new IntValue(5));

    }


    @Test
    public void shouldUpdateStack() throws Exception {
        Repo repository = new SingleThreadRepo();
        Controller controller = new Controller(repository);
        Statement originalProgram =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new IfStatement(
                                new ValueExpression(new BoolValue(true)),
                                new CompoundStatement(
                                        new AssignmentStatement("v", new ValueExpression(new IntValue(5))),
                                        new PrintStatement(
                                                new ArithExpression(
                                                        new VarExpression("v"),
                                                        new ValueExpression(new IntValue(3)),
                                                        1
                                                ) ) ) ,
                                new PrintStatement(new ValueExpression(new IntValue(100) ) ) )
                );

        IStack<Statement> executionStack = new MyStack<>();
        executionStack.push(originalProgram);

        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);

        controller.allStep();

        Assertions.assertTrue(executionStack.isEmpty());

    }
}
