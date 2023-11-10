package projektkurs;

import gui.GUI;

import java.util.Random;

public class Tankstelle {
	private Random random;
	
	private int zeitBisZapf1Frei = 0;
	private int zeitBisZapf2Frei = 0;
	private int kunden = 0;
	private int wartendeKunden = 0;
	
	public Tankstelle(){
		random = new Random();
	}
	
	public double zufallszahl(){
		return random.nextDouble();
	}
	
	public int kundeKommtInSekunde() {
		if(0.00111 > zufallszahl()) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public int wievieleKommenInStd(int pStunden) {
		int erg = 0;
		for (int i=0;i<3600*pStunden;i++) {
			erg += kundeKommtInSekunde();
		}
		return erg/pStunden;
	}
	
	public String run(int pStunden) {
		reset();
		for (int i=0;i<3600*pStunden;i++) {
			if (zeitBisZapf1Frei != 0) {
				zeitBisZapf1Frei -= 1;
			}
			if (zeitBisZapf2Frei != 0) {
				zeitBisZapf2Frei -= 1;
			}
			if (kundeKommtInSekunde() == 1) {
				kunden +=1;
				if(zeitBisZapf1Frei == 0) {
					zeitBisZapf1Frei = 8*60;
				}else if (zeitBisZapf2Frei == 0) {
					zeitBisZapf2Frei = 8*60;
				}else if (zeitBisZapf1Frei < zeitBisZapf2Frei) {
					zeitBisZapf1Frei += 8*60;
					wartendeKunden += 1;
				}else {
					zeitBisZapf2Frei += 8*60;
					wartendeKunden += 1;
				}
			}
		}
		int x = (int) (1.0*kunden/wartendeKunden)*100;
		x = x/100;
		
		String output = x+"% der Kunden mussten warten";
		// Richtig runden
		System.out.println(output);
		reset();
		return output;
	}
	
	public void reset () {
		zeitBisZapf1Frei = 0;
		zeitBisZapf2Frei = 0;
		kunden = 0;
		wartendeKunden = 0;
	}

	
	
	
	public static void main(String[] args) {
		new GUI(new Tankstelle());
	}
	
}
