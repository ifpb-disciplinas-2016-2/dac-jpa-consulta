package edu.ifpb.dac.aula.chave;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/04/2017, 11:26:34
 */
public class PrincipalChaveComposta {
    public static void main(String[] args) {
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("consulta");
        EntityManager em = emf.createEntityManager();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(34);
        pessoa.setNome("Kiko");
        pessoa.addTelefone("1234");
        pessoa.addTelefone("134242");
        
        em.getTransaction().begin();
        em.persist(pessoa);
        em.getTransaction().commit();
    }
}
