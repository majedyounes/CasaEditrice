package casaeditrice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CasaEditrice {

	Map<Integer,Autore> autorim = new HashMap<Integer,Autore>();
	List<Autore> autoril = new LinkedList<Autore>();
	
	public static int codice = 10000;
	
	public Autore definisciAutore(String nome, String cognome, String email){
		Autore a = new Autore(nome,cognome,email,codice);
		autorim.put(codice, a);
		autoril.add(a);
		codice++;
		return a;
	}
	
	public Collection<Autore> elencoAutori(){
		Collections.sort(autoril);
		return autoril;
	}
	
	public Autore getAutore(int codice){
		return autorim.get(codice);
    }

	public Autore getAutore(String nome, String cognome){

		for(int i=0;i<autoril.size();i++){
			Autore a = autoril.get(i);
			if(a.getNome().equals(nome) && a.getCognome().equals(cognome))
			{
				return a;
			}
				
		}
     	return null;
	}

	public Collection<Pubblicazione> pubblicazioniAutore(String nome, String cognome) throws AutoreInesistenteException {
		Autore a = this.getAutore(nome, cognome);
		if(a==null)
			throw new AutoreInesistenteException();
		else {
			return a.elencoPubblicazioni();
			
		}
			
		
	}
	
	
	
	
	
	public Collection<Pubblicazione> elencoPubblicazioni(){
		List<Pubblicazione> risultato = new LinkedList<Pubblicazione>();
		for(int i=0;i<autoril.size();i++){
			risultato.addAll(autoril.get(i).elencoPubblicazioni());
		}
		Collections.sort(risultato);
		return risultato;
	}
	
	public void leggiPubblicazioni(String nomeFile) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(nomeFile));
			
		Pubblicazione p=null;
		boolean primo=false;
		
		String titolo=null;
		String tipologia=null;
		String volume=null;
		String anno=null;
		
		String linea;
		while ((linea = in.readLine()) != null) {
			
			try {
			    StringTokenizer st = new StringTokenizer(linea, ";");
			    String iniziale = st.nextToken().trim();
			    if (iniziale.toUpperCase().equals("P")) 
			    {
			       titolo = st.nextToken().trim();
			       tipologia = st.nextToken().trim();
			       volume = st.nextToken().trim();
			       anno = st.nextToken().trim();
			       primo=true;
			    } 
			    else 
			    {
			       
			       String nome = st.nextToken().trim();
			       String cognome = st.nextToken().trim();
			       String email = st.nextToken().trim();
			       String contributo = st.nextToken().trim();
			       
			       Autore a;
			       if(getAutore(nome, cognome)!=null)
			    	   a = getAutore(nome, cognome);
			       else 
			    	   a = definisciAutore(nome, cognome, email);
			       
			       if(primo==true)
			       {
			    	   p = a.aggiungiPubblicazione(titolo, tipologia.charAt(0), volume, Integer.parseInt(anno), Integer.parseInt(contributo));
				       primo=false;
			       }
			       else
			       {
			    	   p.aggiungiCoautori(a, Integer.parseInt(contributo));
			       }
			       
			    }
			} 
			catch (Exception e) {
			    //System.err.println("Errore");
				e.printStackTrace();
				
			    
			}
			
		}
		in.close();
	}
	
	
	
	
	
}
