package it.polito.po.test;

import casaeditrice.*;

import java.util.*;

import junit.framework.TestCase;


public class TestR2_Pubblicazioni extends TestCase {

	private CasaEditrice ce;
	
    public void setUp(){
        ce = new CasaEditrice();
    }

    public void testAggiungiPubblicazione() {

        Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
		
        String titolo = "Titolo prima pubblicazione";
        String volume = "Nome primo volume";
        int anno = 2011;
        int contributo = 100;
        
        Pubblicazione p1 = a1.aggiungiPubblicazione(titolo, 'R', volume, anno, contributo);

        assertNotNull("Metodo aggiungiPubblicazione() non implementato",p1);
        
        assertEquals("Titolo pubblicazione non corretto.",titolo,p1.getTitolo());

        assertEquals("Tipologia pubblicazione non corretta (rivista).",true, p1 instanceof Rivista);
        
    }

    public void testProprietarioPubblicazione() {

    	Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
    	Pubblicazione p1 = a1.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome primo volume", 2011, 100);
    	
    	Autore proprietario = p1.getProprietario();
    	
        assertEquals("Proprietario pubblicazione non corretto.",a1, proprietario);
        
    }
    
    public void testElencoPubblicazioni() {

    	Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
    	Pubblicazione p1 = a1.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome primo volume", 2011, 60);
    	Pubblicazione p2 = a1.aggiungiPubblicazione("Titolo seconda pubblicazione", 'C', "Nome secondo volume", 2012, 20);
    	Pubblicazione p3 = a1.aggiungiPubblicazione("Titolo terza pubblicazione", 'R', "Nome terzo volume", 2006, 80);
    	
		Collection<Pubblicazione>pubblicazioni = a1.elencoPubblicazioni();
	    
        assertEquals("Numero di pubblicazioni per autore non corretto.",3,pubblicazioni.size());
        
        Pubblicazione prima = ((List<Pubblicazione>)pubblicazioni).get(0);
        
        assertEquals("Ordinamento cronologico non corretto.",p2,prima);
    }
    
    
    
}
