package edu.ifpb.dac.main;

import edu.ifpb.dac.model.Empregado;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author Ricardo Job
 */
public class ExecutorCriteriaQuery {

    public static void main(String[] args) {

        EntityManager em = Persistence.createEntityManagerFactory("consultas").createEntityManager();

        System.out.println("--------------Listando Todos--------------");
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Empregado> criteria = builder.createQuery(Empregado.class);
        Root<Empregado> root = criteria.from(Empregado.class);
        criteria.select(root);

        TypedQuery<Empregado> query = em.createQuery(criteria);

        List<Empregado> empregados = query.getResultList();
        empregados.forEach(System.out::println);
//        for (Empregado emp : empregados) {
//            imprimeEmpregado(emp);
//        }

        System.out.println("--------------Passando Parametros-------------");
        criteria.select(root).where(builder.equal(root.get("id"), 9));
        query = em.createQuery(criteria);
        empregados = query.getResultList();
        empregados.forEach(System.out::println);
//        for (Empregado emp : empregados) {
//            imprimeEmpregado(emp);
//        }

        System.out.println("--------------Paginando uma consulta-----------");
        criteria = builder.createQuery(Empregado.class);
        root = criteria.from(Empregado.class);
        criteria.select(root);
        query = em.createQuery(criteria);
        query.setMaxResults(2).setFirstResult(1);
        empregados = query.getResultList();
        empregados.forEach(System.out::println);
//        for (Empregado emp : empregados) {
//            imprimeEmpregado(emp);
//        }

//        Predicate condition = builder.equal(root.get("id"), 1);
//        Predicate condition2 = builder.equal(root.get("nome"), "Job");
//        Predicate condition3 = builder.and(condition, condition2);
//        criteria.where(condition3);
        System.out.println("--------------Selecionando apenas o nome-----------");
        CriteriaQuery<String> criteriaString = builder.createQuery(String.class);
        root = criteriaString.from(Empregado.class);
        criteriaString.select(root.<String>get("nome"))
                .where(builder.equal(root.get("id"), 6));
        TypedQuery<String> queryString = em.createQuery(criteriaString);
        String nome = queryString.getSingleResult();

        System.out.println("Nome: " + nome);

        System.out.println("--------------Usando o construtor I -----------");
        CriteriaQuery<EmpregadoVO> criteriaVO = builder.createQuery(EmpregadoVO.class);
        Root<Empregado> emp = criteriaVO.from(Empregado.class);
        criteriaVO.multiselect(emp.get("id"), emp.get("nome"));
        TypedQuery<EmpregadoVO> queryVO = em.createQuery(criteriaVO);
        List<EmpregadoVO> emps = queryVO.getResultList();
        for (EmpregadoVO empregadoVO : emps) {
            imprimeEmpregadoVO(empregadoVO);
        }

        System.out.println("--------------Usando o construtor II -----------");
        criteriaVO.select(builder.construct(EmpregadoVO.class, emp.get("id"), emp.get("nome")));
        queryVO = em.createQuery(criteriaVO);
        emps = queryVO.getResultList();
        for (EmpregadoVO empregadoVO : emps) {
            imprimeEmpregadoVO(empregadoVO);
        }

        System.out.println("--------------Usando o construtor III e ALIAS-----------");
        CriteriaQuery<Tuple> criteriaTuple = builder.createTupleQuery();
        emp = criteriaTuple.from(Empregado.class);
        criteriaTuple.select(builder.tuple(emp.get("id").alias("id"), emp.get("nome").alias("nome")));
        TypedQuery<Tuple> tuple = em.createQuery(criteriaTuple);

        for (Tuple t : tuple.getResultList()) {
            Integer id = t.get("id", Integer.class);
            String nomeSimples = t.get("nome", String.class);
            System.out.println("Id: " + id + " - Nome: " + nomeSimples);
        }
//
        System.out.println("--------------Usando o IN, JOIN,DISTINCT e LEFT JOIN -----------");
        root = criteria.from(Empregado.class);
        root.join("dependentes");
        //Usando DISTINCT
        criteria.distinct(true);//Caso não utilize, executa o LEFT JOIN
        criteria.select(root);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Empregado empregado : empregados) {
            imprimeEmpregado(empregado);
        }
//

//        criteria.add(cb.like(emp.<String>get("nome"), p));
//        criteria.add(cb.like(emp.get(Empregado_.nome), p));
        System.out.println("--------------Usando BETWEEN-----------");
        root = criteria.from(Empregado.class);
        Metamodel m = em.getMetamodel();
        EntityType<Empregado> empMeta = m.entity(Empregado.class);
        Integer inicio = 1;
        Integer fim = 10;
        criteria.where(builder.between(root.get(
                empMeta.getSingularAttribute("id", Integer.class)),
                inicio, fim));
        criteria.select(root);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Empregado empregado : empregados) {
            imprimeEmpregado(empregado);
        }

        System.out.println("--------------Usando IS NULL -----------");
        root = criteria.from(Empregado.class);
        criteria.where(builder.isNull(
                root.get(
                        empMeta.getSingularAttribute("faculdade"))));
        criteria.select(root);
        query = em.createQuery(criteria);
        empregados = query.getResultList();

        for (Empregado empregado : empregados) {
            imprimeEmpregado(empregado);
        }

    }

    public static void imprimeEmpregado(Empregado empregado) {
        System.out.println(empregado.getNome());

    }

    public static void imprimeEmpregadoVO(EmpregadoVO empregado) {
        System.out.println(empregado.getId() + " - " + empregado.getNome());

    }
}
