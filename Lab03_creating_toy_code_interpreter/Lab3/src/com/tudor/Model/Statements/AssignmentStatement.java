package com.tudor.Model.Statements;

import com.tudor.Exceptions.TypeMismatch;
import com.tudor.Exceptions.VariableNonDeclared;
import com.tudor.Model.ADTs.IDict;
import com.tudor.Model.Expressions.Expression;
import com.tudor.Model.ProgramState;
import com.tudor.Model.Types.Type;
import com.tudor.Model.Values.Value;

public class AssignmentStatement implements Statement{
    String variableId;
    Expression expression;

    public AssignmentStatement(String v, Expression valueExpression) {
        this.variableId = v;
        this.expression = valueExpression;
    }

    @Override
    public String toString(){
        return variableId + "=" +expression.toString();
    }

    /**
     * it assigns a Value to a key from the current ProgramState symbol table
     * if the key is already defined
     * @param state (type ProgramState)
     * @return the modified ProgramState
     * @throws RuntimeException if the Type of the key from the symbol table
     *                   is not the same as the Value returned by expression evaluation (type TypeMismatch)
     *                   or if the key does not exist (Type VariableNotDeclared)
     */
    @Override
    public ProgramState execute(ProgramState state)  {
        IDict<String, Value> symTable = state.getSymTable();

        if (symTable.isDefined(variableId) ){
            Value value = expression.evaluate(symTable);
            Type type = (symTable.lookup(variableId).getType() );
            if (value.getType().equals(type)){
                symTable.update(variableId, value);
            }
            else{
                throw new TypeMismatch();
            }
        }
        else{
            throw new VariableNonDeclared();
        }

        return state;
    }
}
