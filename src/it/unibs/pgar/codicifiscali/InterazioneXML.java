package it.unibs.pgar.codicifiscali;

import javax.print.DocFlavor;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamConstants;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class InterazioneXML {
    //COSTANTI
    final static String INPUT_PERSONE = "inputPersone.xml";
    final static String INPUT_COMUNI = "comuni.xml";
    final static String INPUT_CODICEFISCALE = "codiciFiscali.xml";
    final static String MSG_ERRORE_INIZIALIZZAZIONE = "Errore nell'inizializzazione del reader:";
    final static String INIZIO_LETTURA_DOCUMENTI = "Inizio lettura del documento";
    final static String TAG = "Tag";
    final static String FINE_TAG = "End-Tag";

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
        String nome = "nome";
        String cognome = "cognome";
        String sesso = "sesso";
        String comune_nascita = "comune_nascita";
        String data_nascita = "data_nascita";


        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println(INIZIO_LETTURA_DOCUMENTI + INPUT_PERSONE);
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println(TAG + xmlr.getLocalName());
                for(int i = 0; i <xmlr.getAttributeCount(); i++) {
                    System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i),
                            xmlr.getAttributeValue(i));
                    if(xmlr.getText().trim().length() > 0){
                        i--;
                        continue;
                    }else {
                        //Per ogni if aggiungo un contenuto differente
                        if(xmlr.getLocalName() == nome)
                            persone.get(i).setNome(xmlr.getText());
                        if(xmlr.getLocalName() == cognome)
                            persone.get(i).setCognome(xmlr.getText());
                        if(xmlr.getLocalName() == sesso)
                            persone.get(i).setSesso(xmlr.getText());
                        if(xmlr.getLocalName() == comune_nascita)
                            persone.get(i).setComune(xmlr.getText());
                        if(xmlr.getLocalName() == data_nascita)
                            persone.get(i).setDataNascita(xmlr.getText());
                          }
                }
                break;
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println(FINE_TAG + xmlr.getLocalName());
                break;
        }
        xmlr.next();
    }

    }
    public void leggiCodiceFiscale(String codice) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Codice
        XMLStreamReader xmlr = inizializzaXmlStreamReader(INPUT_CODICEFISCALE);

        //Lettura file Codici Fiscali
        while(xmlr.hasNext());
        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println(INIZIO_LETTURA_DOCUMENTI + INPUT_CODICEFISCALE);
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println(TAG + xmlr.getLocalName());
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
            case XMLStreamConstants.END_ELEMENT://Stampa fine elemento
                System.out.println(FINE_TAG + xmlr.getLocalName());
                break;
        }
        xmlr.next();
    }

    public void leggiComuni(String[][]comuni) throws XMLStreamException {
        //Inizializzazione della lettura del file XML di Comuni
        XMLInputFactory xmlif = null;
        XMLStreamReader xmlr = null;
        try {
            xmlif = XMLInputFactory.newInstance();
            xmlr = xmlif.createXMLStreamReader(INPUT_COMUNI,
                    new FileInputStream(INPUT_COMUNI));
        } catch (Exception e) {
            System.out.println(MSG_ERRORE_INIZIALIZZAZIONE);
            System.out.println(e.getMessage());
        }

        //Lettura file Comuni
        while(xmlr.hasNext());
        switch(xmlr.getEventType()){
            case XMLStreamConstants.START_DOCUMENT://Stampa inizio documento
                System.out.println(INIZIO_LETTURA_DOCUMENTI + INPUT_COMUNI);
                break;
            case XMLStreamConstants.START_ELEMENT://Stampa inizio elemento
                System.out.println(TAG + xmlr.getLocalName());
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
                System.out.println(FINE_TAG + xmlr.getLocalName());
                break;
        }
        xmlr.next();
    }

    public String[][] getComuni() {
        return comuni;
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
}
