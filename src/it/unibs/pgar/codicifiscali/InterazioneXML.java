package it.unibs.pgar.codicifiscali;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;
import java.util.ArrayList;

public class InterazioneXML {
    //Matrice di Comuni
    String[][] comuni = new String[8092][2];

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
        while(xmlr.hasNext());
        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println("Inizio lettura del documento" + "inputPersone.xml");
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println("Tag" + xmlr.getLocalName());
                for(int i = 0; i <xmlr.getAttributeCount(); i++)
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
        while(xmlr.hasNext());
        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println("Inizio lettura del documento" + "inputPersone.xml");
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println("Tag" + xmlr.getLocalName());
                for(int i = 0; i <xmlr.getAttributeCount(); i++);
                System.out.println(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i),
                        xmlr.getAttributeValue(i));

                break;
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println("End-Tag" + xmlr.getLocalName());
                break;
        }
        xmlr.hasNext();


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
