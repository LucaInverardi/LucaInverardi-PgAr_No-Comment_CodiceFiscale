package it.unibs.pgar.codicifiscali;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class InterazioneXML {

    public static final int N_COD_FISC_FILE = 1000;
    public static final String READER_ERROR = "Errore nell'inizializzazione del reader:";
    public static final boolean WRITER_ERROR = false;
    public static final String UTF_8 = "utf-8";
    public static final String VERSION = "1.0";
    public static final int N_COMUNI_FILE = 8092;
    public static final int INT = 2;

    //Matrice di Comuni
    String[][] comuni = new String[N_COMUNI_FILE][INT];

    //ArrayList di Persone
    ArrayList<Integer> Persone = new ArrayList<Integer>();

    public void leggiPersona(Persona[] persona) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Persona
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader("inputPersone.xml",
                    new FileInputStream("inputPersone.xml"));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        //Lettura file Persone
        while (xmlr.hasNext()) ;
        switch (xmlr.getEventType()) {
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println("Inizio lettura del documento" + "inputPersone.xml");
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println("Tag" + xmlr.getLocalName());
                for (int i = 0; i < xmlr.getAttributeCount(); i++)
                    System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i),
                            xmlr.getAttributeValue(i));
                //prendere il contenuto delle persone e spostarle nell'array di persone
                break;
            case XMLStreamConstants.CHARACTERS:
                if (xmlr.getText().trim().length() > 0)
                    System.out.println();
                break;
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println("End-Tag" + xmlr.getLocalName());
                break;
        }
        xmlr.next();
    }

    public void leggiCodiceFiscale(String codice) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Codice
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader("codiciFiscali.xml",
                    new FileInputStream("codiciFiscali.xml"));
        } catch (Exception e) {
            System.out.println("Errore nell'inizializzazione del reader:");
            System.out.println(e.getMessage());
        }

        //Lettura file Codici Fiscali
        while (xmlr.hasNext()) ;
        switch (xmlr.getEventType()) {
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println("Inizio lettura del documento" + "inputPersone.xml");
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println("Tag" + xmlr.getLocalName());
                for (int i = 0; i < xmlr.getAttributeCount(); i++) {
                    System.out.println(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i),
                            xmlr.getAttributeValue(i));
                }
                break;
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println("End-Tag" + xmlr.getLocalName());
                break;
        }
        xmlr.hasNext();


    }

    public String[] leggiCodiciFiscali(String fileName) throws XMLStreamException {
        String[] vettoreCodiciFiscali = new String[N_COD_FISC_FILE];
        int i = 0;
        XMLInputFactory xmlif;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(fileName, new FileInputStream(fileName));
        } catch (Exception e) {
            System.out.println(READER_ERROR);
            System.out.println(e.getMessage());

        }
        while (xmlr.hasNext()) {
            switch (xmlr.getEventType()) {
                case XMLStreamConstants.START_DOCUMENT: // inizio del documento: non fa nulla
                    break;
                case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: non fa nulla
                    break;
                case XMLStreamConstants.END_ELEMENT: // fine di un elemento: ogni volta che trova un fine tag aumenta i di 1
                    i++;
                    break;
                case XMLStreamConstants.COMMENT:
                    break; // commento: non fa nulla
                case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: lo aggiunge al vettoreCodiciFiscali
                    if (xmlr.getText().trim().length() > 0) { // controlla se il testo non contiene solo spazi
                        vettoreCodiciFiscali[i] = xmlr.getText();  //il testo trovato lo aggiunge al vettore al posto i
                    }
                    break;
            }
            xmlr.next();
        }
        return vettoreCodiciFiscali; //restituisce il vettore contenente tutti i codici fiscali da controllare
    }
    //Davide: non avevo visto che avevi già fatto tu il metodo leggicodicifiscali, sorry!

    public void scriviXML(String filename, Persona persona) {
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
    }

    /*public void leggiComuni(String[8092][2]){
    //Inizializzazione della lettura del file XML di Comuni
            XMLInputFactory xmlif = null;
            XMLStreamReader xmlr = null;
            try {
                xmlif = XMLInputFactory.newInstance();
                xmlr = xmlif.createXMLStreamReader("comuni.xml",
                        new FileInputStream("comuni.xml"));
            } catch (Exception e) {
                System.out.println("Errore nell'inizializzazione del reader:");
                System.out.println(e.getMessage());
                }

    }*/
}
