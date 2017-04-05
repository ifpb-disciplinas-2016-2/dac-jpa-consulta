package edu.ifpb.dac.main;

import edu.ifpb.dac.model.Empregado;
import edu.ifpb.dac.model.Faculdade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/03/2016, 07:35:20
 */
public class ExecutorTestesAula {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("consulta");
        EntityManager em = emf.createEntityManager();

//        String consulta = "Select p.id, p.nome From Empregado p";
//        TypedQuery<String> query = em.createQuery(consulta, String.class);      
//        List<String> lista = query.getResultList();
//        lista.forEach(System.out::println);
//        TypedQuery<Object[]> query = em.createQuery(consulta, Object[].class);
//        List<Object[]> lista = query.getResultList();
//        
//        List<EmpregadoVO> list = new ArrayList<>();
//        for (Object[] objects : lista) {
//            System.out.print("id = " + objects[0]);
//            System.out.println("\tNome = " + objects[1]);
//            list.add(new EmpregadoVO((int) objects[0], (String) objects[1]));
//        }
//        String consulta = "Select "
//                + "new edu.ifpb.dac.main.ExecutorTestesAula."
//                + "DependenteVO(p.nome, d.nome) "
//                + "From Empregado p LEFT JOIN p.dependentes d";
////        String consulta = "Select "
//                + "new edu.ifpb.dac.main.ExecutorTestesAula."
//                + "EmpregadoVO(p.id, d.nome) "
//                + "From Empregado p, IN (p.dependentes) d";
//        TypedQuery<DependenteVO> query = em.createQuery(consulta, 
//                DependenteVO.class);
//        
//        List<DependenteVO> resultList = query.getResultList();
//        
//        resultList.forEach(System.out::println);
//        String consulta = "Select p.faculdade.descricao From Empregado p where p.id=1";
//        String consulta = "Select d.nome From Empregado e,"
//                + "IN(e.dependentes) d";
////                + " where e.id=1";
//        
//        TypedQuery<String> createQuery = em.createQuery(consulta, String.class);
//        List<String> resultList = createQuery.getResultList();
//        resultList.forEach(System.out::println);
//        String consulta = "Select DISTINCT p "
//                + "From Empregado p JOIN p.dependentes d";
//        
//        TypedQuery<Empregado> createQuery = em.createQuery(consulta, Empregado.class);
//        List<Empregado> lista = createQuery.getResultList();
//        lista.forEach(System.out::println);
        
        
        String consulta = "Select e FROM Empregado e WHERE e.faculdade=:f";
        TypedQuery<Empregado> createQuery = em.createQuery(consulta, Empregado.class);
        createQuery.setParameter("f", new Faculdade(2, "CEFETPB"));
        List<Empregado> lista = createQuery.getResultList();
        lista.forEach(System.out::println);
        
        
    }   

    static class EmpregadoVO {

        private int id;
        private String nome;

        public EmpregadoVO(int id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return "EmpregadoVO{" + "id=" + id + ", nome=" + nome + '}';
        }

    }

    static class DependenteVO {

        private String id;
        private String nome;

        public DependenteVO(String id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public String getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return "EmpregadoVO{" + "id=" + id + ", nome=" + nome + '}';
        }

    }

}
