package projektkurs;

import gui.GUI;

import java.util.Random;

public class ZuflaelligeReihenfolge private Random random = new Random();private Object[] objekte;private double[] zufalls;public ZuflaelligeReihenfolge(){random = new Random();Object[] input =  {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};objekte = input;}public ZuflaelligeReihenfolge(Object[] a){random = new Random();objekte = a;}public double zufallszahl(){return random.nextDouble();}public void bubblesort() {for(int i=0; i<objekte.length-1; i++) {for(int j=0; j<objekte.length-1; j++) {if(zufalls[j] > zufalls[j+1]){Object zahl0 = objekte[j];Object zahl1 = objekte[j+1];objekte[j] = zahl1;objekte[j+1] = zahl0;double zahl0_d = zufalls[j];double zahl1_d = zufalls[j+1];zufalls[j] = zahl1_d;zufalls[j+1] = zahl0_d;}}}}public void mischen() {zufalls = new double[objekte.length];for(int i=0;i<objekte.length;i++) {zufalls[i] = zufallszahl();}bubblesort();System.out.println(objekte);}public static void main(String[] args) {String[] test = {"eins", "zwei", "drei", "vier"};new GUI(new ZuflaelligeReihenfolge(test));}
	
}