package edu.ifpb.dac.aula.chave;

import java.io.Serializable;
import javax.persistence.Column;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 10/04/2017, 11:23:39
 */
public class TelefonePk implements Serializable{

    public TelefonePk() {
    }

    private int id;
    @Column(name = "pessoa_id")
    private int pessoa_id;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.id;
        hash = 79 * hash + this.pessoa_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TelefonePk other = (TelefonePk) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.pessoa_id != other.pessoa_id) {
            return false;
        }
        return true;
    }

}
