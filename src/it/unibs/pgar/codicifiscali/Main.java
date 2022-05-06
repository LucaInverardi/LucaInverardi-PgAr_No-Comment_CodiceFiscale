package it.unibs.pgar.codicifiscali;

import javax.xml.stream.XMLStreamException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws XMLStreamException {
        //Leggi file contenente le persone da cui estrapolare il codice
         /*metodo che scorre XML e salva i dati in un array di persone*/
        ArrayList<Persona> persone = new ArrayList<>();
        String codiciFiscali = "codiciFiscali.xml";
        InterazioneXML interagisciXML = new InterazioneXML();
        interagisciXML.leggiPersona(persone);

        //genera codici fiscali
         /*inserito in un ciclo for lungo come la quantità di persone, passandogli l'iesima persona
         * crea l'iesimo codice e lo aggiunge come attributo dell'iesima persona*/


        //controllo che ci siano i codici fiscali nell'altro file XML
         /*3 metodi:
         * 1 -> scorre il file XML contenente i codicifiscali e li inserisce in un vettore di codicifiscali da controllare
         * 2 -> controlla se l'iesimo codice passatogli già esiste
         * 3 -> il codice che non esiste lo tiene nel vettore da controllare e lo stamperà in coda al file xml di
         * output come codice errato*/


        interagisciXML.leggiCodiceFiscale(codiciFiscali);

        //genera file xml di output con persone + relativi codici e codici errati
         /* passa l'iesima persona e crea la stampa del file xml
         * una volta finito la 1° stampa, aggiunge gli altri file*/

        interagisciXML.

    }
}
