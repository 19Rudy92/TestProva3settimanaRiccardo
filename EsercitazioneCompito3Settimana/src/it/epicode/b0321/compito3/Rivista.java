package it.epicode.b0321.compito3;

public class Rivista extends Pubblicazione {
	public enum Periodicita {
		SETTIMANALE, MENSILE, SEMESTRALE;
	}
	private Periodicita periodicita;
	public Rivista(String codiceIsbn, String titolo, int annoPubblicazione, int numPagine, Periodicita periodicita) {
		super(codiceIsbn, titolo, annoPubblicazione, numPagine);
		this.periodicita = periodicita;
	}
	
	public Periodicita getPeriodicita() {
		return periodicita;
	}
	@Override
	public String toFileString() {
		return String.format("%s#%s#%s", "R",super.toFileString(), periodicita);
		
	}

}
