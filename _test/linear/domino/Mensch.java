package _test.linear.domino;

import javax.swing.JOptionPane;

public class Mensch extends Spieler {

	public Mensch(String pName, Tisch ptisch) {
		super(pName, ptisch);
		// TODO Auto-generated constructor stub
	}
	
	public Dominostein steinAuswaehlen(){
		System.out.println("Spieler.steinAuswaehlen()");
		if(steine.isEmpty()){
			return null;
		}
		int zahl = -1;
		do{
			String zahlString = JOptionPane.showInputDialog(this.name, null);
			try {
				zahl = Integer.parseInt(zahlString);
			} catch (NumberFormatException e) {
				System.err.println("NumberFormatException in Spieler");
			}
		}while(zahl < 0);
		
		steine.toFirst();
		for(int i=1; i<zahl; i++){
			if(steine.hasAccess()){
				steine.next();
				if(!steine.hasAccess()){
					steine.toFirst();
				}
			}
		}
		return steine.getContent();
	}
	
	public boolean vorneHintenWaehlen(){
		System.out.println("Spieler.vorneHintenWaehlen()");
		int vorneZahl = JOptionPane.showOptionDialog(null, "vorne?", this.name, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		System.out.println(vorneZahl);
		if(vorneZahl == JOptionPane.OK_OPTION){
			return true;
		}
		return false;
	}

}
