import casaeditrice.*;

import java.util.*;

public class Esempio {

	public static void main(String[] args) throws Exception{

		CasaEditrice ce = new CasaEditrice();

		Autore a;
		Pubblicazione p;
		Collection<Pubblicazione> pubblicazioni;
		Collection<Autore> autori;
		
		System.out.println("PRIMA DELLA LETTURA DA FILE\n");
		
		System.out.println("Nuovo autore:");
		a = ce.definisciAutore("Mario", "Rossi", "mario.rossi@polito.it");
		a = ce.getAutore("Mario", "Rossi");
		System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+a.getEmail()+", "+a.getCodice());
		
		System.out.println("Nuovo autore:");
		a = ce.definisciAutore("Gianni", "Verdi", "gianni.verdi@polito.it");
		a = ce.getAutore(10001);
		System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+a.getEmail()+", "+a.getCodice());

		System.out.println("Nuovo autore:");
		a = ce.definisciAutore("Roberto", "Bianchi", "roberto.bianchi@unisa.it");
		a = ce.getAutore(10002);
		System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+a.getEmail()+", "+a.getCodice());
		
		System.out.println("Elenco autori (ordine alfabetico):");
		autori = ce.elencoAutori();
		for(int i=0;i<autori.size();i++)
		{
			a = ((List<Autore>)autori).get(i);
			System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+a.getEmail()+", "+a.getCodice());
		}
		
		System.out.println("Nuova pubblicazione:");
		a = ce.getAutore("Gianni", "Verdi");
		p = a.aggiungiPubblicazione("Titolo prima pubblicazione", 'R', "Nome del primo volume", 2011, 60);
		((Rivista)p).setIsbn("1234-5678");
		pubblicazioni = a.elencoPubblicazioni();
		p = pubblicazioni.iterator().next();
		System.out.println(" "+p.getTitolo()+", "+p.getVolume()+", "+p.getAnno());

		System.out.println("Proprietario: ");
		a = p.getProprietario();
		System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+a.getEmail()+", "+a.getCodice());
		
		a = ce.getAutore("Mario", "Rossi");
		p.aggiungiCoautori(a, 40);
		
		System.out.println("Autori pubblicazione e contributo individuale: ");
		autori = p.elencoAutori();
		for(int i=0;i<autori.size();i++)
		{
			a = ((List<Autore>)autori).get(i);
			System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+p.getContributo(a)+"%");
		}
		
		a = ce.getAutore("Mario", "Rossi");
		p = a.aggiungiPubblicazione("Titolo seconda pubblicazione", 'C', "Nome secondo volume", 2010, 20);
		((Conferenza)p).setLuogo("Italia");

		a = ce.getAutore("Gianni", "Verdi");
		p.aggiungiCoautori(a, 10);
		
		a = ce.definisciAutore("Alberto", "Blu", "alberto.blu@unitn.it");
		p.aggiungiCoautori(a, 70);

		System.out.println("Autori pubblicazione e contributo individuale: ");
		autori = p.elencoAutori();
		for(int i=0;i<autori.size();i++)
		{
			a = ((List<Autore>)autori).get(i);
			System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+p.getContributo(a)+"%");
		}
		
		System.out.println("Pubblicazioni di Mario Rossi:");
		a = ce.getAutore("Mario", "Rossi");
		pubblicazioni = a.elencoPubblicazioni();
		for(int i=0;i<pubblicazioni.size();i++)
		{
			p = ((List<Pubblicazione>)pubblicazioni).get(i);
			System.out.println(" "+p.getTitolo()+", "+p.getVolume()+", "+p.getAnno());		
		}

		System.out.println("\nDOPO LA LETTURA DA FILE\n");		
		
		ce.leggiPubblicazioni("input.txt");
		
		System.out.println("Elenco autori dopo la lettura da file (ordine alfabetico):");
		autori = ce.elencoAutori();
		for(int i=0;i<autori.size();i++)
		{
			a = ((List<Autore>)autori).get(i);
			System.out.println(" "+a.getNome()+" "+a.getCognome()+", "+a.getEmail()+", "+a.getCodice());
		}
		
		System.out.println("Pubblicazioni dell'autore 10000 dopo la lettura da file (ordine cronologico):");
		a = ce.getAutore(10000);
		pubblicazioni = a.elencoPubblicazioni();
		for(int i=0;i<pubblicazioni.size();i++)
		{
			p = ((List<Pubblicazione>)pubblicazioni).get(i);
			System.out.println(" "+p.getTitolo()+", "+p.getVolume()+", "+p.getAnno());		
		}
	}

}
