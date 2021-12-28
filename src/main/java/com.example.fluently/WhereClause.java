package com.example.fluently;

public class WhereClause {
    private String column;
    private String operator;
    private String value;
    private String combineOperator;

    public WhereClause(String column, String operator, String value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    public WhereClause(String column, String operator, String value, String combineOperator) {
        this.column = column;
        this.operator = operator;
        this.value = value;
        this.combineOperator = combineOperator;
    }

    public String getColumn() {
        return column;
    }

    public String getOperator() {
        return operator;
    }

    public String getValue() {
        return value;
    }

    public String getCombineOperator() {
        return combineOperator;
    }
}
