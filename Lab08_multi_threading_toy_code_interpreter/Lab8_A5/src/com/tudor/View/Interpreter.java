package com.tudor.View;

import com.tudor.Controller.Controller;
import com.tudor.Model.ADTs.*;
import com.tudor.Model.Expressions.*;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Statements.*;
import com.tudor.Model.Types.BoolType;
import com.tudor.Model.Types.IntType;
import com.tudor.Model.Types.RefType;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.StringValue;
import com.tudor.Model.Values.Value;
import com.tudor.Repository.MultiThreadRepo;
import com.tudor.Repository.Repo;
import com.tudor.View.Commands.ExitCommand;
import com.tudor.View.Commands.RunExampleCommand;

import java.util.Arrays;

public class Interpreter {

    //int v; v=2; Print(v)
    private static Controller example1() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
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

    //int a; int b; a=2+3*5; b=a+1; Print(b)
    private static Controller example2() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
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

    //bool a; int v; a=true; If(a)Then(v=2)Else(v=3); Print(v)
    private static Controller example3() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
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

    //int v; v=4; while(v>0) print(v);v=v-1;
    private static Controller example4() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
        Statement program1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(
                                new AssignmentStatement("v",new ValueExpression(new IntValue(4))),
                                new WhileStatement(
                                        new RelationalExpression(
                                                new VarExpression("v"),
                                                new ValueExpression(new IntValue(0)),
                                                new StringValue(">")
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(new VarExpression("v")),
                                                new AssignmentStatement(
                                                        "v",
                                                        new ArithExpression(
                                                                new VarExpression("v"),
                                                                new ValueExpression(new IntValue(1)),
                                                                2
                                                        )
                                                )
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

    //Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); printf(rH(rH(a)))
    private static Controller example5() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
        Statement program1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new RefType(new IntType())),
                        new CompoundStatement(
                                new AllocateStatement("v",new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))),
                                        new CompoundStatement(
                                                new AllocateStatement("a",new VarExpression("v")),
                                                new CompoundStatement(
                                                        new AllocateStatement("v",new ValueExpression(new IntValue(30))),
                                                        new PrintStatement(new ReadHeap(new ReadHeap(new VarExpression("a"))))
                                                )

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

    //Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); printf(rH(rH(a)))
    private static Controller example6() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
        Statement program = new CompoundListStatement(
                Arrays.asList(
                        new VariableDeclarationStatement("v", new RefType(new IntType())),
                        new AllocateStatement("v",new ValueExpression(new IntValue(20))),
                        new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))),
                        new AllocateStatement("a",new VarExpression("v")),
                        new AllocateStatement("v",new ValueExpression(new IntValue(30))),
                        new PrintStatement(new ReadHeap(new ReadHeap(new VarExpression("a"))))
                )
        );

        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        executionStack.push(program);
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);
        return controller;
    }

    //int v; Ref int a; v=10; new(a,22); fork(wH(a,30);v=32;printf(v);print(rH(a)) ); printf(v); print(rH(a))
    private static Controller example7() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
        Statement program = new CompoundListStatement(
                Arrays.asList(
                        new VariableDeclarationStatement("v", new IntType()),
                        new VariableDeclarationStatement("a",new RefType(new IntType())),
                        new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                        new AllocateStatement("a",new ValueExpression(new IntValue(22))),
                        new ForkStatement(
                        new CompoundListStatement(Arrays.asList(
                            new WriteToHeapStatement("a",new ValueExpression(new IntValue(30))),
                            new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                            new PrintStatement(new VarExpression("v")),
                            new PrintStatement(new ReadHeap(new VarExpression("a")))
                                )
                            )
                        ),
                        // a new CompoundListStatement is used to "sync" the iterations of one step for the each thread.
                        // the fork statement has to execute on the first step a CompoundListStatement, so we have to add one each of
                        // the other threads so that the first one step only executes CompoundListStatement, and only after the
                        // remaining statements are executed
                        new CompoundListStatement(Arrays.asList(
                            new PrintStatement(new VarExpression("v")),
                            new PrintStatement(new ReadHeap(new VarExpression("a")))
                        ))
                )
        );

        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        executionStack.push(program);
        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out);

        controller.addProgram(myProgramState);
        return controller;
    }

    //int v; Ref int a; v=10; new(a,22); fork(wH(a,30);v=32;printf(v);print(rH(a)) ); printf(v); print(rH(a))
    private static Controller example8() {
        Repo repository = new MultiThreadRepo();
        Controller controller = new Controller(repository);
        controller.setMuteLogProgramStateExecution(true);
        Statement program = new CompoundStatement(
                new VariableDeclarationStatement("v",new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("a",new RefType(new IntType())),
                        new CompoundStatement(
                            new AssignmentStatement("v",new ValueExpression(new IntValue(10))),
                            new CompoundStatement(
                                    new AllocateStatement("a",new ValueExpression(new IntValue(10))),
                                    new CompoundStatement(
                                            new ForkStatement(
                                                    new CompoundStatement(
                                                            new WriteToHeapStatement("a",new ValueExpression(new IntValue(30))),
                                                            new CompoundStatement(
                                                                    new AssignmentStatement("v",new ValueExpression(new IntValue(32))),
                                                                    new CompoundStatement(
                                                                            new PrintStatement(new VarExpression("v")),
                                                                            new PrintStatement(new ReadHeap(new VarExpression("a")))
                                                                    )
                                                            )
                                                    )
                                            ),
                                            new CompoundStatement(
                                                    new PrintStatement(new VarExpression("v")),
                                                    new PrintStatement(new ReadHeap(new VarExpression("a")))
                                            )
                                    )
                            )
                        )
                )
        );

        IStack<Statement> executionStack = new MyStack<>();
        IDict<String, Value> symbolTable = new MyDict<>();
        IList<Value> out = new MyList<>();

        executionStack.push(program);
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
        menu.addCommand(new RunExampleCommand("4","int v; v=4; while(v>0) print(v);v=v-1",example4()));
        menu.addCommand(new RunExampleCommand("5","Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); printf(rH(rH(a)))",example5()));
        menu.addCommand(new RunExampleCommand("6","{With CompoundListStatement} Ref int v; new(v,20); Ref Ref int a; new(a,v); new(v,30); printf(rH(rH(a)))",example6()));
        menu.addCommand(new RunExampleCommand("7","int v; Ref int a; v=10; new(a,22); fork(wH(a,30);v=32;printf(v);print(rH(a)) ); printf(v); print(rH(a))",example7()));
        menu.addCommand(new RunExampleCommand("8","{Without CompoundListStatement} int v; Ref int a; v=10; new(a,22); fork(wH(a,30);v=32;printf(v);print(rH(a)) ); printf(v); print(rH(a))",example8()));

        menu.show();

    }

}
