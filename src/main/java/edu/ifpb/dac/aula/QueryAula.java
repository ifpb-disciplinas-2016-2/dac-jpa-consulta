package edu.ifpb.dac.aula;

import edu.ifpb.dac.model.Empregado;
import edu.ifpb.dac.model.Faculdade;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import static org.eclipse.persistence.jpa.JpaHelper.createQuery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 05/04/2017, 07:24:48
 */
public class QueryAula {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("consulta");
        EntityManager em = emf.createEntityManager();
//        consultarTodosOsEmpregados(em);
//        consultarFaculdadeComId(em);
//        consultarFaculdadeComIdParametros(em);
//        consultarFaculdadeComPaginacao(em);
//        consultarNomeDoEmpregado(em);
//        consultarNomeDoEmpregadoFaculdade(em);
        consultarNomeDoEmpregadoFaculdadeTipada(em);
    }

    private static void consultarTodosOsEmpregados(EntityManager em) {
        TypedQuery<Empregado> createQuery = em.createQuery("SELECT e FROM Empregado e",
                Empregado.class);
        List<Empregado> resultList = createQuery.getResultList();
        resultList.forEach(emp -> {
            System.out.println(emp.getNome());
            System.out.println(emp.getFaculdade().getDescricao());
        });
    }

    private static void consultarFaculdadeComId(EntityManager em) {
        String consulta = "SELECT f FROM Faculdade f WHERE f.id=23";

        TypedQuery<Faculdade> createQuery = em.createQuery(consulta,
                Faculdade.class);
        try {
            Faculdade faculdade = createQuery.getSingleResult();

            System.out.println("faculdade = " + faculdade.getDescricao());
        } catch (NonUniqueResultException | NoResultException e) {
            System.out.println("muitas entidades no resultado");
        }
    }

    private static void consultarFaculdadeComIdParametros(EntityManager em) {
        long id = 23l;
//        String consulta = "SELECT f FROM Faculdade f WHERE f.id=:id";
        String consulta = "SELECT f FROM Faculdade f WHERE f.id=?1";
        TypedQuery<Faculdade> createQuery = em.createQuery(consulta,
                Faculdade.class);
        createQuery.setParameter(1, id);
        Faculdade faculdade = createQuery.getSingleResult();
        System.out.println("faculdade = " + faculdade.getDescricao());
    }

    private static void consultarFaculdadeComPaginacao(EntityManager em) {

        Long total = em.createQuery("SELECT COUNT(f) FROM Faculdade f",
                Long.class).getSingleResult();

        String consulta = "SELECT f FROM Faculdade f";
        TypedQuery<Faculdade> createQuery = em.createQuery(consulta,
                Faculdade.class);

        int tamanho = 3;
        long quantidadeDePagina = total / tamanho;

        for (int i = 0; i <= quantidadeDePagina; i++) {
            System.out.println(String.format("página: %d ", (i + 1)));
            query(createQuery, i * tamanho, tamanho);
        }

    }

    private static void query(TypedQuery<Faculdade> createQuery, int inicial, int tamanho) {
        createQuery.setFirstResult(inicial).setMaxResults(tamanho);

        createQuery.getResultList().forEach(faculdade -> System.out.println(faculdade.getDescricao()));
    }

    private static void consultarNomeDoEmpregado(EntityManager em) {
        String consulta = "SELECT e.nome FROM Empregado e";
        TypedQuery<String> createQuery = em.createQuery(consulta, String.class);
        createQuery.getResultList().forEach(System.out::println);
    }

    private static void consultarNomeDoEmpregadoFaculdade(EntityManager em) {
        String consulta = "SELECT e.id, e.faculdade FROM Empregado e";
        Query createQuery = em.createQuery(consulta);

        List<Object[]> resultList = createQuery.getResultList();
        for (Object[] tupla : resultList) {
            System.out.println(Arrays.toString(tupla));
            System.out.println(tupla[0] + " - " + tupla[1]);
        }
    }
    private static void consultarNomeDoEmpregadoFaculdadeTipada(EntityManager em) {
        String consulta = "SELECT new edu.ifpb.dac.aula.EmpregadoFaculdade(e.nome, e.faculdade) "
                + "FROM Empregado e";
        TypedQuery<EmpregadoFaculdade> createQuery = em.createQuery(consulta, 
                EmpregadoFaculdade.class);
        createQuery.getResultList().forEach(System.out::println);
    }

}
