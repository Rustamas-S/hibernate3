package sda.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sda.db.hibernate.entity.Album;
import sda.db.hibernate.entity.Author;
import sda.db.hibernate.entity.Song;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class Project {

    public void run() {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Album.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Song.class)
                .buildSessionFactory();

        EntityManager em = sessionFactory.createEntityManager();

        EntityTransaction t = em.getTransaction();

        t.begin();

        Author author = new Author();
        author.setName("Super Author");

        em.persist(author);
        em.persist(createAlbum(author));

        t.commit();

        List<Song> songs = em.createQuery("FROM Song", Song.class).getResultList();
        songs.forEach(System.out::println);

        List<Album> albums = em.createQuery("FROM Album", Album.class).getResultList();
        albums.forEach(System.out::println);

//        try (Session session = sessionFactory.openSession()) {
//            Query q = session.createQuery("FROM Song s", Song.class);
//        }
    }

    private Album createAlbum(Author author) {
        Song songA = new Song();
        songA.setName("song A");
        songA.setAuthor(author);

        Song songB = new Song();
        songB.setName("song B");
        songB.setAuthor(author);

        Album album = new Album();
        album.setName("New Album");
        album.setAuthor(author);
        album.addSong(songA);
        album.addSong(songB);

        return album;
    }
}
