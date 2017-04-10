package edu.ifpb.dac.aula.chave;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/04/2017, 11:22:36
 */
@Entity
@IdClass(TelefonePk.class)
public class Telefone implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Id
    private int pessoa_id;

    private String numero;

    public Telefone(int id, int pessoa_id, String numero) {
        this.id = id;
        this.pessoa_id = pessoa_id;
        this.numero = numero;
    }

    public Telefone(int id, String numero) {
        this.id = id;
        this.numero = numero;
    }

    public Telefone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPessoa_id() {
        return pessoa_id;
    }

    public void setPessoa_id(int pessoa_id) {
        this.pessoa_id = pessoa_id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
