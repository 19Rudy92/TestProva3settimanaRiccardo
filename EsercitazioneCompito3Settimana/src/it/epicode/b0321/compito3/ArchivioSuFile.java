package it.epicode.b0321.compito3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;

public class ArchivioSuFile implements Archivio {
	private Map<String, Pubblicazione> catalogo = new HashMap<String, Pubblicazione>(); 
	private File pubFile = new File("pubblicazioni.txt");
	
	@Override
	public void aggiungiPubblicazione(Pubblicazione p) {
		catalogo.put(p.getCodiceIsbn(), p);    //Inserisco la pubblicazione nella mappa,  
	}										   //passando come valore la pubblicazione e 
											   //come chiave il codice isbm ottenuto dalla pubblicazione
	
	@Override
	public Pubblicazione rimuoviPubblicazioneDaIsbn(String isbn) {
		Pubblicazione rimossa = catalogo.remove(isbn);	//Definisco Pubblicazione rimossa
		return rimossa;									//remove restituisce il valore rimossa
	}													//dalla mappa

	@Override
	public Pubblicazione ricercaPubblicazionePerIsbn(String isbn) {
		Pubblicazione trovata = catalogo.get(isbn);
		return trovata;
	}

	@Override
	public List<Pubblicazione> ricercaPubblicazionePerAnno(int a) {
//		Collection<Pubblicazione> pubs = catalogo.values();  //Creiamo una collection di pubblicazioni
//		List<Pubblicazione> risultati = new ArrayList<Pubblicazione>(); //creiamo una lista vuota che verra popolata dalle pubblicazione filtrate dal for each 
//		for(Pubblicazione p : pubs) { 		     // per ogni pubblicazione p all interno della sequenza pubs
//			if(a == p.getAnnoPubblicazione()) { //qui filtriamo le pubblicazioni con l anno di pubblicazione uguale ad a
//			risultati.add(p);      			    //aggiunge i filtrati alla lista risultati
//			}
//		}
//		return risultati;
		
		//versione con gli stream
		List<Pubblicazione> filtratiAnno = 					 //definisci una lista in cui raccogliere i dati filtrati dallo stream
				catalogo.values() 							 //prendiamo i valori di catalogo (pubblicazione)
				.stream()									 //viene trasformato in uno stream
				.filter(p -> p.getAnnoPubblicazione() == a)  //pubblicazione viene filtrata da una lambda, per anno = ad a
				.collect(Collectors.toList());				 //raccoglie tutti i dati e li mette in una lista
		return filtratiAnno; //								 //mi ritorna i dati filtrati
	}

	@Override
	public List<Pubblicazione> ricercaPubblicazionePerAutore(String au) {
		List<Pubblicazione> filtraAutore = catalogo.values()
				.stream()
				.filter(p -> p instanceof Libro)			 //Filtriamo i libri tra tutte le pubblicazioni
				//.map(p -> (Libro)p)							 //Trasformiamo le pubblicazioni in libri con un cast
				.map(Libro.class::cast)
				.filter(p -> p.getAutore().equals(au))				
				.collect(Collectors.toList());
		return filtraAutore;
	}

	@Override
	public void salva() throws IOException {
		String pubString = catalogo.values()			     //creeiamo una variabile pubString, chiamiamo i valori dentro catalogo
				.stream()									 //creeiamo uno stream dei valori
				//.map(i -> i.toFileString())					 //prendiamo i valori (pubblicazioni) e chiamiamo il metodo toFileString (diventando uno stream di stringhe)
				.map(Pubblicazione::toFileString)
				.collect(Collectors.joining("@"));			 //prendiamo lo stream di stringhe, e andiamo ad unire tutto in un unica stringa, con ogni stringa 
															 //separata da @
			
		FileUtils.writeStringToFile(pubFile, pubString, "UTF-8");
	}

	@Override
	public void leggi() throws IOException {
		String pubString = FileUtils.readFileToString(pubFile, "UTF-8");
		String[] tokens = pubString.split("@");
		Stream.of(tokens)									  						  //trasformo lo string di tokens in stream
		.map(s -> Pubblicazione								  						  //trasforma uno strem di stringhe in uno stream di Pubblicazione
				.fromFileString(s)).forEach(p -> catalogo.put(p.getCodiceIsbn(), p)); 
		
	}

	@Override
	public void svuota() {
		catalogo.clear();
	}

	@Override
	public String report() {				//stampa in modo leggibile un report dell archivio
		//String reportString = catalogo.values().stream().map(p -> p.reportString()).collect(Collectors.joining());
		String reportString = catalogo.values().stream().map(Pubblicazione::reportString).collect(Collectors.joining());
		return reportString;
	}

}
