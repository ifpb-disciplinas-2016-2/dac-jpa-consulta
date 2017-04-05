package edu.ifpb.dac.main;

import edu.ifpb.dac.infraestrutura.Dao;
import edu.ifpb.dac.infraestrutura.DaoJPA;
import edu.ifpb.dac.model.Dependente;
import edu.ifpb.dac.model.Empregado;
import edu.ifpb.dac.model.Endereco;
import edu.ifpb.dac.model.Faculdade;
import edu.ifpb.dac.model.Projeto;

/**
 *
 * @author Ricardo Job
 */
public class CadastraEmpregado {

//    private static EntityManager em = Persistence.createEntityManagerFactory("consulta").createEntityManager();

    public static void main(String[] args) {
        
        Dao dao = new DaoJPA("consulta");
        Empregado empregado = new Empregado();
        Endereco endereco = new Endereco("Sua rua", "Seu Bairro", empregado);
        Dependente dependente = new Dependente("Alguem");
        empregado.setNome("Kiko");
        empregado.addDep(dependente);
        empregado.setEndereco(endereco);
        Projeto projeto = new Projeto();
        projeto.setDescricao("Google");
        projeto.addEmpregado(empregado);
        empregado.addProj(projeto);
        Faculdade faculdade = new Faculdade("IFPB");
        empregado.setFaculdade(faculdade);
        faculdade.setDescricao("CEFETPB");

        dao.save(empregado);
    }

    

     

     
}
