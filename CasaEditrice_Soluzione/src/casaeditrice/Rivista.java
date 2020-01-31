package casaeditrice;

public class Rivista extends Pubblicazione{

	private String isbn;

	public Rivista(String titolo, String volume, int anno){
		super(titolo,volume,anno);
	}
	
	public void setIsbn(String isbn){
		this.isbn=isbn;
	}
	
}
