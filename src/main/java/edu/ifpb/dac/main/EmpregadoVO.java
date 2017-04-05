package edu.ifpb.dac.main;

/**
 *
 * @author Ricardo Job
 */
public class EmpregadoVO {

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
}
