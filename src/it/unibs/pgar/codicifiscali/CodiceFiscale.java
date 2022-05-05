package it.PgAr2022;
import java.util.*;
import java.util.ArrayList;
public class CodiceFiscale {
    ArrayList<String> codicivalidi = new ArrayList<String>();
    ArrayList<String> codiciInvalidi = new ArrayList<String>();

    char mesi[] = {'A','B','C', 'D', 'E', 'H', 'L', 'M', 'P','R','S','T'};

    private void aggiungiCodice(String codice) {
        codicivalidi.add(codice);
    }

    public ArrayList<String> aggiungiCodiceInvalido(String codice) {
        codiciInvalidi.add(codice);
        return codiciInvalidi;
    }


    public void controlloCodice(String codice) {
        boolean temp = false;
        if (codice.length() != 16) {
            aggiungiCodiceInvalido(codice);
        }

        String nome = codice.substring(0, 5);
        for (int i = 0; i < nome.length(); i++)
            for (int j = 0; j < 10; j++)
                if (nome.substring(i).equals(j)) {
                    aggiungiCodiceInvalido(codice);
                }
        String anno = codice.substring(6, 7);
        for (int i = 0; i < anno.length(); i++)
            for (int j = 0; j < 10; j++)
                if (anno.substring(i).equals(j)) {
                    temp = true;
                }
        if (!temp) {
            aggiungiCodiceInvalido(codice);
        }
        temp = false;
        Character mese = codice.charAt(8);
        for (int i = 0; i < mesi.length; i++)
            if (mesi[i] == mese)
                temp = true;
        if (!temp) {
            aggiungiCodiceInvalido(codice);
        }

        Integer giorno = 0;
        giorno.parseInt(codice.substring(9, 10));
        if (giorno < 0 || (giorno >= 32 && giorno < 41) || giorno > 71) {
            aggiungiCodiceInvalido(codice);

        }
        temp = false;
        Character luogolettera = codice.charAt(11);

        for (int j = 0; j < 10; j++)
            if (luogolettera == j)
                temp = true;
        if (!temp) {
            aggiungiCodiceInvalido(codice);
        }
        Integer luogonumeri = 0;
        luogonumeri.parseInt(codice.substring(12, 14));
        if (luogonumeri < 0 || luogonumeri > 999) {
            aggiungiCodiceInvalido(codice);
        }
        Character finale = codice.charAt(15);
        for (int j = 0; j < 10; j++)
            if (finale.equals(j)) {
                aggiungiCodiceInvalido(codice);
            }

        aggiungiCodice(codice);

    }

    

    public char ultimaLettera (String codice){
        int somma=0;
        int precedente = 0;
        for (int i=0; i<codice.length()/2; i++){
            for (int j=0; j<9; j++)
                if (codice.charAt(i)==j) {
                    precedente += j;
                    somma+=precedente+codice.charAt(i);
                }

        }
            //aggiungere return
        }



    public String generaNome (String nome, String codice){

        for (int i = 0; i < nome.length(); i++)
            if (nome.charAt(i) != 'a' && nome.charAt(i) != 'e' && nome.charAt(i) != 'i' && nome.charAt(i) != 'o' && nome.charAt(i) != 'u')
                codice = codice + nome.charAt(i);
        if (nome.length() < 3)
            for (int i = 0; i < nome.length(); i++)
                if (nome.charAt(i) == 'a' || nome.charAt(i) == 'e' || nome.charAt(i) == 'i' || nome.charAt(i) == 'o' || nome.charAt(i) == 'u') {
                    codice += nome.charAt(i);
                    break;
                }
        return codice;
    }

    InteragisciXML comuni = new InteragisciXML();
    public String generaCodice(Persona persona) {
        String codice = null;
        generaNome (persona.getCognome(), generaNome (persona.getNome(), codice));
        int temp = persona.getDataNascita().getAnno()%100;
        codice +=  temp;
        codice += mesi.get(persona.getDataNascita().getMese()-1);
        if (persona.getSesso() == 'M')
            codice+= persona.getDataNascita().getGiorno();
        else codice+= persona.getDataNascita().getGiorno()+40;
        //aggiungere codice del comune in base a quale nome
        return codice;
    }

}





