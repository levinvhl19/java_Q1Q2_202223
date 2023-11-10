package _test.linear.domino;

import javax.swing.JOptionPane;

public class Computer extends Spieler {
	

	public Computer(String pName, Tisch ptisch) {
		super(pName, ptisch);
		// TODO Auto-generated constructor stub
	}
	boolean vorne = false;
	public Dominostein steinAuswaehlen() {
		System.out.println("Spieler.steinAuswaehlen()");

		
		
		Dominostein vorneKette = tisch.ersterSteinDerKette();
		int vorneKette1 = vorneKette.gibZahl1();
		int vorneKette2 = vorneKette.gibZahl2();
		Dominostein hintenKette = tisch.letzterSteinDerKette();
		int hintenKette1 = hintenKette.gibZahl1();
		int hintenKette2 = hintenKette.gibZahl2();
		if(steine.isEmpty()){
			return null;
		}
		for(steine.toFirst();steine.hasAccess();steine.next()) {
			if(steine.getContent().gibZahl1()==vorneKette1||steine.getContent().gibZahl1()==vorneKette2) {
				vorne = true;
				return steine.getContent();
			}
			else if(steine.getContent().gibZahl1()==hintenKette1||steine.getContent().gibZahl1()==hintenKette2) {
				vorne = false;
				return steine.getContent();
			}
		
		}
		
		return steine.getContent();
		
	}

	@Override
	public boolean vorneHintenWaehlen() {
		// TODO Auto-generated method stub
		boolean test = vorne;
		vorne = (Boolean) null;
		return test;
	}

}
