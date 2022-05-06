package it.unibs.pgar.codicifiscali;
import it.unibs.pgar.codicifiscali.Persona;

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

    //rifare
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





    //giusto
    public static String generaNome (String nome){
        String codice ="";
        for (int i = 0; i < nome.length(); i++)
            if (nome.charAt(i) != 'A' && nome.charAt(i) != 'E' && nome.charAt(i) != 'I' && nome.charAt(i) != 'O' && nome.charAt(i) != 'U')
                codice = codice + nome.charAt(i);
        if (nome.length() < 3)
            for (int i = 0; i < nome.length(); i++)
                if (nome.charAt(i) == 'A' || nome.charAt(i) == 'E' || nome.charAt(i) == 'I' || nome.charAt(i) == 'O' || nome.charAt(i) == 'U') {
                    codice += nome.charAt(i);
                    break;
                }
        return codice;
    }

    InterazioneXML comuni = new InterazioneXML();

    public static char ultimoCarattere(String codice) {
        boolean pari = false;
        int somma=0;
        for (int i=0; i<codice.length(); i++) {
            if (pari) {
                switch (codice.charAt(i)) {
                    case '0':
                        somma += 0;
                        pari =false;
                        break;
                    case '1':
                        somma+=1;
                        pari =false;
                        break;
                    case '2':
                        somma+=2;
                        pari =false;
                        break;
                    case '3':
                        somma+=3;
                        pari = false;
                    case '4':
                        somma+=4;
                        pari =false;
                        break;
                    case '5':
                        somma+=5;
                        pari =false;
                        break;
                    case '6':
                        somma+=6;
                        pari =false;
                        break;
                    case '7':
                        somma+=7;
                        pari =false;
                        break;
                    case '8':
                        somma+=8;
                        pari =false;
                        break;
                    case '9':
                        somma+=9;
                        pari =false;
                        break;
                    case 'A':
                        somma+=0;
                        pari = false;
                        break;
                    case 'B':
                        somma+=1;
                        pari = false;
                        break;
                    case 'C':
                        somma+=2;
                        pari = false;
                        break;
                    case 'D':
                        somma+=3;
                        pari = false;
                        break;
                    case 'E':
                        somma+=4;
                        pari = false;
                        break;
                    case 'F':
                        somma+=5;
                        pari = false;
                        break;
                    case 'G':
                        somma+=6;
                        pari = false;
                        break;
                    case 'H':
                        somma+=7;
                        pari = false;
                        break;
                    case 'I':
                        somma+=8;
                        pari = false;
                        break;
                    case 'J':
                        somma+=9;
                        pari = false;
                        break;
                    case 'K':
                        somma+=10;
                        pari = false;
                        break;
                    case 'L':
                        somma+=11;
                        pari = false;
                        break;
                    case 'M':
                        somma+=12;
                        pari = false;
                        break;
                    case 'N':
                        somma+=13;
                        pari = false;
                        break;
                    case 'O':
                        somma+=14;
                        pari = false;
                        break;
                    case 'P':
                        somma+=15;
                        pari = false;
                        break;
                    case 'Q':
                        somma+=16;
                        pari = false;
                        break;
                    case 'R':
                        somma+=17;
                        pari = false;
                        break;

                    case 'S':
                        somma+=18;
                        pari = false;
                        break;
                    case 'T':
                        somma+=19;
                        pari = false;
                        break;
                    case 'U':
                        somma+=20;
                        pari = false;
                        break;
                    case 'V':
                        somma+=21;
                        pari = false;
                        break;
                    case 'W':
                        somma+=22;
                        pari = false;
                        break;
                    case 'X':
                        somma+=23;
                        pari = false;
                        break;
                    case 'Y':
                        somma+=24;
                        pari = false;
                        break;
                    case 'Z':
                        somma+=25;
                        pari = false;
                        break;

                }

            }
            else {
                switch (codice.charAt(i)) {
                    case '0':
                        somma += 1;
                        pari =true;
                        break;
                    case '1':
                        somma+=0;
                        pari =true;
                        break;
                    case '2':
                        somma+=5;
                        pari =true;
                        break;
                    case '3':
                        somma+=7;
                        pari = true;
                    case '4':
                        somma+=9;
                        pari =true;
                        break;
                    case '5':
                        somma+=13;
                        pari =true;
                        break;
                    case '6':
                        somma+=15;
                        pari =true;
                        break;
                    case '7':
                        somma+=17;
                        pari =true;
                        break;
                    case '8':
                        somma+=19;
                        pari =true;
                        break;
                    case '9':
                        somma+=21;
                        pari =true;
                        break;
                    case 'A':
                        somma+=1;
                        pari = true;
                        break;
                    case 'B':
                        somma+=0;
                        pari = true;
                        break;
                    case 'C':
                        somma+=5;
                        pari = true;
                        break;
                    case 'D':
                        somma+=7;
                        pari = true;
                        break;
                    case 'E':
                        somma+=9;
                        pari = true;
                        break;
                    case 'F':
                        somma+=13;
                        pari = true;
                        break;
                    case 'G':
                        somma+=15;
                        pari = true;
                        break;
                    case 'H':
                        somma+=17;
                        pari = true;
                        break;
                    case 'I':
                        somma+=19;
                        pari = true;
                        break;
                    case 'J':
                        somma+=21;
                        pari = true;
                        break;
                    case 'K':
                        somma+=2;
                        pari = true;
                        break;
                    case 'L':
                        somma+=4;
                        pari = true;
                        break;
                    case 'M':
                        somma+=18;
                        pari = true;
                        break;
                    case 'N':
                        somma+=20;
                        pari = true;
                        break;
                    case 'O':
                        somma+=11;
                        pari = true;
                        break;
                    case 'P':
                        somma+=3;
                        pari = true;
                        break;
                    case 'Q':
                        somma+=6;
                        pari = true;
                        break;
                    case 'R':
                        somma+=8;
                        pari = true;
                        break;

                    case 'S':
                        somma+=12;
                        pari = true;
                        break;
                    case 'T':
                        somma+=14;
                        pari = true;
                        break;
                    case 'U':
                        somma+=16;
                        pari = true;
                        break;
                    case 'V':
                        somma+=10;
                        pari = true;
                        break;
                    case 'W':
                        somma+=22;
                        pari = true;
                        break;
                    case 'X':
                        somma+=25;
                        pari = true;
                        break;
                    case 'Y':
                        somma+=24;
                        pari = true;
                        break;
                    case 'Z':
                        somma+=23;
                        pari = true;
                        break;

                }
            }

        }
        somma%=26;
        char lettera='A';
        for (int i=0; i<26; i++) {
            if (somma ==i)
                return lettera;
            lettera++;
        }
        return '0';
    }

    //giusto
    public String generaCodice(Persona persona) {
        String codice = "";
        codice+=generaNome (persona.getCognome());
        codice+=generaNome (persona.getNome());
        //generaNome (persona.getCognome(), generaNome (persona.getNome(), codice));
        //anno
        codice+= persona.getDataNascita().substring(2, 4);
        //mese:
        Integer mese=0;
        int Mese = mese.valueOf(persona.getDataNascita().substring(5, 7));
        codice+=mesi[Mese-1];
        Integer giorno =0;
        if (persona.getSesso() == 'M') {
            codice+=giorno.valueOf(persona.getDataNascita().substring(8, 10));
        }
        else codice+=giorno.valueOf(persona.getDataNascita().substring(8,10))+40;
        codice+="E801";
        //aggiungere codice del comune in base a quale nome
        codice+=ultimoCarattere(codice);
        return codice;
    }

}





