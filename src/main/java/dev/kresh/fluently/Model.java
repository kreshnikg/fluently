package dev.kresh.fluently;


import javax.persistence.*;
import java.util.List;

public class Model {

    @PersistenceContext
    private EntityManager entityManager;

    private QueryBuilder queryBuilder;
    protected String tableName;
    protected String primaryKey;

    public Model() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(this.getClass().getName());

        entityManager = entityManagerFactory.createEntityManager();

        queryBuilder = new QueryBuilder(tableName);
    }

    public List<Model> all() {
        return entityManager.createQuery(queryBuilder.select("*").build()).getResultList();
    }

    void printClassName () {
        System.out.println(this.getClass().getName());
    }
}
