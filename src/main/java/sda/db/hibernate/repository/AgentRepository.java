package sda.db.hibernate.repository;

import sda.db.hibernate.entity.Agent;
import sda.db.hibernate.entity.util.AgentId;

import javax.persistence.EntityManager;
import java.util.List;

public class AgentRepository extends AbstractRepository<Agent, AgentId> {

    public AgentRepository(EntityManager entityManager) {
        super(entityManager, Agent.class);
    }

    public Agent find(String name, String surname) {
        return find(new AgentId(name, surname));
    }

    @Override
    public List<Agent> findAll() {
        return entityManager.createQuery("FROM agent", Agent.class).getResultList();
    }
}
