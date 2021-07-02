package it.epicode.b0321.compito3;

import it.epicode.b0321.compito3.Rivista.Periodicita;

public abstract class Pubblicazione {
	private String codiceIsbn;
	private String titolo;
	private int annoPubblicazione;
	private int numPagine;

	public Pubblicazione(String codiceIsbn, String titolo, int annoPubblicazione, int numPagine) {
		this.codiceIsbn = codiceIsbn;
		this.titolo = titolo;
		this.annoPubblicazione = annoPubblicazione;
		this.numPagine = numPagine;
	}

	public String getCodiceIsbn() {
		return codiceIsbn;
	}

	public String getTitolo() {
		return titolo;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public int getNumPagine() {
		return numPagine;
	}

	public String toFileString() {
		return String.format("%s#%s#%d#%d", codiceIsbn, titolo, annoPubblicazione, numPagine); // La digitura %s prende
																								// il posto della
																								// stringa, mentre %d il
																								// posto di integer
	}

	public static Pubblicazione fromFileString(String s) {
		String[] tokens = s.split("#");
		switch (tokens[0]) {
		case "L":
			return new Libro(tokens[1], tokens[2], Integer.valueOf(tokens[3]), Integer.valueOf(tokens[4]), tokens[5],
					tokens[6]);
		case "R":
			return new Rivista(tokens[1], tokens[2], Integer.valueOf(tokens[3]), Integer.valueOf(tokens[4]),
					Periodicita.valueOf(tokens[5]));
		default:
			throw new IllegalArgumentException("File corrotto, il discriminatore deve essere R o L");
		}
	}
	public String reportString() {
	//	return codiceIsbn + " " + titolo + " " + this.getClass().getSimpleName();
		return String.format("%s %s %s%n", codiceIsbn, titolo, this.getClass().getSimpleName());
	}

	
	
	
	
}
