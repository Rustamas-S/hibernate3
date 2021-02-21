package sda.db.hibernate.repository;

import sda.db.hibernate.entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class AbstractRepository<T, ID> implements CrudRepository<T, ID> {

    protected EntityManager entityManager;

    public AbstractRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(T entity) {
        EntityTransaction transaction = entityManager.getTransaction();
        boolean isTransactionActive = transaction.isActive();
        if (!isTransactionActive) {
            transaction.begin();
        }
        entityManager.persist(entity);
        if (!isTransactionActive) {
            transaction.commit();
        }
    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }
}
