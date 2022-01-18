package dev.kreshnikgashi.fluently;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private StringBuilder query;
    private List<String> toSelectColumns;
    private List<WhereClause> whereClauses;
    private String tableName;

    public QueryBuilder(String tableName) {
        this.query = new StringBuilder();
        this.toSelectColumns = new ArrayList<>();
        this.whereClauses = new ArrayList<>();
        this.tableName = tableName;
    }

    public QueryBuilder select(String... columns) {
        toSelectColumns.addAll(List.of(columns));
        return this;
    }

    public String build() {

        addSelectClauseToQuery();

        addWhereClausesToQuery();

        return query.toString().trim();
    }

    private void addSelectClauseToQuery() {
        String selectClause =
                String.format("SELECT %s FROM %s", formatColumnsForSelectQuery(toSelectColumns), tableName);
        query.append(selectClause);
    }

    private void addWhereClausesToQuery() {
        if (whereClauses.size() == 0) return;

        query.append(" WHERE ");
        for (WhereClause whereClause : whereClauses) {
            addWhereClauseToQuery(whereClause);
        }
    }

    private void addWhereClauseToQuery(WhereClause whereClause) {
        if (whereClause.getCombineOperator() != null)
            query.append(String.format(" %s ", whereClause.getCombineOperator()));

        query.append(String.format(
                "%s %s '%s'",
                whereClause.getColumn(),
                whereClause.getOperator(),
                whereClause.getValue()
        ));
    }

    private String formatColumnsForSelectQuery(List<String> columns) {
        if (columns.size() == 0)
            return "*";
        return String.join(", ", columns);
    }

    public QueryBuilder where(String column, String operator, String value) {
        String combineOperator = null;
        if (whereClauses.size() > 0)
            combineOperator = "AND";
        whereClauses.add(new WhereClause(column, operator, value, combineOperator));
        return this;
    }

    public QueryBuilder andWhere(String column, String operator, String value) {
        whereClauses.add(new WhereClause(column, operator, value, "AND"));
        return this;
    }

    public QueryBuilder orWhere(String column, String operator, String value) {
        whereClauses.add(new WhereClause(column, operator, value, "OR"));
        return this;
    }
}
