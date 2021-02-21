package sda.db.hibernate.entity;

import sda.db.hibernate.entity.util.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "albums")
public class Album extends BaseEntity {

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    @OneToOne
    private Author author;

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void addSong(Song song) {
        song.getAlbums().add(this);
        songs.add(song);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + getName() + '\'' +
                ", songs: " + songs.stream().map(Song::getName).collect(Collectors.toList()) +
                ", author=" + author +
                '}';
    }
}
