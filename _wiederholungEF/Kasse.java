package _wiederholungEF;

import gui.GUI;
import java.lang.Math;

public class Kasse{
    private double summe = .0;
    private double tagesSumme = .0;
    private double mwst = 0.19;

    public Kasse(){

    }
    public void produktHinzufügen(double pBetrag){
        summe += pBetrag; 
    }

    public void kassieren(){
        System.out.println("Summe: +"+summe+"€");
        System.out.println("MwSt: +"+summe*mwst+"€");
        summe += summe*mwst;
        summe*=100;
        Math.round(summe);
        summe/=100;
        System.out.println("Gesamtsumme: "+summe+"€");
        tagesSumme += summe;
        summe = 0;
    }

    public void leeren(){
        System.out.println("Heute umgesetzt: "+tagesSumme+"€");
        tagesSumme = 0;
    }
    
    public static void main(String[] args) {
    	new GUI(new Kasse());
    }
}
