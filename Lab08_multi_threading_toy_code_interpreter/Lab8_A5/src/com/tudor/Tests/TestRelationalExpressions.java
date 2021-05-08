package com.tudor.Tests;

import com.tudor.Exceptions.OperationNonExistent;
import com.tudor.Exceptions.TypeExceptions.TypeMismatch;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.ADTs.IHeap;
import com.tudor.Model.ADTs.MyDict;
import com.tudor.Model.ADTs.MyHeap;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.Expressions.RelationalExpression;
import com.tudor.Model.Expressions.ValueExpression;
import com.tudor.Model.Values.BoolValue;
import com.tudor.Model.Values.IntValue;
import com.tudor.Model.Values.StringValue;
import com.tudor.Model.Values.Value;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRelationalExpressions {

    @Test
    public void shouldEvaluateSmallerRelationalExpressionAsTrue(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(12));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("<"));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateSmallerRelationalExpressionAsFalse(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(15));
        Expression value2 = new ValueExpression(new IntValue(6));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("<"));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(false));
    }

    @Test
    public void shouldEvaluateSmallerOrEqualRelationalExpressionAsTrue__IntValuesAreEqual(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(6));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("<="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateSmallerOrEqualRelationalExpressionAsTrue__IntValuesAreNotEqual(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(8));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("<="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateSmallerOrEqualRelationalExpressionAsFalse(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(3));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("<="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(false));
    }

    @Test
    public void shouldEvaluateEqualRelationalExpressionAsFalse(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(3));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("=="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(false));
    }

    @Test
    public void shouldEvaluateEqualRelationalExpressionAsTrue(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(3));
        Expression value2 = new ValueExpression(new IntValue(3));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("=="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateNotEqualRelationalExpressionAsFalse(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(6));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("!="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(false));
    }

    @Test
    public void shouldEvaluateNotEqualRelationalExpressionAsTrue(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(3));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("!="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateBiggerOrEqualRelationalExpressionAsTrue__IntValuesAreEqual(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(8));
        Expression value2 = new ValueExpression(new IntValue(8));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue(">="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateBiggerOrEqualRelationalExpressionAsTrue__IntValuesAreNotEqual(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(3));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue(">="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateBiggerOrEqualRelationalExpressionAsFalse(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(8));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue(">="));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(false));
    }

    @Test
    public void shouldEvaluateBiggerRelationalExpressionAsTrue(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(3));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue(">"));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(true));
    }

    @Test
    public void shouldEvaluateBiggerEqualRelationalExpressionAsFalse(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(9));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue(">"));

        Assertions.assertEquals(expressionAddition.evaluate(notImportantDict,notImportantHeap), new BoolValue(false));
    }

    @Test
    public void shouldFailEvaluatingRelationalExpression__OperationNonExistent(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new IntValue(9));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue(";"));

        Assertions.assertThrows(OperationNonExistent.class, () -> expressionAddition.evaluate(notImportantDict,notImportantHeap), "OperationNonExistent not thrown");
    }

    @Test
    public void shouldFailEvaluatingRelationalExpression__TypeMismatch(){
        IDict<String, Value> notImportantDict = new MyDict<>();
        IHeap<Integer, Value> notImportantHeap = new MyHeap<>();

        Expression value1 = new ValueExpression(new IntValue(6));
        Expression value2 = new ValueExpression(new BoolValue(false));

        // +
        Expression expressionAddition = new RelationalExpression(value1,value2,new StringValue("=="));

        Assertions.assertThrows(TypeMismatch.class, () -> expressionAddition.evaluate(notImportantDict,notImportantHeap), "TypeMismatch not thrown");
    }

}
