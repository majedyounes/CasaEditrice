package casaeditrice;

public class Conferenza extends Pubblicazione{

	private String luogo;
	
	
	public Conferenza(String titolo, String volume, int anno){
		super(titolo,volume,anno);
	}

	public void setLuogo(String luogo) {
		this.luogo=luogo;
	}
	
}
