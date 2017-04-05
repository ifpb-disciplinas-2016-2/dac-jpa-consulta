/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ifpb.dac.infraestrutura;

import edu.ifpb.dac.model.Empregado;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Samuell
 */
public class DaoJPA implements Dao {

    private EntityManagerFactory emf;
    private EntityManager em;
    private Query query;

    public DaoJPA(String unidadePersistencia) {
        emf = Persistence.createEntityManagerFactory(unidadePersistencia);
        em = emf.createEntityManager();
    }

    public DaoJPA() {
        this("consulta");
    }

    @Override
    public void save(Object o) {

        try {

            em.getTransaction().begin();

            em.persist(o);

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

    @Override
    public Object find(Class classe, Object object) {
        return em.find(classe, object);
    }

    @Override
    public void update(Object o) {
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }

     
    @Override
    public Object getSingleResult(String consulta) {
        query = em.createQuery(consulta);
        return query.getSingleResult();
    }

    @Override
    public List getResultList(String consulta) {
        query = em.createQuery(consulta);
        return query.getResultList();
    }
    
    
    public List getResult(String consulta) {
        // SELECT e FROM Empregado e
        query = em.createQuery("SELECT e FROM Empregado e");
        
        // empregado.listar.todos -> FROM Empregado e e.nome NOT LIKE :nome
        query = em.createNamedQuery("empregado.listar.todos");
        query.setParameter("nome", "Job");
        return query.getResultList();
    }

    @Override
    public List getResultList(String consulta, Map<String, Object> parametros) {
        query = em.createQuery(consulta);
        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            String nomeParametro = entry.getKey();
            Object valorParametro = entry.getValue();
            query.setParameter(nomeParametro, valorParametro);
        }
        return query.getResultList();
    }

    @Override
    public List getResultList(String consulta, int min, int max) {
        query = em.createQuery(consulta);
        
        return query.setFirstResult(min).
                setMaxResults(max).
                getResultList();
    }

     
}
