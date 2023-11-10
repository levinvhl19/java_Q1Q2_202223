package projektkurs;

import gui.GUI;

import java.util.Random;

public class KreisflaecheBerechnen {
	private Random random;
	int anzahl;
	
	public KreisflaecheBerechnen(int pAnzahl){
		random = new Random();
		anzahl = pAnzahl;
	}
	
	public double zufallszahl(){
		return (random.nextDouble());
	}
	
	public double kreisflaeche() {

		double pi=0;
		int treffer = 0;
		for(int i=0; i< anzahl ;i++) {
			double x=zufallszahl();
			double y= zufallszahl();
			System.out.println(x);
			System.out.println(y);
			if((x*x)+(y*y) < 1) {
				treffer++;
			}
			
			
		}
		pi = 4.0*treffer/anzahl;
		return pi;
	}
	
	public static void main(String[] args) {
		new GUI(new KreisflaecheBerechnen(100000));
	}
	
}
