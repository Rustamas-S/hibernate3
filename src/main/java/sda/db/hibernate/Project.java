package sda.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sda.db.hibernate.entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Project {

    public void run() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Song.class)
                .buildSessionFactory();

        EntityManager em = sessionFactory.createEntityManager();

        Song song = new Song();
        song.setName("test");

        EntityTransaction t = em.getTransaction();

        t.begin();
        em.persist(song);
        t.commit();

        List<Song> songs = em.createQuery("FROM Song s", Song.class).getResultList();
        songs.forEach(System.out::println);

//        try (Session session = sessionFactory.openSession()) {
//            Query q = session.createQuery("FROM Song s", Song.class);
//        }
    }
}
