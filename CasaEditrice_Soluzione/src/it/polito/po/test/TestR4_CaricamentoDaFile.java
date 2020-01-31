package it.polito.po.test;

import casaeditrice.*;

import java.io.*;
import java.util.*;

import junit.framework.TestCase;


public class TestR4_CaricamentoDaFile extends TestCase {

	private CasaEditrice ce;
	
    public void setUp(){
        ce = new CasaEditrice();
    }
    
    private static void writeFile(String fileName,String content) {
      	try{
      		FileOutputStream fos = new FileOutputStream(fileName);
      		fos.write(content.getBytes());
      		fos.close();
    	}catch(IOException ioe){
    		throw new RuntimeException(ioe.getMessage());
    	}
      }
    
    public void testleggiPubblicazioniBase() throws IOException{
      	String base = "P;Titolo terza pubblicazione;R;Nome terzo volume;2011\n" +
    			      "A;Davide;Gialli;davide.gialli@polito.it;60\n";

      	writeFile("file.txt",base);
      	
      	ce.leggiPubblicazioni("file.txt");
 
      	Autore a = ce.getAutore("Davide", "Gialli");
      	
      	assertEquals("Errore lettura autore da file.","Gialli",a.getCognome());

      	Collection<Pubblicazione> pubblicazioni = a.elencoPubblicazioni();

      	Pubblicazione p = ((List<Pubblicazione>)pubblicazioni).get(0);
      	
      	assertEquals("Errore lettura pubblicazione da file.","Titolo terza pubblicazione",p.getTitolo());
      	
      	assertEquals("Errore assegnazione proprietario nella lettura da file.",a,p.getProprietario());
    }
    
    
    public void testleggiPubblicazioniCompleto() throws IOException{
      	String completo = "P;Titolo terza pubblicazione;R;Nome terzo volume;2011\n" +
      			          "A;Davide;Gialli;davide.gialli@polito.it;60\n" +
      			          "A;Angelo;Neri;angelo.neri@poliba.it;40\n" +
      			          "P;Titolo quarta pubblicazione;C;Nome quarto volume;2012\n" +
      			          "A;Angelo;Neri;angelo.neri@poliba.it;40\n" +
                          "A;Mario;Rossi;mario.rossi@polito.it;100\n" +
                          "A;Tommaso;Bianchi;tommaso.bianchi@poliba.it;100\n";

      	writeFile("file.txt",completo);
      	
      	ce.leggiPubblicazioni("file.txt");
 
      	Autore a = ce.getAutore("Angelo", "Neri");

      	Collection<Pubblicazione> pubblicazioni = a.elencoPubblicazioni();

      	assertEquals("Numero pubblicazioni per autore non corretto.",2,pubblicazioni.size());
      	
      	Pubblicazione prima = ((List<Pubblicazione>)pubblicazioni).get(0);

      	assertEquals("Ordinamento cronologico pubblicazioni non corretto.",prima.getAnno(),2012);
      	
      	Collection<Autore> autori = prima.elencoAutori();
      	
      	assertEquals("Numero autori per pubblicazione non corretto.",3,autori.size());
      	
      	Autore secondo = ((List<Autore>)autori).get(1);
      	
      	assertEquals("Ordinamento alfabetico autori non corretto.",secondo.getEmail(),"tommaso.bianchi@poliba.it");
    }
    
}
