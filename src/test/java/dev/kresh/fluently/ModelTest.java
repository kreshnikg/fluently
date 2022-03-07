package dev.kresh.fluently;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private Model model;

    @BeforeEach
    void setUp () {
        model = new User();
        model.tableName = "model";
        model.primaryKey = "model_id";
    }

    @Test
    void getAllResultsOfModel () {
        model.all();
    }
}
