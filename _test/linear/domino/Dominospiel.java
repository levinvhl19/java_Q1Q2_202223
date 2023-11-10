package _test.linear.domino;

import gui.GUI;
import linear.Queue;
import linear.QueueWithViewer;

public class Dominospiel {
	private Queue<Spieler> spieler;
	private int anzahl;
	public Tisch tisch;

	public Dominospiel(int pAnzahl){
		System.out.println("Dominospiel("+pAnzahl+")");
		anzahl = pAnzahl;
		tisch = new Tisch();
		spieler = new Queue<Spieler>();
		for(int i=1; i<=pAnzahl; i++){
			Spieler s = new Mensch("Spieler "+i, gibTisch());
			spieler.enqueue(s);
		}
		
		if(anzahl==1) {
			Spieler henning = new Computer("Henning");
			spieler.enqueue(henning);
			
		}
		
	}
	
	public Tisch gibTisch() {
		return tisch;
	}

	/**
	 * an jeden Spieler werden vom Vorrat 5 Dominosteine verteilt
	 */
	public void austeilen(){
		System.out.println("Dominospiel.austeilen()");
		Spieler s = spieler.front();
		for(int i=0; i<5; i++) {
			s.steinHinzufuegen(tisch.vomVorrat());
		}
		spieler.dequeue();
		spieler.enqueue(s);
		while(spieler.front() != s) {
			Spieler aktueller = spieler.front();
			for(int i=0;i<5;i++) {
				aktueller.steinHinzufuegen(tisch.vomVorrat());
			}
			spieler.dequeue();
			spieler.enqueue(aktueller);
			
			
			
		}
	}

	public void spielen(){
		System.out.println("Dominospiel.spielen()");
		boolean gameover = false;
		

		
		while(! gameover){
			Spieler aktuellerSpieler = spieler.front();
			Dominostein d =aktuellerSpieler.steinAuswaehlen();
			boolean vorne = aktuellerSpieler.vorneHintenWaehlen();
			
			

			
			if(vorne) {
				if(tisch.anlegenVorne(d)) {
					aktuellerSpieler.wegnehmenAktuellenStein();
				}
				else {
					aktuellerSpieler.steinHinzufuegen(tisch.vomVorrat());
				}
			}
				
			else {
				if(tisch.anlegenHinten(d)) {
					aktuellerSpieler.wegnehmenAktuellenStein();
				}
				else {
					aktuellerSpieler.steinHinzufuegen(tisch.vomVorrat());
				}
			}
			spieler.dequeue();
			spieler.enqueue(aktuellerSpieler);
			
			if(aktuellerSpieler.hatGewonnen()) {
				gameover = true;
				return;
			}
		}
	}


	public static void main(String[] args) {
		new GUI(new Dominospiel(1));
	}

}
