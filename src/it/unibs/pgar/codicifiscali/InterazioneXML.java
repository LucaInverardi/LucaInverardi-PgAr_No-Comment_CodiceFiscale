package it.unibs.pgar.codicifiscali;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class InterazioneXML {
    //COSTANTI
    final static String INPUT_PERSONE = "inputPersone.xml";
    final static String INPUT_COMUNI = "comuni.xml";
    final static String INPUT_CODICEFISCALE = "codiciFiscali.xml";
    final static String MSG_ERRORE_INIZIALIZZAZIONE = "Errore nell'inizializzazione del reader:";
    final static String INIZIO_LETTURA_DOCUMENTI = "Inizio lettura del documento";
    final static String TAG = "Tag";
    final static String FINE_TAG = "End-Tag";
    final static String NOME = "nome";
    final static String COGNOME = "cognome";
    final static String SESSO = "sesso";
    final static String COMUNE_NASCITA = "comune_nascita";
    final static String DATA_NASCITA = "data_nascita";
    final static String ASSEGNA_ATTRIBUTO = " => attributo %s->%s%n";
    final static String NUMERO = "numero";
    final static String PERSONA = "Persona";
    final static String PERSONE = "Persone";
    final static String ID = "id";
    final static String ASSENTE = "ASSENTE";
    final static String CODICI_SPAIATI = "spaiati";
    final static String CODICI_INVALIDI = "invalidi";
    final static String CODICI = "Codici";
    final static String CODICE = "codice";
    final static String CODICE_FISCALE = "codice_fiscale";
    final static String VERSIONE = "1.0";
    final static String UTF_8 ="utf-8";
    final static String OUTPUT = "output";

    //Matrice di Comuni
    String[][] comuni = new String[8092][2];
    //ArrayList di CodiceFiscale
    private ArrayList<String> codicifiscali = new ArrayList<>();


    public void leggiPersona(ArrayList<Persona> persone) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Persona
        XMLStreamReader xmlr = inizializzaXmlStreamReader(INPUT_PERSONE);

        //Lettura file Persone
        while(xmlr.hasNext()) {


            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT ->//Stampa inizio documento
                        System.out.println(INIZIO_LETTURA_DOCUMENTI + INPUT_PERSONE);
                case XMLStreamConstants.START_ELEMENT -> {//Stampa inizio elemento
                    System.out.println(TAG + xmlr.getLocalName());
                    for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                        System.out.printf(ASSEGNA_ATTRIBUTO, xmlr.getAttributeLocalName(i),
                                xmlr.getAttributeValue(i));
                        if (xmlr.getText().trim().length() > 0) {
                            i--;
                        } else {
                            //Per ogni if aggiungo un contenuto differente
                            if (Objects.equals(xmlr.getLocalName(), NOME))
                                persone.get(i).setNome(xmlr.getText());
                            if (Objects.equals(xmlr.getLocalName(), COGNOME))
                                persone.get(i).setCognome(xmlr.getText());
                            if (Objects.equals(xmlr.getLocalName(), SESSO))
                                persone.get(i).setSesso(xmlr.getText().charAt(0));
                            if (Objects.equals(xmlr.getLocalName(), COMUNE_NASCITA))
                                persone.get(i).setComune(xmlr.getText());
                            if (Objects.equals(xmlr.getLocalName(), DATA_NASCITA))
                                persone.get(i).setDataNascita(xmlr.getText());
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT ->//Stampa fine elemento
                        System.out.println(FINE_TAG + xmlr.getLocalName());
            }
            xmlr.next();
        }
    }

    public void leggiCodiceFiscale() throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Codice
        XMLStreamReader xmlr = inizializzaXmlStreamReader(INPUT_CODICEFISCALE);

        //Lettura file Codici Fiscali
        while(xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT ->//Stampa inizio documento
                        System.out.println(INIZIO_LETTURA_DOCUMENTI + INPUT_CODICEFISCALE);
                case XMLStreamConstants.START_ELEMENT -> {//Stampa inizio elemento
                    System.out.println(TAG + xmlr.getLocalName());
                    for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                        System.out.printf(ASSEGNA_ATTRIBUTO, xmlr.getAttributeLocalName(i),
                                xmlr.getAttributeValue(i));
                        if (xmlr.getText().trim().length() > 0) {
                            i--;
                        } else {
                            codicifiscali.set(i, xmlr.getText());
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT ->//Stampa fine elemento
                        System.out.println(FINE_TAG + xmlr.getLocalName());
            }
            xmlr.next();
        }
    }
    public void leggiComuni(String[][]comuni) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Comuni
        XMLStreamReader xmlr = inizializzaXmlStreamReader(INPUT_COMUNI);

        //Lettura file Comuni
        while(xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT ->//Stampa inizio documento
                        System.out.println(INIZIO_LETTURA_DOCUMENTI + INPUT_COMUNI);
                case XMLStreamConstants.START_ELEMENT -> {//Stampa inizio elemento
                    System.out.println(TAG + xmlr.getLocalName());
                    for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                        System.out.printf(ASSEGNA_ATTRIBUTO, xmlr.getAttributeLocalName(i),
                                xmlr.getAttributeValue(i));
                        if (xmlr.getText().trim().length() > 0) {
                            i--;
                        } else {
                            for (int j = 0; j < 2; j++)
                                comuni[i][j] = xmlr.getText();
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT ->//Stampa fine elemento
                        System.out.println(FINE_TAG + xmlr.getLocalName());
            }
            xmlr.next();
        }
    }

    private XMLStreamReader inizializzaXmlStreamReader(String nomeFile) {
        XMLInputFactory xmlif;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomeFile,
                    new FileInputStream(nomeFile));
        } catch (Exception e) {
            System.out.println(MSG_ERRORE_INIZIALIZZAZIONE);
            System.out.println(e.getMessage());
        }
        return xmlr;
    }
    public void scriviXML(String filename, ArrayList<Persona> persone, ArrayList<String> codiciFiscaliInvalidi, ArrayList<String> codiciFiscaliSpaiati) {
        XMLOutputFactory xmlof;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), UTF_8);
            xmlw.writeStartDocument(UTF_8, VERSIONE);
        } catch (Exception e) {
            System.out.println(MSG_ERRORE_INIZIALIZZAZIONE);
            System.out.println(e.getMessage());
        }
        try { // blocco try per raccogliere eccezioni
            xmlw.writeStartElement(OUTPUT); // scrittura del tag radice <output>

            aggiungiPersoneXML(persone, xmlw); //stampa le persone in formato XML

            aggiungiCodiciXML(codiciFiscaliInvalidi, codiciFiscaliSpaiati, xmlw); //stampa i codici in formato XML

            xmlw.writeEndDocument(); // scrittura della fine del documento </output>
            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate

        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println(MSG_ERRORE_INIZIALIZZAZIONE);

        }

    }
    private void aggiungiPersoneXML(ArrayList<Persona> persone, XMLStreamWriter xmlw) throws XMLStreamException {
        xmlw.writeStartElement(PERSONE); //scrittura del tag <Persone>
        xmlw.writeAttribute(NUMERO, Integer.toString(persone.size()));
        for (int i = 0; i < persone.size(); i++) {
            xmlw.writeStartElement(PERSONA); // scrittura del tag Persona...
            xmlw.writeAttribute(ID, Integer.toString(i)); // ...con attributo id...

            xmlw.writeStartElement(NOME); //apertura tag <nome>
            xmlw.writeCharacters(persone.get(i).getNome()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </nome>

            xmlw.writeStartElement(COGNOME); //apertura tag <cognome>
            xmlw.writeCharacters(persone.get(i).getCognome()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </cognome>

            xmlw.writeStartElement(SESSO); //apertura tag <sesso>
            xmlw.writeCharacters(String.valueOf(persone.get(i).getSesso())); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </sesso>

            xmlw.writeStartElement(COMUNE_NASCITA); //apertura tag <comune_nascita>
            xmlw.writeCharacters(persone.get(i).getComune()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </comune_nascita>

            xmlw.writeStartElement(DATA_NASCITA); //apertura tag <data_nascita>
            xmlw.writeCharacters(persone.get(i).getDataNascita()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </data_nascita>

            if(persone.get(i).getCodiceFiscale()!= null){
                xmlw.writeStartElement(CODICE_FISCALE); //apertura tag <codice_fiscale>
                xmlw.writeCharacters(persone.get(i).getCodiceFiscale()); //contenuto tag
                xmlw.writeEndElement(); //chiusura tag </codice_fiscale>
            }else {
                xmlw.writeStartElement(CODICE_FISCALE); //apertura tag <codice_fiscale>
                xmlw.writeCharacters(ASSENTE); //contenuto tag
                xmlw.writeEndElement(); //chiusura tag </codice_fiscale>
            }
        }
        xmlw.writeEndElement(); //chiusura di </persone>
    }

    private void aggiungiCodiciXML(ArrayList<String> codiciFiscaliInvalidi,
                                   ArrayList<String> codiciFiscaliSpaiati,
                                   XMLStreamWriter xmlw) throws XMLStreamException {
        xmlw.writeStartElement(CODICI); //scrittura del tag <Codici>
        xmlw.writeStartElement(CODICI_INVALIDI); //scrittura del tag <invalidi>
        stampaCodici(codiciFiscaliInvalidi, xmlw);
        xmlw.writeEndElement(); //chiude l'ultimo tag aperto
        xmlw.writeStartElement(CODICI_SPAIATI);
        stampaCodici(codiciFiscaliSpaiati, xmlw);
        xmlw.writeEndElement(); //chiusura del tag </codici_spaiati>
        xmlw.writeEndElement(); //chiusura del tag </codici>
    }

    private void stampaCodici(ArrayList<String> codiciFiscali, XMLStreamWriter xmlw) throws XMLStreamException {
        xmlw.writeAttribute(NUMERO, Integer.toString(codiciFiscali.size()));
        for (String s : codiciFiscali) {
            xmlw.writeStartElement(CODICE); //apertura tag <codice>
            xmlw.writeCharacters(s.toUpperCase()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </codice>
        }
        xmlw.writeEndElement(); // chiusura di </invalidi o spaiati>
    }



    public String[][] getComuni() {
        return comuni;
    }

    public String getComune(int i, int j){
        return comuni[i][j];
    }
    public void setComuni(String[][] comuni) {
        this.comuni = comuni;
    }

    public ArrayList<String> getCodicifiscali() {
        return codicifiscali;
    }

    public void setCodicifiscali(ArrayList<String> codicifiscali) {
        this.codicifiscali = codicifiscali;
    }


}
