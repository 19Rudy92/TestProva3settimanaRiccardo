package it.epicode.b0321.compito3;

public class Libro extends Pubblicazione {
	private String autore;
	private String genere;

	public Libro(String codiceIsbn, String titolo, int annoPubblicazione, int numPagine, String autore, String genere) {
		super(codiceIsbn, titolo, annoPubblicazione, numPagine);
		this.autore = autore;
		this.genere = genere;
	}
	public String getAutore() {
		return autore;
	}
	public String getGenere() {
		return genere;
	}
	@Override
	public String toFileString() {
		return String.format("%s#%s#%s#%s", "L",super.toFileString(), autore, genere);
		
	}

}
