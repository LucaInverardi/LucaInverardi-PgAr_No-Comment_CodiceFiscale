package it.unibs.pgar.codicifiscali;
import it.unibs.pgar.codicifiscali.Persona;

import java.util.*;
import java.util.ArrayList;
public class CodiceFiscale {
    private ArrayList<String> codicivalidi = new ArrayList<String>();
    private ArrayList<String> codiciInvalidi = new ArrayList<String>();

    char mesi[] = {'A','B','C', 'D', 'E', 'H', 'L', 'M', 'P','R','S','T'};

    private void aggiungiCodice(String codice) {
        codicivalidi.add(codice);
    }

    public ArrayList<String> aggiungiCodiceInvalido(String codice) {
        codiciInvalidi.add(codice);
        return codiciInvalidi;
    }

    //controlla la validità del codice e lo insierisce nell'array di codici validi o invalidi
    public void controlloCodice(String codice) {
        //controllo della lunghezza
        if (codice.length()!=16) {
            aggiungiCodiceInvalido(codice);
            return;
        }
        //preso il cognome e il nome controlla che non siano numeri
        Integer nome[] = {0,0,0,0,0,0};
        for (int i=0; i<6; i++) {
            //casting che permettera di lavorare con il codice ASCII
            nome[i]= (int) codice.charAt(i);
        }

        for (int i=0; i<6; i++) {
            for (int j=48; j<58; j++)
                if (nome[i]==j) {
                    aggiungiCodiceInvalido(codice);
                    return;

                }
        }
        //anche in questo caso si effettua un casting per lavorare con i codici ASCII
        Integer anno[]= {0,0};
        anno[0]=(int)codice.charAt(6);
        anno[1]=(int)codice.charAt(7);
        boolean check = false;
        for (int i=0; i<2; i++) {
            for (int j=48; j<58; j++)
                if (anno[i]==j) {
                    check=true;
                }
        }
        if (!check) {
            aggiungiCodiceInvalido(codice);
            return;
        }
        //controlla che la lettera del mese corrisponda ad una corretta
        char mese ='0';
        mese = codice.charAt(8);
        check=false;
        for (int i=0; i<mesi.length; i++) {
            if (mese==mesi[i])
                check = true;
        }
        if (!check) {
            aggiungiCodiceInvalido(codice);
            return;
        }

        //anche in questo caso si è fatto il casting
        Integer giorno[] = {0,0};
        giorno[0]=(int) codice.charAt(9);
        giorno[1]=(int) codice.charAt(10);
        /*in giorno[0] vi è la cifra delle decine mentre in giorno[1] vi è quella delle unità
         (che si controllano solo per le decine 3 e 7)*/
        if ((giorno[0]<48 ||giorno[0]>55)|| ((giorno[0]==51 && giorno[1]>48)||(giorno[0]==55 &&giorno[1]>48))){
            aggiungiCodiceInvalido(codice);
        }
        Integer comuneLettera = 0;
        comuneLettera =(int)codice.charAt(11);
        for (int i=48; i<58; i++) {
            if (comuneLettera == i) {
                aggiungiCodiceInvalido(codice);
                return;

            }
        }
        check=false;
        //controlla che ciascun numero identificante il comune sia effettivamente un numero
        Integer comuneNumeri[]= {0,0,0};
        comuneNumeri[0]=(int)codice.charAt(12);
        comuneNumeri[1]= (int)codice.charAt(13);
        comuneNumeri[2]=(int)codice.charAt(14);

        for (int j=48; j<58; j++) {
            if (comuneNumeri[0]==j)
                check = true;
        }
        if (!check) {
            aggiungiCodiceInvalido(codice);
            return;
        }
        check=false;
        for (int j=48; j<58; j++) {
            if (comuneNumeri[1]==j)
                check = true;
        }
        if (!check) {
            aggiungiCodiceInvalido(codice);
            return;
        }
        check=false;
        for (int j=48; j<58; j++) {
            if (comuneNumeri[2]==j)
                check = true;
        }
        if (!check) {
            aggiungiCodiceInvalido(codice);
            return;
        }
        check=false;
        int ultimaLettera=0;
        ultimaLettera= (int) codice.charAt(15);
        for (int i=48; i<58; i++) {
            if (ultimaLettera == i) {
                aggiungiCodiceInvalido(codice);
                return;
            }

        }
    }





    //dato un nome ti genera la sua parte di codice fiscale. metodo ausiliario
    public static String generaNome (String nome){
        String codice ="";
        for (int i = 0; i < nome.length(); i++)
            //controllo delle consonanti
            if (nome.charAt(i) != 'A' && nome.charAt(i) != 'E' && nome.charAt(i) != 'I' && nome.charAt(i) != 'O' && nome.charAt(i) != 'U')
                codice = codice + nome.charAt(i);
        //in presenza di poche consonanti si prende la prima vocale
        if (nome.length() < 3)
            for (int i = 0; i < nome.length(); i++)
                if (nome.charAt(i) == 'A' || nome.charAt(i) == 'E' || nome.charAt(i) == 'I' || nome.charAt(i) == 'O' || nome.charAt(i) == 'U') {
                    codice += nome.charAt(i);
                    break;
                }
        return codice;
    }


    //metodo che genera l'ultima lettera del codice fiscale (secondo una tabella). metodo ausiliario
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

    //istanza che permette di usare i metodi e gli array dichiarati nell'XML
    InterazioneXML XML = new InterazioneXML();
    //data una parsona genera un codicefiscale
    public String generaCodice(Persona persona) {
        String codice = "";
        //il nome e il congome vengono generati con l'ausilio del metodo generaNome
        codice+=generaNome (persona.getCognome());
        codice+=generaNome (persona.getNome());

        //anno
        codice+= persona.getDataNascita().substring(2, 4);
        //mese: dato un numero quello corrisponde alla posizione+1 dell'array di mesi (che parte dall'indice 0 fino all'11
        Integer mese=0;
        int Mese = mese.valueOf(persona.getDataNascita().substring(5, 7));
        codice+=mesi[Mese-1];
        Integer giorno =0;
        //se la persona è femmina al giorno di nascita bisogna aggiungere 40
        if (persona.getSesso() == 'M') {
            codice+=giorno.valueOf(persona.getDataNascita().substring(8, 10));
        }
        else codice+=giorno.valueOf(persona.getDataNascita().substring(8,10))+40;
        //aggiungere codice del comune in base a quale nome
        for (int i=0; i<XML.getComuni().length; i++){
            if (XML.getComune(i, 0).equals(persona.getComune())){
                codice+=XML.getComune (i, 1);
            }
        }
        //l'ultimo carattere si genera con il metodo ausiliario
        codice+=ultimoCarattere(codice);
        return codice;
    }

    public ArrayList<String> getCodicivalidi() {
        return codicivalidi;
    }

    public void setCodicivalidi(ArrayList<String> codicivalidi) {
        this.codicivalidi = codicivalidi;
    }

    public ArrayList<String> getCodiciInvalidi() {
        return codiciInvalidi;
    }

    public void setCodiciInvalidi(ArrayList<String> codiciInvalidi) {
        this.codiciInvalidi = codiciInvalidi;
    }
}





