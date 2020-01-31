package it.polito.po.test;

import java.util.*;
import java.util.List;

import casaeditrice.*;
import junit.framework.TestCase;

public class TestR1_Autori extends TestCase {

	private CasaEditrice ce;
	
    public void setUp(){
        ce = new CasaEditrice();
    }

    public void testDefinisciAutore() {
        String nome = "Mario";
        String cognome = "Rossi";
        String email = "mario.rossi@polito.it";
        
        Autore a1 = ce.definisciAutore(nome, cognome, email);
        
        assertNotNull("Metodo definisciAutore() non implementato.",a1);
        
        assertEquals("Nome autore non corretto.",nome,a1.getNome());

        assertEquals("Codice numerico autore assegnato non corretto.",10000,a1.getCodice());
        
		Autore a2 = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
        
        assertEquals("Incremento codice numerico autore non corretto.",10001,a2.getCodice());
    }

    public void testGetAutoreCodice() {

        Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
		Autore a2 = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
    	
        String nome = "Roberto";
        String cognome = "Bianchi";
        String email = "roberto.bianchi@unisa.it";
		
		Autore a3a = ce.definisciAutore(nome, cognome, email);
		Autore a3b = ce.getAutore(a3a.getCodice());
		
        assertEquals("Accesso all'autore per codice non corretto.",a3a,a3b);
    }
    
    public void testGetAutoreNomeCognome() {

        Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
		Autore a2 = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
		Autore a3 = ce.definisciAutore("Roberto", "Bianchi", "roberto.bianchi@unisa.it");
    	
        String nome = "Tommaso";
        String cognome = "Bianchi";
        String email = "tommaso.bianchi@poliba.it";

		Autore a4a = ce.definisciAutore(nome, cognome, email);
		Autore a4b = ce.getAutore(nome, cognome);
		
        assertEquals("Accesso all'autore per nome e cognome non corretto.",a4a,a4b);
    }
    

    public void testElencoAutori() {

        Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
		Autore a2 = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
		Autore a3 = ce.definisciAutore("Roberto", "Bianchi", "roberto.bianchi@unisa.it");
    	Autore a4 = ce.definisciAutore("Tommaso", "Bianchi", "tommaso.bianchi@poliba.it");
	    Collection<Autore>autori = ce.elencoAutori();
	    
        assertEquals("Numero di autori definiti non corretto.",4,autori.size());
        
        Autore primo = ((List<Autore>)autori).get(0);
        
        
        assertEquals("ordinamento alfabetico non corretto.",primo,a3);
        
    }
    
    
    
}
