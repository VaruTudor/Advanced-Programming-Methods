package com.tudor.View;

import com.tudor.Controller.Controller;
import com.tudor.Model.ADTs.*;
import com.tudor.Model.Expressions.ArithExpression;
import com.tudor.Model.Expressions.ValueExpression;
import com.tudor.Model.Expressions.VarExpression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Statements.*;
import com.tudor.Model.Types.BoolType;
import com.tudor.Model.Types.IntType;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.Value;
import com.tudor.Repository.Repo;
import com.tudor.Repository.SingleThreadRepo;
import com.tudor.View.Commands.ExitCommand;
import com.tudor.View.Commands.RunExampleCommand;

public class Interpreter {

    private static Controller example1() {
        Repo repository = new SingleThreadRepo();
        Controller controller = new Controller(repository);

        Statement program1 =
                new CompoundStatement(
                    new VariableDeclarationStatement("v", new IntType()),
                    new CompoundStatement(
                            new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                            new PrintStatement(new VarExpression("v"))
                    )
                );

        IStack<Statement> executionStack = new MyStack<>();
        executionStack.push(program1);

        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);

        return controller;
    }

    private static Controller example2() {
        Repo repository = new SingleThreadRepo();
        Controller controller = new Controller(repository);
        Statement program1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("b",new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a",
                                                new ArithExpression(
                                                        new ValueExpression(new IntValue(2)),
                                                        new ArithExpression(
                                                            new ValueExpression(new IntValue(3)),
                                                            new ValueExpression(new IntValue(5)),
                                                            3),
                                                    1)
                                        ),
                                        new CompoundStatement(
                                                new AssignmentStatement("b",
                                                        new ArithExpression(
                                                            new VarExpression("a"),
                                                            new ValueExpression(new IntValue(1)),
                                                            1
                                                        )
                                                ),
                                                new PrintStatement(new VarExpression("b"))
                                        )
                                )
                        )
                );

        IStack<Statement> executionStack = new MyStack<>();
        executionStack.push(program1);

        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);
        return controller;
    }

    private static Controller example3() {
        Repo repository = new SingleThreadRepo();
        Controller controller = new Controller(repository);
        Statement program1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new BoolType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v",new IntType()),
                                new CompoundStatement(
                                        new AssignmentStatement("a",new ValueExpression(new BoolValue(true))),
                                        new CompoundStatement(
                                                new IfStatement(
                                                        new VarExpression("a"),
                                                        new AssignmentStatement("v",new ValueExpression(new IntValue(2))),
                                                        new AssignmentStatement("v",new ValueExpression(new IntValue(3)))
                                                ),
                                                new PrintStatement(new VarExpression("v"))
                                        )
                                )
                        )
                );

        IStack<Statement> executionStack = new MyStack<>();
        executionStack.push(program1);

        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);
        return controller;
    }

    public static void main(String[] args){

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExampleCommand("1","int v; v=2; Print(v)",example1()) );
        menu.addCommand(new RunExampleCommand("2","int a; int b; a=2+3*5; b=a+1; Print(b)",example2()) );
        menu.addCommand(new RunExampleCommand("3","bool a; int v; a=true; If(a)Then(v=2)Else(v=3); Print(v)",example3()) );
        menu.show();

    }

}
