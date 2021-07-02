package it.epicode.b0321.compito3;

import java.io.IOException;
import java.util.List;

import it.epicode.b0321.compito3.Rivista.Periodicita;

public class Start {

	public static void main(String[] args) {
//		Archivio x = new ArchivioSuFile();
//		try {
//			x.salva();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		
		//commento prova
		
		
		Pubblicazione l1 = new Libro("p02t1", "Planet terror", 2002, 400, "Quentin Tarantino", "Horro, Fantascenza");
		Pubblicazione r1 = new Rivista("12urt", "tom s software", 1999, 400, Periodicita.MENSILE);
		Pubblicazione l2 = new Libro("prt34", "Planet terror", 1980, 400, "Quentin Tarantino", "Horro, Fantascenza");
		Pubblicazione r2 = new Rivista("9eyr4", "Good home", 2012, 400, Periodicita.SEMESTRALE);
		Pubblicazione r3 = new Rivista("f844r", "Planet terror", 2012, 300, Periodicita.SETTIMANALE);

		Archivio ar = new ArchivioSuFile();
		ar.aggiungiPubblicazione(r3);
		ar.aggiungiPubblicazione(r2);
		ar.aggiungiPubblicazione(l2);
		ar.aggiungiPubblicazione(r1);
		ar.aggiungiPubblicazione(l1);
		System.out.println("Primo report");

		System.out.println(ar.report());

		try {
			ar.salva();
			ar.svuota();
			System.out.println("Report dopo aver svuotato l archivio");

			System.out.println(ar.report());
			ar.leggi();
			System.out.println("Report dopo aver letto l archivio");

			System.out.println(ar.report());
			System.out.println();
			ar.rimuoviPubblicazioneDaIsbn("p02t1");
			System.out.println("Report dopo aver rimosso planet terror");
			System.out.println(ar.report());
			Pubblicazione p = ar.ricercaPubblicazionePerIsbn("12urt");
			System.out.println(p.getTitolo());
			System.out.println();
			List<Pubblicazione> pub2012 = ar.ricercaPubblicazionePerAnno(2012);	
			System.out.println("Ricerca per anno 2012");
			//pub2012.forEach(r -> System.out.println(r.getTitolo()));
			pub2012.stream().map(Pubblicazione::getTitolo).forEach(System.out::println); //metodo reference si puo usare al posto della lambda, 
																						 //quando l unica cosa che fa la lambda e ricevere un input e passarlo ad una funzione. 
			System.out.println();
			List<Pubblicazione> pubTarantino = ar.ricercaPubblicazionePerAutore("Quentin Tarantino");
			System.out.println();
			System.out.println("Ricerca per autore Tarantino");
			pubTarantino.forEach(r -> System.out.println(r.getTitolo()));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
