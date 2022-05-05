package it.unibs.pgar.codicifiscali;

import java.util.ArrayList;

public class CodiceFiscale {
    //Programma con metodi per creazione dei Codici Fiscali

    ArrayList<Character> mesi = new ArrayList<Character>();

    public boolean controlloCodice(String codice){
        //aggiungere vari metodi substring che controllano i vari pezzi del codice che gli passiamo
        //restituisce true se è corretto false se non lo è
    }

    public void aggiungiCodiceInvalido(String codiceDaAggiungere, String[] codiciInvalidi){
        //a questo metodo passiamo un codice invalido(determinato dal metodo controllo codice) e lo aggiungiamo al vettore dei codici invalidi

    }

    public String generaCodice(Persona persona){
        //questo metodo riceve in entrata i dati di una persona e fa le opportune trasformazioni per ottenere il codice fiscale

    }

    private void aggiungiCodice(String codiceDaAggiungere, String[] codici){
        //questo metodo riceve in entrata un codice fiscale corretto e lo aggiunge al vettore dei codici fiscali corretti

    }



}
