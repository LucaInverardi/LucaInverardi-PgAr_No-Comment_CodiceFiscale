package it.unibs.pgar.codicifiscali;

import javax.print.DocFlavor;
import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class InterazioneXML {
    //COSTANTI
    final static String INPUT_PERSONE = "inputPersone.xml";
    final static String INPUT_COMUNI = "comuni.xml";
    final static String INPUT_CODICEFISCALE = "codiciFiscali.xml";
    final static String VERSION = "1.0";
    final static String UTF_8 = "utf-8";
    final static String WRITER_ERROR = "Errore nell'inizializzazione del writer:";


    //Matrice di Comuni
    String[][] comuni = new String[8092][2];
    //ArrayList di CodiceFiscale
    ArrayList<String> codicifiscali = new ArrayList<String>();
    //ArrayList di Persone
    ArrayList<Persona> persone = new ArrayList<Persona>();
    public void leggiPersona(Persona[] persona) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Persona
        XMLStreamReader xmlr = inizializzaXmlStreamReader(INPUT_PERSONE);

        //Lettura file Persone
        while(xmlr.hasNext());
        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println("Inizio lettura del documento" + INPUT_PERSONE);
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println("Tag" + xmlr.getLocalName());
                for(int i = 0; i <xmlr.getAttributeCount(); i++) {
                    System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i),
                            xmlr.getAttributeValue(i));
                    if(xmlr.getText().trim().length() > 0){
                        i--;
                        continue;
                    }else {
                        persone.get(i).setNome(xmlr.getText());
                        persone.get(i).setCognome(xmlr.getText());
                        persone.get(i).setSesso(xmlr.getText());
                        persone.get(i).setComune(xmlr.getText());
                        persone.get(i).setDataNascita(xmlr.getText());
                        persone.get(i).setCodiceFiscale(xmlr.getText());
                    }
                }
                break;
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println("End-Tag" + xmlr.getLocalName());
                break;
        }
        xmlr.next();


    }
    public void leggiCodiceFiscale(String codice) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Codice
        XMLStreamReader xmlr = inizializzaXmlStreamReader(INPUT_CODICEFISCALE);

        //Lettura file Codici Fiscali
        while(xmlr.hasNext());
        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println("Inizio lettura del documento" + INPUT_CODICEFISCALE);
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println("Tag" + xmlr.getLocalName());
                for(int i = 0; i <xmlr.getAttributeCount(); i++) {
                    System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i),
                            xmlr.getAttributeValue(i));
                    if (xmlr.getText().trim().length() > 0) {
                        i--;
                        continue;
                    } else{
                        codicifiscali.set(i, xmlr.getText());
                    }
                }
                break;
            /*case XMLStreamConstants.CHARACTERS:
                if (xmlr.getText().trim().length() > 0)
                    System.out.println();
                break;*/
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println("End-Tag" + xmlr.getLocalName());
                break;
        }
        xmlr.next();


    }
    public void leggiComuni(String[][]comuni) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Comuni
        XMLStreamReader xmlr = inizializzaXmlStreamReader(INPUT_COMUNI);

        //Lettura file Comuni
        while(xmlr.hasNext());
        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println("Inizio lettura del documento" + INPUT_COMUNI);
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println("Tag" + xmlr.getLocalName());
                for(int i = 0; i <xmlr.getAttributeCount(); i++) {
                    System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i),
                            xmlr.getAttributeValue(i));
                    if (xmlr.getText().trim().length() > 0){
                        i--;
                        continue;

                    }else {
                        for (int j = 0; j < 2; j++)
                            comuni[i][j] = xmlr.getText();
                    }
                }
                break;
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println("End-Tag" + xmlr.getLocalName());
                break;
        }
        xmlr.next();


    }

    private XMLStreamReader inizializzaXmlStreamReader(String nomeFile) {
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(nomeFile,
                    new FileInputStream(nomeFile));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }
        return xmlr;
    }

    public String[][] getComuni() {
        return comuni;
    }

    public void setComuni(String[][] comuni) {
        this.comuni = comuni;
    }

    public void setCodicifiscali(ArrayList<String> codicifiscali) {
        this.codicifiscali = codicifiscali;
    }

    //questo metodo serve per modificare il set richiede: in che posizione e cosa
    public void setCodicefiscale(int i, String codice) {
        this.codicifiscali.add(i, codice);
    }

    public void setPersone(ArrayList<Persona> persone) {
        this.persone = persone;
    }

    public ArrayList<String> getCodicifiscali() {
        return codicifiscali;
    }

    public ArrayList<Persona> getPersone() {
        return persone;
    }

    public void scriviXML(String filename, Persona[] persone, ArrayList<String> codiciFiscaliInvalidi, ArrayList<String> codiciFiscaliSpaiati) {
        XMLOutputFactory xmlof = null;
        XMLStreamWriter xmlw = null;
        try {
            xmlof = XMLOutputFactory.newInstance();
            xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(filename), UTF_8);
            xmlw.writeStartDocument(UTF_8, VERSION);
        } catch (Exception e) {
            System.out.println(WRITER_ERROR);
            System.out.println(e.getMessage());
        }
        try { // blocco try per raccogliere eccezioni
            xmlw.writeStartElement("output"); // scrittura del tag radice <output>

            aggiungiPersoneXML(persone, xmlw); //stampa le persone in formato XML

            aggiungiCodiciXML(codiciFiscaliInvalidi, codiciFiscaliSpaiati, xmlw); //stampa i codici in formato XML

            xmlw.writeEndDocument(); // scrittura della fine del documento </output>
            xmlw.flush(); // svuota il buffer e procede alla scrittura
            xmlw.close(); // chiusura del documento e delle risorse impiegate

        } catch (Exception e) { // se c’è un errore viene eseguita questa parte
            System.out.println("Errore nella scrittura");
        }

    }

    private void aggiungiPersoneXML(Persona[] persone, XMLStreamWriter xmlw) throws XMLStreamException {
        xmlw.writeStartElement("Persone"); //scrittura del tag <Persone>
        xmlw.writeAttribute("numero", Integer.toString(persone.length));
        for (int i = 0; i < persone.length; i++) {
            xmlw.writeStartElement("Persona"); // scrittura del tag Persona...
            xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id...

            xmlw.writeStartElement("nome"); //apertura tag <nome>
            xmlw.writeCharacters(persone[i].getNome()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </nome>

            xmlw.writeStartElement("cognome"); //apertura tag <cognome>
            xmlw.writeCharacters(persone[i].getCognome()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </cognome>

            xmlw.writeStartElement("sesso"); //apertura tag <sesso>
            xmlw.writeCharacters(persone[i].getSesso()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </sesso>

            xmlw.writeStartElement("comune_nascita"); //apertura tag <comune_nascita>
            xmlw.writeCharacters(persone[i].getComune()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </comune_nascita>

            xmlw.writeStartElement("data_nascita"); //apertura tag <data_nascita>
            xmlw.writeCharacters(persone[i].getDataNascita()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </data_nascita>

            if(persone[i].getCodiceFiscale()!= null){
                xmlw.writeStartElement("codice_fiscale"); //apertura tag <codice_fiscale>
                xmlw.writeCharacters(persone[i].getCodiceFiscale()); //contenuto tag
                xmlw.writeEndElement(); //chiusura tag </codice_fiscale>
            }else {
                xmlw.writeStartElement("codice_fiscale"); //apertura tag <codice_fiscale>
                xmlw.writeCharacters("ASSENTE"); //contenuto tag
                xmlw.writeEndElement(); //chiusura tag </codice_fiscale>
            }
        }
        xmlw.writeEndElement(); //chiusura di </persone>
    }

    private void aggiungiCodiciXML(ArrayList<String> codiciFiscaliInvalidi, ArrayList<String> codiciFiscaliSpaiati, XMLStreamWriter xmlw) throws XMLStreamException {
        xmlw.writeStartElement("Codici"); //scrittura del tag <Codici>
        xmlw.writeStartElement("invalidi"); //scrittura del tag <invalidi>
        stampaCodici(codiciFiscaliInvalidi, xmlw);

        xmlw.writeStartElement("spaiati");
        stampaCodici(codiciFiscaliSpaiati, xmlw);
        xmlw.writeEndElement(); //chiusura del tag </codici>
    }

    private void stampaCodici(ArrayList<String> codiciFiscali, XMLStreamWriter xmlw) throws XMLStreamException {
        xmlw.writeAttribute("numero", Integer.toString(codiciFiscali.size()));
        for (int i=0; i<codiciFiscali.size(); i++){
            xmlw.writeStartElement("codice"); //apertura tag <codice>
            xmlw.writeCharacters(codiciFiscali.get(i).toUpperCase()); //contenuto tag
            xmlw.writeEndElement(); //chiusura tag </codice>
        }
        xmlw.writeEndElement(); // chiusura di </invalidi o spaiati>
    }


}
