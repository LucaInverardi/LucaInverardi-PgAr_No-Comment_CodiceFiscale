package it.unibs.pgar.codicifiscali;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws XMLStreamException {
        //Leggi file contenente le persone da cui estrapolare il codice
         /*metodo che scorre XML e salva i dati in un array di persone*/
        ArrayList<Persona> persone = new ArrayList<>();
        ArrayList <String> codicispaiati = new ArrayList<>();
        String codiciFiscali = "codiciFiscali.xml";
        InterazioneXML interagisciXML = new InterazioneXML();
        interagisciXML.leggiPersona(persone);
        //genera codici fiscali
        /*inserito in un ciclo for lungo come la quantità di persone, passandogli l'iesima persona
         * crea l'iesimo codice e lo aggiunge come attributo dell'iesima persona*/
        CodiceFiscale codice = new CodiceFiscale();
        for (int i=0; i<persone.size(); i++){
            persone.get(i).setCodiceFiscale(codice.generaCodice(persone.get(i)));
        }
        //1 -> scorre il file XML contenente i codicifiscali e li inserisce in un vettore di codicifiscali da controllare, togliere invalidi
        for (int i=0; i<interagisciXML.getCodicifiscali().size(); i++)
            codice.controlloCodice(interagisciXML.getCodicifiscali().get(i));

        //2 -> controlla se l'iesimo codice passatogli già esiste, di quelli giusti a quanti posso associare
        boolean temp = false;
        for (int i=0; i<codice.getCodicivalidi().size(); i++){
            for (int j=0; j<persone.size(); j++) {
                if (codice.getCodicivalidi().get(i).equals(persone.get(j).getCodiceFiscale())) {
                    temp = true;
                }
            }
            if (!temp)
                codicispaiati.add(codice.getCodicivalidi().get(i));

        }




        interagisciXML.leggiCodiceFiscale();

        //genera file xml di output con persone + relativi codici e codici errati
         /* passa l'iesima persona e crea la stampa del file xml
         * una volta finito la 1° stampa, aggiunge gli altri file*/
        interagisciXML.scriviXML("codiciPersone", persone, codice.getCodiciInvalidi(), codicispaiati);


    }
}
