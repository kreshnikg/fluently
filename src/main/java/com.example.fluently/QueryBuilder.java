package com.example.fluently;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private StringBuilder query;
    private List<String> toSelectColumns;
    private List<WhereClause> whereClauses;

    public QueryBuilder() {
        this.query = new StringBuilder();
        this.toSelectColumns = new ArrayList<>();
        this.whereClauses = new ArrayList<>();
    }

    public QueryBuilder select(String ...columns) {
        toSelectColumns.addAll(List.of(columns));
        return this;
    }

    public String build() {
        query = new StringBuilder();
        query.append("SELECT ")
                .append(formatColumnsForSelectQuery(toSelectColumns))
                .append(" FROM table");
        if(whereClauses.size() > 0) {
            boolean firstWhereClause = true;
            for (WhereClause whereClause: whereClauses) {
                if (firstWhereClause)
                    query.append(" WHERE ");
                query.append(whereClause.getColumn())
                        .append(" ")
                        .append(whereClause.getOperator())
                        .append(" '")
                        .append(whereClause.getValue())
                        .append("'");
                if(whereClause.getCombineOperator() != null)
                    query.append(" ")
                            .append(whereClause.getCombineOperator())
                            .append(" ");
                firstWhereClause = false;
            }
        }
        return query.toString().trim();
    }

    private String formatColumnsForSelectQuery(List<String> columns) {
        return String.join(", ", columns);
    }

    public QueryBuilder where(String column, String operator, String value) {
        whereClauses.add(new WhereClause(column, operator, value));
        return this;
    }
}
