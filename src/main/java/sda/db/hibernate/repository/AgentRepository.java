package sda.db.hibernate.repository;

import sda.db.hibernate.entity.Agent;
import sda.db.hibernate.entity.util.AgentId;

import javax.persistence.EntityManager;
import java.util.List;

public class AgentRepository extends AbstractRepository<Agent, AgentId> {

    public AgentRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Agent find(AgentId agentId) {
        return entityManager.find(Agent.class, agentId);
    }

    @Override
    public List<Agent> findAll() {
        return entityManager.createQuery("FROM agent", Agent.class).getResultList();
    }
}
