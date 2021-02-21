package sda.db.hibernate.repository;

import sda.db.hibernate.entity.Album;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class AlbumRepository extends AbstractRepository<Album, UUID> {

    public AlbumRepository(EntityManager entityManager) {
        super(entityManager, Album.class);
    }

    @Override
    public List<Album> findAll() {
        return entityManager.createQuery("FROM Album", Album.class).getResultList();
    }
}
