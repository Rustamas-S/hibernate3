package sda.db.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Author author;

    @Column(nullable = false)
    private Integer duration;

    @ManyToMany
    private List<Album> albums = new ArrayList<>();

    public Song(String name, Author author, Integer duration) {
        this.name = name;
        this.author = author;
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public Integer getDuration() {
        return duration;
    }

    public String getDurationString() {
        int s = duration % 60;
        return duration / 60 + ":" + (s < 10 ? "0" : "") + duration % 60;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", album=" + albums.stream().map(Album::getName).collect(Collectors.toList()) +
                ", duration=" + getDurationString() +
                '}';
    }
}
