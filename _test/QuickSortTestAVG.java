package _test;

import gui.GUI;
import java.util.Random;

public class QuickSortTestAVG {
	
	private Random r = new Random();
	
	public int random(int max) {
		return r.nextInt(max);
	}
	
	public int anzahlVergleicheQuickSortAVG(int n) {
		int ergebnis = 0;
		if(n <=1) {
			return 0;
		}
		
		int positionPivot = random(n);
		ergebnis=n-1;
		
		ergebnis += anzahlVergleicheQuickSortAVG(n-positionPivot);
		ergebnis += anzahlVergleicheQuickSortAVG(positionPivot-1);
		return ergebnis;
	}
	
	public long test (int anzahlDurchlauf, int anzahlVergleiche) {
		long ergebnis = 0;
		for(int i=0; i<anzahlDurchlauf;i++) {
			ergebnis += anzahlVergleicheQuickSortAVG(anzahlVergleiche);
		
		}
		ergebnis/=anzahlDurchlauf;
		return ergebnis;
	}

	public static void main(String[] args) {
		new GUI(new QuickSortTestAVG());
	}

}
