package edu.ifpb.dac.main;

import edu.ifpb.dac.infraestrutura.Dao;
import edu.ifpb.dac.infraestrutura.DaoJPA;
import edu.ifpb.dac.model.Empregado;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ricardo Job
 */
public class ExecutorQuery {

    private static String consulta = null;
    private static Dao dao;

    public static void main(String[] args) {
        dao = new DaoJPA("consulta");
        consultaSimples();
        listarTodos();
        consultaComParametros();
        paginandoUmaConsulta();
        consultaDoNome();
        consultaUsandoConstrutor();
        consultaSemConstrutor();
        /*susandoIn();
        usandoJoin();
        usandoLeftJoin();
        consultaDistinct();
        consultaComBetween();
        consultaIsNull();
        consultaComIsEmpty();
          consultaComMemberOf(); 
         consultaComExpressoesFuncionais(); 
         expressoesFuncionaisI();
         expressoesFuncionaisII();
         agregadas();
         orderBy();
         groupBy();
         subConsultasI();
         subconsultas(); 
         consultaNomeadaSemParametro();
         consultaNomeadas(); */
    }

    private static void expressoesFuncionaisI() {
        System.out.println("--------------Usando Expressões Funcionais I -----------");
        consulta = "SELECT p "
                + "FROM Empregado p, Dependente d "
                + "WHERE LOWER(d.nome) LIKE '_h%'"
                + " AND d MEMBER OF p.dependentes"
                + " AND SQRT(d.id)=5";
        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

    private static void expressoesFuncionaisII() {
        System.out.println("--------------Usando Expressões Funcionais II -----------");
        consulta = "SELECT UPPER(p.nome) FROM Empregado p JOIN p.dependentes d";

        List<String> nomeDeps = dao.getResultList(consulta);
        for (String string : nomeDeps) {
            System.out.println(string);
        }
    }

    private static void agregadas() {
        System.out.println("--------------Usando Funções Agregadas I -----------");
        consulta = "SELECT COUNT(p) FROM Empregado p WHERE p.nome LIKE 'R%'";

        Long valor = (Long) dao.getSingleResult(consulta);
        System.out.println("Temos : " + valor);
    }

    private static void orderBy() {
        System.out.println("--------------Usando ORDER BY -----------");
        consulta = "SELECT p FROM Empregado p order by p.nome";

        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

    private static void groupBy() {
        System.out.println("--------------Usando GROUP BY -----------");
        consulta = "SELECT p.nome, COUNT(d) FROM Empregado p"
                + " LEFT JOIN p.dependentes d GROUP BY p.nome";

        List<Object[]> lista = dao.getResultList(consulta);
        for (Object[] objects : lista) {
            System.out.println("Nome: " + objects[0] + "\t Quant. Dep : " + objects[1]);
        }
    }

    private static void subConsultasI() {
        System.out.println("--------------Usando SUBCONSULTAS I  -----------");
        consulta = "Select p FROM Empregado p WHERE p.id < (SELECT MAX(p.id) FROM Empregado p)";

        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {;
            imprimeEmpregado(emp);
        }
    }

    private static void subconsultas() {
        System.out.println("--------------Usando SUBCONSULTAS II  -----------");
        consulta = "SELECT e FROM Empregado e WHERE EXISTS (SELECT d FROM e.dependentes d WHERE d.id > 2)";

        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

//    private static void consultaNomeadaSemParametro() {
//        
//        System.out.println("--------------Usando Consultas Nomeadas  -----------");
//        consulta = "Empregado.ordenado";
//        query = em.createNamedQuery(consulta);
//        List<Empregado> empregados = dao.getResultListNomeada(consulta);
//        for (Empregado emp : empregados) {
//            imprimeEmpregado(emp);
//        }
//    }
//
//    private static void consultaNomeadas() {
//        System.out.println("--------------Usando Consultas Nomeadas Parametros -----------");
//        consulta = "Empregado.nome";
//        query = em.createNamedQuery(consulta);
//        query.setParameter("nome", "Job");
//        List<Empregado> empregados = dao.getResultList(consulta);
//        for (Empregado emp : empregados) {
//            imprimeEmpregado(emp);
//        }
//    }
    private static void consultaComExpressoesFuncionais() {
        System.out.println("--------------Usando Expressões Funcionais Captura nome do Empregado E Dependente -----------");
        consulta = "SELECT LOWER(p.nome), UPPER(d.nome) "
                + "FROM Empregado p, Dependente d "
                + "WHERE d MEMBER OF p.dependentes"
                + " AND LOWER(d.nome) LIKE 'c%'";

        List<Object[]> listaString = dao.getResultList(consulta);

        for (Object[] string : listaString) {
            System.out.print("Nome do Empregado:  " + string[0]);
            System.out.println("\tNome do Dependente:  " + string[1]);
        }

        String nome = "   Ricardo Job  ";
        System.out.println("nome = " + nome.trim());
    }

    private static void consultaComMemberOf() {
        System.out.println("--------------Usando MEMBER OF e LIKE-----------");
        consulta = "SELECT p FROM Empregado p, "
                + "Dependente d WHERE p.nome LIKE "
                + "'_i%' AND d.nome LIKE 'C%' AND d MEMBER OF p.dependentes";

        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

    private static void consultaComIsEmpty() {
        System.out.println("--------------Usando IS EMPTY -----------");
        consulta = "SELECT p FROM Empregado p "
                + "WHERE p.dependentes  IS NOT EMPTY";
        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

    private static void consultaIsNull() {
        System.out.println("--------------Usando IS NULL -----------");
        consulta = "SELECT p FROM Empregado "
                + "p WHERE p.faculdade  IS NULL";
        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

    private static void consultaComBetween() {
        System.out.println("--------------Usando BETWEEN-----------");
        consulta = "SELECT p FROM Empregado p"
                + " WHERE p.id BETWEEN 1 AND 10 ";
        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

    private static void consultaDistinct() {
        System.out.println("--------------Usando DISTINCT-----------");
        consulta = "SELECT DISTINCT p FROM Empregado p, IN (p.dependentes) d";
        List<Empregado> empregados = dao.getResultList(consulta);
        for (Empregado emp : empregados) {
            imprimeEmpregado(emp);
        }
    }

    private static void usandoLeftJoin() {
        System.out.println("--------------Usando o LEFT JOIN-----------");
        consulta = "SELECT p.nome, d.nome FROM Empregado p LEFT JOIN p.dependentes d";
        List<Object[]> lista = dao.getResultList(consulta);
        for (Object[] objects : lista) {
            System.out.println("Nome: " + objects[0] + " Dep : " + objects[1]);
        }
    }

    private static void usandoJoin() {
        System.out.println("--------------Usando o JOIN-----------");
        consulta = "SELECT p.nome FROM Empregado p JOIN p.dependentes d";
        List<String> nomeDeps = dao.getResultList(consulta);
        nomeDeps.forEach(System.out::println);
    }

    private static void usandoIn() {
        System.out.println("--------------Usando o IN-----------");
        consulta = "SELECT d.nome FROM Empregado p, IN (p.dependentes) d";
        List<String> nomeDeps = dao.getResultList(consulta);
        nomeDeps.forEach(System.out::println);
    }

    private static void consultaSemConstrutor() {
        System.out.println("--------------Usando sem o construtor-----------");
        consulta = "SELECT p.id, p.nome FROM Empregado p";

        List<Object[]> listaDeObjeto = dao.getResultList(consulta);
        listaDeObjeto.forEach((objects) -> {
            Integer id = (Integer) objects[0];
            String nomes = objects[1].toString();
            System.out.println("Id:" + id + " Nome: " + nomes);
        });
    }

    private static void consultaUsandoConstrutor() {
        System.out.println("--------------Usando o construtor-----------");
        consulta = "SELECT new edu.ifpb.dac.main.EmpregadoVO"
                + "(p.id, p.nome) FROM Empregado p";
        List<EmpregadoVO> emps = dao.getResultList(consulta);
        emps.forEach(ExecutorQuery::imprimeEmpregadoVO);
    }

    private static void consultaDoNome() {
        System.out.println("--------------Selecionando apenas o nome-----------");
        consulta = "Select p.nome From Empregado p where p.id=1";

        String nome = (String) dao.getSingleResult(consulta);
        System.out.println("Nome: " + nome);
    }

    private static void paginandoUmaConsulta() {
        System.out.println("--------------Paginando uma consulta-----------");
        consulta = "From Empregado p";

        List<Empregado> empregados = dao.getResultList(consulta, 0, 1);
        empregados.forEach(ExecutorQuery::imprimeEmpregado);
    }

    private static void consultaComParametros() {
        System.out.println("--------------Passando Parametros-------------");
        consulta = "Select p From Empregado p where p.id=:identificador "
                + "AND p.nome=:nome";

        Map<String, Object> parametros = new HashMap();
        parametros.put("identificador", 1);
        parametros.put("nome", "Kiko");

        List<Empregado> empregados = dao.getResultList(consulta,
                parametros);
        empregados.forEach(ExecutorQuery::imprimeEmpregado);
    }

    private static void listarTodos() {
        System.out.println("--------------Listando Todos--------------");
        consulta = "Select e From Empregado e";
        List<Empregado> empregados = dao.getResultList(consulta);
        empregados.forEach(ExecutorQuery::imprimeEmpregado);
    }

    private static void consultaSimples() {
        System.out.println("--------------Consulta Simples--------------");
        consulta = "Select p from Empregado p where p.id=1";
        Empregado empregado = (Empregado) dao.getSingleResult(consulta);
        imprimeEmpregado(empregado);
    }

    public static void imprimeEmpregado(Empregado empregado) {
        System.out.println(empregado);
    }

    public static void imprimeEmpregadoVO(EmpregadoVO empregado) {
        System.out.println(empregado.getNome());
    }
}
