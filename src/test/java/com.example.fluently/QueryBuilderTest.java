package com.example.fluently;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryBuilderTest {

    private QueryBuilder queryBuilder;

    @BeforeEach
    void setUp() {
        queryBuilder = new QueryBuilder();
    }

    @Test
    void selectAllFromTable() {
        String query = queryBuilder.select("*").build();
        assertEquals("SELECT * FROM table", query);
    }

    @Test
    void selectAColumnFromTable() {
        String query = queryBuilder.select("a_column").build();
        assertEquals("SELECT a_column FROM table", query);
    }

    @Test
    void selectTwoColumnsFromTable() {
        String query = queryBuilder.select("first_column", "second_column").build();
        assertEquals("SELECT first_column, second_column FROM table", query);
    }

    @Test
    void selectAllWhereCondition() {
        String query = queryBuilder.where("a_column", "=", "value").build();
        assertEquals("SELECT * FROM table WHERE a_column = 'value'", query);
    }

    @Test
    void selectColumnWhereCondition() {
        String query = queryBuilder.select("id")
                .where("a_column", "=", "value")
                .build();
        assertEquals("SELECT id FROM table WHERE a_column = 'value'", query);
    }

    @Test
    void selectTwoColumnsWhereCondition() {
        String query = queryBuilder.select("first_column", "second_column")
                .where("a_column", "=", "value")
                .build();
        assertEquals("SELECT first_column, second_column FROM table WHERE a_column = 'value'", query);
    }

    @Test
    void selectAllWhereConditionAndCondition() {
        String query = queryBuilder.where("a_column", "=", "value")
                .where("another_column", "=", "2")
                .build();
        assertEquals("SELECT first_column, second_column FROM table WHERE a_column = 'value'", query);
    }
}