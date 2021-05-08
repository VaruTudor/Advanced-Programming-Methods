package com.tudor.Tests;

import com.tudor.Exceptions.DivisionByZero;
import com.tudor.Exceptions.OperationNonExistent;
import com.tudor.Exceptions.TypeExceptions.TypeMismatch;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.MyDict;
import com.tudor.Model.Expressions.*;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExpressions {

    @Test
    public void shouldEvaluateValueExpression() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression expression = new ValueExpression(new IntValue(7));

        Assertions.assertEquals(expression.evaluate(notImportantDict), new IntValue(7));
    }

    @Test
    public void shouldEvaluateVarExpression() {
        IDict<String, Value> symbolTable = new MyDict<>();
        symbolTable.add("a",new IntValue(7));

        Expression expression = new VarExpression("a");

        Assertions.assertEquals(expression.evaluate(symbolTable), new IntValue(7));
    }

    @Test
    public void shouldEvaluateAdditionArithExpression() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new IntValue(6));

        // +
        Expression expressionAddition = new ArithExpression(value1,value2,1);

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict), new IntValue(18));

    }

    @Test
    public void shouldEvaluateSubtractionArithExpression() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new IntValue(6));

        // -
        Expression expressionSubtraction = new ArithExpression(value1,value2,2);

        Assertions.assertEquals(expressionSubtraction.evaluate(notImportantDict), new IntValue(6));
    }

    @Test
    public void shouldEvaluateMultiplicationArithExpression() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new IntValue(6));

        // *
        Expression expressionMultiplication = new ArithExpression(value1,value2,3);

        Assertions.assertEquals(expressionMultiplication.evaluate(notImportantDict), new IntValue(72));
    }

    @Test
    public void shouldEvaluateDivisionArithExpression() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new IntValue(6));

        // /
        Expression expressionDivision = new ArithExpression(value1,value2,4);

        Assertions.assertEquals(expressionDivision.evaluate(notImportantDict), new IntValue(2));
    }

    @Test
    public void shouldFailEvaluatingDivisionArithExpression__DivisionByZero() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new IntValue(0));

        // /
        Expression expressionDivision = new ArithExpression(value1,value2,4);

        Assertions.assertThrows(DivisionByZero.class, () -> expressionDivision.evaluate(notImportantDict), "DivisionByZero not thrown");
    }

    @Test
    public void shouldFailEvaluatingDivisionArithExpression__TypeMismatch() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new BoolValue(false));

        Expression expressionDivision = new ArithExpression(value1,value2,4);

        Assertions.assertThrows(TypeMismatch.class, () -> expressionDivision.evaluate(notImportantDict), "TypeMismatch not thrown");
    }

    @Test
    public void shouldFailEvaluatingDivisionArithExpression__OperationNonExistent() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new IntValue(5));

        Expression expressionDivision = new ArithExpression(value1,value2,7);

        Assertions.assertThrows(OperationNonExistent.class, () -> expressionDivision.evaluate(notImportantDict), "OperationNonExistent not thrown");
    }

    @Test
    public void shouldEvaluateORLogicExpression() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new BoolValue(true));
        Expression value2 = new ValueExpression(new BoolValue(false));

        Expression expression = new LogicExpression(value1,value2,2);

        Assertions.assertEquals(expression.evaluate(notImportantDict), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateANDLogicExpression() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new BoolValue(true));
        Expression value2 = new ValueExpression(new BoolValue(false));

        Expression expression = new LogicExpression(value1,value2,1);

        Assertions.assertEquals(expression.evaluate(notImportantDict), new BoolValue(false));
    }

    @Test
    public void shouldFailEvaluatingLogicExpression__TypeMismatch() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new IntValue(12));
        Expression value2 = new ValueExpression(new BoolValue(false));

        Expression expression = new LogicExpression(value1,value2,1);

        Assertions.assertThrows(TypeMismatch.class, () -> expression.evaluate(notImportantDict), "TypeMismatch not thrown");
    }

    @Test
    public void shouldFailEvaluatingLogicExpression__OperationNonExistent() {
        IDict<String, Value> notImportantDict = new MyDict<>();

        Expression value1 = new ValueExpression(new BoolValue(true));
        Expression value2 = new ValueExpression(new BoolValue(false));

        Expression expression = new LogicExpression(value1,value2,5);

        Assertions.assertThrows(OperationNonExistent.class, () -> expression.evaluate(notImportantDict), "OperationNonExistent not thrown");
    }

}
