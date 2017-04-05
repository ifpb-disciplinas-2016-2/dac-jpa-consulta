package edu.ifpb.dac.aula;

import edu.ifpb.dac.model.Faculdade;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 05/04/2017, 08:57:23
 */
public class EmpregadoFaculdade {

    private String nome;
    private Faculdade faculdade;

    public EmpregadoFaculdade() {
    }

    public EmpregadoFaculdade(String nome, Faculdade faculdade) {
        this.nome = nome;
        this.faculdade = faculdade;
    }

    public String getNome() {
        return nome;
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }

    @Override
    public String toString() {
        return "EmpregadoFaculdade{" + "nome=" + nome + ", faculdade=" + faculdade.getDescricao() + '}';
    }
    
}
