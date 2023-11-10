package projektkurs;

import gui.GUI;

import java.util.Random;

public class KreisflaechenStreifen {
	private Random random;
	
	public KreisflaechenStreifen(){
		random = new Random();
	}
	
	public double zufallszahl(){
		return random.nextDouble();
	}
	
	
	public double kreiflaeche() {
		double breite =0.00001;
		double summe = 0;
		double streifen = 0;
		double x=0;
		double pi = 0;
		
		while(x<=1) {
			double y=0;
			y = Math.sqrt(1-(x*x));
			streifen = breite*y;
			x += breite;
			summe += streifen;
		}
		
		pi = summe*4;
		return pi;
	}
	
	public static void main(String[] args) {
		new GUI(new KreisflaechenStreifen());
	}
	
}
