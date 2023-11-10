package _test.linear.domino;

import gui.GUI;

import javax.swing.JOptionPane;

import com.sun.management.internal.GcInfoBuilder;

import linear.ListWithViewer;

public abstract class Spieler {
	protected ListWithViewer<Dominostein> steine;
	protected String name; 
	public Tisch tisch;
	// nur zu Testzwecken!
	private Dominostein d12 = new Dominostein(1,2); 
	private Dominostein d23 = new Dominostein(2,3); 
	private Dominostein d43 = new Dominostein(4,3); 
	private Dominostein d51 = new Dominostein(5,1); 
	
	public Spieler(String pName, Tisch pTisch){
		System.out.println("Spieler("+pName+")");
		steine = new ListWithViewer<Dominostein>();
		this.name = pName;
		this.tisch = pTisch;
	}
	
	public abstract Dominostein steinAuswaehlen();
	
	/**
	 * nimmt dem Spieler den ein weg, den er vorher mit auswaehlen ausgewaehlt hat.
	 */
	public void wegnehmenAktuellenStein(){
		System.out.println("Spieler.wegnehmenAktuellenStein()");
		steine.remove();
	}
	
	/**
	 * Diese Methode fragt den Spieler, ob er vorne oder hinten anlegen will.
	 * Wenn der Spieler "vorne" waehlt, 
	 * dann gibt die Methode true zurueck,
	 * sonst false. 
	 * @return
	 */
	public abstract boolean vorneHintenWaehlen();

	public void steinHinzufuegen(Dominostein pStein) {
		System.out.println("Spieler.steinHinzufuegen("+pStein+")");
		steine.append(pStein);
	}

	public String getName() {
		return name;
	}

	/**
	 * gibt zurueck, ob der Spieler alle seine Steine verbraucht 
	 * und also gewonnen hat.
	 * @return
	 */
	public boolean hatGewonnen() {
		System.out.println("Spieler.hatGewonnen()");
		if(this.steine.isEmpty()){
			JOptionPane.showMessageDialog(null,getName()+" hat gewonnen!");
			return true;
		}
		return false;
	}
	
	public String toString(){
		return name;
	}
	
	public static void main(String[] args) {
		Spieler s = new Mensch("test");
		new GUI(s);
	}
}
