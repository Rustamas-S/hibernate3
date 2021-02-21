package sda.db.hibernate.repository;

import java.util.List;

public interface CrudRepository<T, ID> {

//    void create(T entity); // CREATE

    T find(ID id);         // READ

    List<T> findAll();

    void save(T entity);   // UPDATE

    void delete(T entity); // DELETE
}
