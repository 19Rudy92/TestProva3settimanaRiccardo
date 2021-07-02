package it.epicode.b0321.compito3;

import java.io.IOException;
import java.util.List;

public interface Archivio {
	void aggiungiPubblicazione(Pubblicazione p);

	Pubblicazione rimuoviPubblicazioneDaIsbn(String isbn);

	Pubblicazione ricercaPubblicazionePerIsbn(String isbn);

	List<Pubblicazione> ricercaPubblicazionePerAnno(int a);

	List<Pubblicazione> ricercaPubblicazionePerAutore(String au);

	void salva() throws IOException;

	void leggi() throws IOException;
	
	void svuota();
	
	String report();
	
	
}
