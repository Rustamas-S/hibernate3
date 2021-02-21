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
        return entityManager.createQuery("FROM Agent", Agent.class).getResultList();
    }

    public Double getTotalAuthorSalaryForAgent(String name, String surname) {
        return (Double) entityManager.createQuery("" +
                "SELECT SUM(aa.salary) " +
                "FROM Agent a " +
                "JOIN a.authors aa " +
                "WHERE a.id = ?1")
                .setParameter(1, new AgentId(name, surname))
                .getSingleResult();
    }
}
