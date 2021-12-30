package com.example.demo.fluently;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {

    private QueryBuilder queryBuilder;
    private String tableName;

    @BeforeEach
    void setUp() {
        tableName = "table";
        queryBuilder = new QueryBuilder(tableName);
    }

    @Test
    void selectAllFromTable() {
        String query = queryBuilder.select("*").build();
        assertEquals(String.format("SELECT * FROM %s", tableName), query);
    }

    @Test
    void selectAColumnFromTable() {
        String query = queryBuilder.select("a_column").build();
        assertEquals(String.format("SELECT a_column FROM %s", tableName), query);
    }

    @Test
    void selectTwoColumnsFromTable() {
        String query = queryBuilder.select("first_column", "second_column").build();
        assertEquals(String.format("SELECT first_column, second_column FROM %s", tableName), query);
    }

    @Test
    void selectAllWhereCondition() {
        String query = queryBuilder.where("a_column", "=", "value").build();
        assertEquals(String.format("SELECT * FROM %s WHERE a_column = 'value'", tableName), query);
    }

    @Test
    void selectColumnWhereCondition() {
        String query = queryBuilder.select("id")
                .where("a_column", "=", "value")
                .build();
        assertEquals(
                String.format("SELECT id FROM %s WHERE a_column = 'value'", tableName),
                query
        );
    }

    @Test
    void selectTwoColumnsWhereCondition() {
        String query = queryBuilder.select("first_column", "second_column")
                .where("a_column", "=", "value")
                .build();
        assertEquals(
                String.format("SELECT first_column, second_column FROM %s WHERE a_column = 'value'", tableName),
                query
        );
    }

    @Test
    void selectAllWithTwoWheres() {
        String query = queryBuilder.where("a_column", "=", "value")
                .where("another_column", "=", "2")
                .build();
        assertEquals(
                String.format("SELECT * FROM %s WHERE a_column = 'value' AND another_column = '2'", tableName),
                query
        );
    }

    @Test
    void selectAllWhereAndWhere() {
        String query = queryBuilder.where("a_column", "=", "value")
                .andWhere("another_column", "=", "2")
                .build();
        assertEquals(
                String.format("SELECT * FROM %s WHERE a_column = 'value' AND another_column = '2'",tableName),
                query
        );
    }

    @Test
    void selectAllWhereOrWhere() {
        String query = queryBuilder.where("a_column", "=", "value")
                .orWhere("another_column", "=", "2")
                .build();
        assertEquals(
                String.format("SELECT * FROM %s WHERE a_column = 'value' OR another_column = '2'", tableName),
                query
        );
    }
}