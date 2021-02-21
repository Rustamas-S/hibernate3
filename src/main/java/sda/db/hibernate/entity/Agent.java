package sda.db.hibernate.entity;

import sda.db.hibernate.entity.util.AgentId;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agents")
public class Agent {

    @EmbeddedId
    private AgentId id;

    private Instant activeSince;

    @OneToMany(mappedBy = "agent")
    private List<Author> authors = new ArrayList<>();

    public AgentId getId() {
        return id;
    }

    public void setId(AgentId id) {
        this.id = id;
    }

    public Instant getActiveSince() {
        return activeSince;
    }

    public void setActiveSince(Instant activeSince) {
        this.activeSince = activeSince;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "id=" + id +
                ", activeSince=" + activeSince +
                ", authors=" + authors +
                '}';
    }
}
