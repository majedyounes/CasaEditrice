package it.polito.po.test;

import casaeditrice.*;

import java.util.*;

import junit.framework.TestCase;


public class TestR3_Coautori  extends TestCase {

	private CasaEditrice ce;
	
    public void setUp(){
        ce = new CasaEditrice();
    }
    
    public void testAggiungiCoautoriDuplicati() {
    	
    	Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
    	
    	Pubblicazione p1 = a1.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome primo volume", 2011, 60);

		Autore a2 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");

		try
		{
			p1.aggiungiCoautori(a2, 40);
			fail("Coautore non riconosciuto come duplicato.");
		}
		catch(AutoreDuplicatoException e)
		{
			assertTrue("Coautore duplicato correttamente riconosciuto.",true);
		}
    }
    
    public void testElencoCoautori() throws AutoreDuplicatoException{
    	
    	Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
    	
    	Pubblicazione p1 = a1.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome primo volume", 2011, 60);

		Autore a2 = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
		p1.aggiungiCoautori(a2, 10);

		Autore a3 = ce.definisciAutore("Roberto", "Bianchi", "roberto.bianchi@unisa.it");
		p1.aggiungiCoautori(a3, 25);

		Autore a4 = ce.definisciAutore("Alberto", "Blu", "alberto.blu@unitn.it");
		p1.aggiungiCoautori(a4,  5);
			
		Collection<Autore> autori = p1.elencoAutori();
			
		Autore primo = ((List<Autore>)autori).get(0);
		Autore secondo = ((List<Autore>)autori).get(1);
			
		assertEquals("Numero coautori non corretto.",4,autori.size());
			
		assertEquals("Primo coautore (proprietario) non corretto.",primo,a1);
			
		assertEquals("Ordine alfabetico non corretto.",secondo,a3);
			
    }
    
    public void testContributoIndividuale() throws AutoreDuplicatoException{
    	
    	
    	Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
    	Pubblicazione p1 = a1.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome primo volume", 2011, 70);
    	
    	Autore a2 = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
		p1.aggiungiCoautori(a2, 30);
    	
    	Autore a3 = ce.definisciAutore("Roberto", "Bianchi", "roberto.bianchi@unisa.it");    	
    	Pubblicazione p2 = a3.aggiungiPubblicazione("Titolo seconda pubblicazione", 'C', "Nome secondo volume", 2012, 50);
		p2.aggiungiCoautori(a1, 50);

		assertEquals("Contributo individuale (proprietario) sulla pubblicazione errato.",70, p1.getContributo(a1));

		assertEquals("contributo individuale (coautore) sulla pubblicazione errato.",50, p2.getContributo(a1));

    }    
    
    public void testPubblicazioniAutoreInesistente() {
    	
    	Autore a1 = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
    	Pubblicazione p1 = a1.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome primo volume", 2011, 70);
    	
    	try
		{
    		Collection<Pubblicazione> autori = ce.pubblicazioniAutore("Gianni", "Verdi");
    		
    		fail("Autore non riconosciuto come inesistente.");
		}
		catch(AutoreInesistenteException e)
		{
			assertTrue("Autore correttamente riconosciuto come inesistente.",true);
		}
    	
    }
    	
    
    public void testPubblicazioniAutore() {
    	
    	
    	Autore a = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
    	Pubblicazione p1 = a.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome primo volume", 2006, 70);
    	
    	Autore b = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
    	
    	Autore c = ce.definisciAutore("Roberto", "Bianchi", "roberto.bianchi@unisa.it");    	
    	Pubblicazione p2 = c.aggiungiPubblicazione("Titolo seconda pubblicazione", 'C', "Nome secondo volume", 2011, 50);

    	Autore d = ce.definisciAutore("Tommaso", "Bianchi", "tommaso.bianchi@poliba.it");
    	Pubblicazione p3 = d.aggiungiPubblicazione("Titolo terza pubblicazione", 'R', "Nome terzo volume", 2004, 10);

    	
		try
		{
			p1.aggiungiCoautori(b, 30);
			p2.aggiungiCoautori(a, 50);
			p3.aggiungiCoautori(a, 90);
			
			
			Collection<Pubblicazione> pubblicazioni = ce.pubblicazioniAutore("Mario", "Rossi");
			
			assertEquals("numero di pubblicazioni noncorretto.",3, pubblicazioni.size());
			
			Pubblicazione prima = ((List<Pubblicazione>)pubblicazioni).get(0);
			Pubblicazione seconda = ((List<Pubblicazione>)pubblicazioni).get(1);
			
			assertEquals("ordine cronologico per la prima pubblicazione non corretto.",prima, p2);
			
			assertEquals("ordine cronologico per la seconda pubblicazione non corretto.",seconda, p1);

		}
		catch(AutoreDuplicatoException e)
		{
			fail("gestione elenco pubblicazioni autore non corretta.");
		}
		catch(AutoreInesistenteException e)
		{
			fail("gestione elenco pubblicazioni autore non corretta.");
		}
    }    
    
    
}
