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

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
//    static Repo repository = new SingleThreadRepo();
//    static Controller controller = new Controller(repository);

    public static void printMenu(){
        System.out.println("1. int v; v=2; Print(v)");
        System.out.println("2. int a; int b; a=2+3*5; b=a+1; Print(b)");
        System.out.println("3. bool a; int v; a=true; If(a)Then(v=2)Else(v=3); Print(v)");
        System.out.println("0. exit");
    }

    private static void example1() {
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

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, program1);

        controller.addProgram(myProgramState);

        controller.allStep();
    }

    private static void example2() {
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

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, program1);

        controller.addProgram(myProgramState);

        controller.allStep();
    }

    private static void example3()  {
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

        ProgramState myProgramState = new ProgramState(executionStack, symbolTable, out, program1);

        controller.addProgram(myProgramState);

        controller.allStep();
    }

    public static void main(String[] args){

        int choiceIndex;

        while(true){
            try {
                printMenu();

                // !if the scanner is not created at each iteration, it would get blocked if someone would give it a different type
                // and won't attempt to read user input again
                Scanner scanner = new Scanner(System.in);

                System.out.print("> ");
                choiceIndex = scanner.nextInt();
                switch (choiceIndex) {
                    default -> System.out.println("The number you introduced is not part of the options");
                    case 0 -> System.exit(0);
                    case 1 -> {
                        try {
                            example1();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    case 2 -> {
                        try {
                            example2();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    case 3 -> {
                        try {
                            example3();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }catch (InputMismatchException e){
                System.out.println("U did not give an integer");
            }
        }
    }

}
