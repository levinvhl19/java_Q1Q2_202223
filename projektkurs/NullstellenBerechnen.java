package projektkurs;

import gui.GUI;

import java.util.Random;
import java.util.function.DoubleUnaryOperator;

public class NullstellenBerechnen {
	private Random random;

	
	public NullstellenBerechnen(){
		random = new Random();
	}
	
	public double zufallszahl(){
		return random.nextDouble();
	}
	
	public double nullstellen(double pLinks, double pRechts, int pAnzahl) {
		double xLinks = pLinks;
		double xRechts = pRechts;
		double funktion;
		for(int i=0; i<pAnzahl;i++) {
			
		}
	}
	
	public static void main(String[] args) {
		new GUI(new NullstellenBerechnen());
	}
	
}
