package casaeditrice;

import java.util.*;

public class Autore implements Comparable<Autore>{


	private String nome;
	private String cognome;
	private String email;
	private int codice;
	
	private List<Pubblicazione> pubblicazioni = new LinkedList<Pubblicazione>();
	
	public Autore(String nome, String cognome, String email, int codice){
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.codice=codice;
	}

	public String getCognome(){
		return cognome;
	}

	public String getNome(){
		return nome;
	}

	public String getEmail(){
		return email;
	}
	
	public int getCodice(){
		return codice;
	}
	
	
	public Pubblicazione aggiungiPubblicazione(String titolo, char tipologia, String volume, int anno, int contributo){
		
		Pubblicazione p=null;
		
		if(tipologia=='R'){
			p = new Rivista(titolo, volume, anno);
		}
		else if(tipologia=='C'){
			p = new Conferenza(titolo, volume, anno);
		}

		p.setProprietario(this,contributo);
		pubblicazioni.add(p);
		
		return p;
	}
	

	public Collection<Pubblicazione> elencoPubblicazioni(){
		Collections.sort(pubblicazioni);
		return pubblicazioni;
	}
	
	
	
	public void aggiungiPubblicazione(Pubblicazione p){
		
		if(!pubblicazioni.contains(p))
		{
			pubblicazioni.add(p);
		}
	}
	
	
	
	public int compareTo(Autore altro) {
		int confrontoCognomi=altro.getCognome().compareTo(this.getCognome());
		if(confrontoCognomi!=0)
			return -confrontoCognomi;
		
		int confrontoNomi=altro.getNome().compareTo(this.getNome());
			return -confrontoNomi;
	}
	

	
	
	
	
}
