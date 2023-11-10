package _test.automaten.spionage;

import gui.GUI;

public class KGBAutomat {
	public int zustand;
	private char[] alphabet = {'0','1','2','3','4','5','6','7','8','9'};
	
	public boolean imAlphabet(char pZeichen){
		//TODO
		return false;
	}
	
	public boolean teste(String pEingabe){
		zustand = 0;
		for(int i=0; i<pEingabe.length();i++) {
			char z = pEingabe.charAt(i);
			if(zustand==0) {
				if(z=='0') {
					zustand=1;
				}
				if(z=='x' || z=='7') {
					zustand=0; 
				}
			}
			else if(zustand==1){
				if(z=='0') {
					zustand=2;
				}
				if(z=='x' || z=='7') {
					zustand=0;
				}
			}
			else if(zustand==2) {
				if(z=='0') {
					zustand=2;
				}
				if(z=='7') {
					zustand=3;
				}
				if(z=='x') {
					zustand=0;
				}
			}
			else if(zustand==3) {
				
				return true;
			}

			
		}
		return false;
	}
	
	public static void main(String[] args) {
		new GUI(new KGBAutomat());
	}
	
}
