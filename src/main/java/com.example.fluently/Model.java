package com.example.fluently;

import javax.persistence.EntityManager;

public class Model {
    protected String tableName;
    protected String primaryKey;
    protected QueryBuilder queryBuilder;

    protected EntityManager entityManager;

    public Model() {
        queryBuilder = new QueryBuilder(tableName);
        
    }


}
