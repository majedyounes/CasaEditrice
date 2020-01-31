package casaeditrice;

import java.util.*;

public class Pubblicazione implements Comparable<Pubblicazione>{

	String titolo;
	String volume;
	int anno;
	
	private Autore proprietario;
	Map<Autore, Integer> coautori = new HashMap<Autore, Integer>();
	

	public Pubblicazione(String titolo, String volume, int anno){
		this.titolo=titolo;
		this.volume=volume;
		this.anno=anno;
	}

	public String getTitolo(){
		return titolo;
	}
	
	public String getVolume(){
		return volume;
	}
	
	
	public int getAnno(){
		return anno;
	}

	public Autore getProprietario(){
		return proprietario;
	}


	public void aggiungiCoautori(Autore a, int contributo) throws AutoreDuplicatoException{
		boolean trovato=false;
		List<Autore> l = new LinkedList<Autore>(coautori.keySet());
		for(int i=0;i<l.size();i++)
			if(l.get(i).getNome().equals(a.getNome()) && l.get(i).getCognome().equals(a.getCognome())){
				trovato=true;
				break;
			}
		
		if(trovato)
			throw new AutoreDuplicatoException();
		else
		{	
			coautori.put(a,contributo);
			// Devo anche aggiungere la pubblicazione nella lista delle pubblicazioni dei coautori
			a.aggiungiPubblicazione(this);
		}
		
	}
	
	public Collection<Autore> elencoAutori(){
		List<Autore> autori = new LinkedList<Autore>(coautori.keySet());
		Collections.sort(autori);
		
		List<Autore> risultato = new LinkedList<Autore>();
		risultato.add(proprietario);
		for(int i=0;i<autori.size();i++)
		{
			Autore a = autori.get(i);
			if(!a.equals(proprietario))
				risultato.add(a);
		}
		return risultato;
	}

	public int getContributo(Autore a){
		return coautori.get(a);
	}
	
	
	
	public int compareTo(Pubblicazione altra) {
		return altra.getAnno()-this.anno;
	}
	
	
	public void setProprietario(Autore proprietario, int contributo){
		this.proprietario=proprietario;
		this.coautori.put(proprietario, contributo);
	}
	
	
}
