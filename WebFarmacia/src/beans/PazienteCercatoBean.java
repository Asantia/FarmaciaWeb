package beans;

import java.sql.Date;

/**
 * Created by csantia on 9/23/2017.
 */
public class PazienteCercatoBean {
    private String cf;
    private String nome;
    private String cognome;
    private Date datanascita;

    public PazienteCercatoBean() { }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDatanascita() {
        return datanascita;
    }

    public void setDatanascita(Date datanascita) {
        this.datanascita = datanascita;
    }
}
