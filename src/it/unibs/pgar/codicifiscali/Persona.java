package it.unibs.pgar.codicifiscali;

import java.util.ArrayList;
public class Persona {
    //Creazione di un ArrayList di Persone



    //Attributi Classe Persona
    private String nome;
    private String cognome;
    private char sesso;
    private String comune;
    private String dataNascita;
    private String codiceFiscale;

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

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public Persona(String nome, String cognome, char sesso, String comune, String dataNascita, String codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.sesso = sesso;
        this.comune = comune;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
    }
}
