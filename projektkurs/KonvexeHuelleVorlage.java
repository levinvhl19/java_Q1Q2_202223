package projektkurs;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import formenBlueJ.Kreis;
import formenBlueJ.Leinwand;
import formenBlueJ.Linie;
import gui.GUI;

public class KonvexeHuelleVorlage {
	private Punkt[] punkte;
	int zentrumX = 300;
	int zentrumY = 200; 
	int radius = 150;
	
	Random r;
	
	private Vector<Punkt> konvexeHuelle;
	
	public KonvexeHuelleVorlage() {
		//new GUI(this);
		r = new Random();
		ausfuehren();
	}
	
	public void ausfuehren() {
	//	punkte = beispielPunkte10;
//		punkte = beispielPunkte20;
		punkte = beispielPunkteGemein;
	//	punkteZufaelligErzeugen(100);
		allePunkteAnzeigen();
		konvexeHuelleBerechnen();
	}
	
	/**
	 * Voraussetzung: die Punkte sind sortiert
	 */
	public void konvexeHuelleBerechnen() {
		konvexeHuelle = new Vector<>();
		Punkt unterster = gibPunktGanzUnten();
		System.out.println("unterster: "+unterster);
		unterster.anzeigen("rot");
		konvexeHuelle.add(unterster);
		Punkt aktuell = unterster;
		// der "Vorgaenger" hat den gleichen y-Wert wie unterster
		// liegt aber auf der x-Achse ganz weit rechts
		Punkt vorgaenger = new Punkt(-5000, unterster.gibY());
		Punkt naechsterEins =findePunktMitKleinstemWinkel(vorgaenger, unterster);
		//TODO die Schleife muss sinnvoll sein
		//Dafuer ueberlegen: Wann ist man fertig???
		int i = 1;
		while(i==1 || aktuell!=unterster){
			//TODO die naechste Zeile sinnvoll aendern.
			Punkt naechster = findePunktMitKleinstemWinkel(vorgaenger, aktuell);
			System.out.println(naechster);
			konvexeHuelle.add(naechster);
			linieZeichnen(aktuell, naechster);
			vorgaenger=aktuell;
			aktuell=naechster;
			i++;
			//TODO jetzt muss man weiter gehen!			
		}
	}
	
	/**
	 * findet den Punkt, der mit p1 und scheitelpunkt den kleinsten Winkel bildet.
	 * p1 und scheitelpunkt selber sind ausgeschlossen.
	 * @param p1
	 * @param scheitelPunkt
	 * @return
	 */
	public Punkt findePunktMitKleinstemWinkel(Punkt p1, Punkt scheitelPunkt) {
		Punkt ergebnis = null;
		// Startwert auf ueber das maximal moegliche setzen
		double minWinkel = 361;
		for(Punkt p: punkte) {
			if(p == p1 || p == scheitelPunkt) continue;
			double winkelP = winkelBerechnen(p1, scheitelPunkt, p);
			if(winkelP < minWinkel && winkelP!=0) {
				minWinkel = winkelP;
				ergebnis = p;
			}
		}
		return ergebnis;
	}
	
	/**
	 * berechnet den Schnittpunkt von zwei Strecken.
	 * wenn sich die Strecken nicht schneiden, wird null zurueckgegeben.
	 * Danke an Chat-GPT
	 * @param a1 Punkt 1 der Linie a
	 * @param a2 Punkt 2 der Linie a
	 * @param b1 Punkt 1 der Linie b
	 * @param b2 Punkt 2 der Linie b
	 * @return
	 */
	public Punkt gibSchnittpunkt(Punkt a1, Punkt a2, Punkt b1, Punkt b2) {
	    double x1 = a1.gibX();
	    double y1 = a1.gibY();
	    double x2 = a2.gibX();
	    double y2 = a2.gibY();
	    double x3 = b1.gibX();
	    double y3 = b1.gibY();
	    double x4 = b2.gibX();
	    double y4 = b2.gibY();

	    double denominator = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
	    if (denominator == 0) {
	        // The two lines are parallel or coincident
	        return null;
	    }

	    double ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denominator;
	    double ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denominator;

	    if (ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1) {
	        // The two line segments intersect within their endPunkts
	        double x = x1 + ua * (x2 - x1);
	        double y = y1 + ua * (y2 - y1);
	        return new Punkt(x, y);
	    } else {
	        // The two line segments do not intersect
	        return null;
	    }
	}
	
	public Punkt[] berechneExtremPunkte() {
		Punkt[] ergebnis = {
			gibExtremPunkt(true, false),
			gibExtremPunkt(true, true),
			gibExtremPunkt(false, false),
			gibExtremPunkt(false, true)
		};
		return ergebnis;
	}
	
	public Punkt gibPunktGanzLinks() {
		return gibExtremPunkt(true, false);
	}

	public Punkt gibPunktGanzUnten() {
		return gibExtremPunkt(false, false);
	}

	/**
	 * gibt den auessersten Punkt (max/min) in der x- bzw. y-Koordinate
	 * @param xRichtung in x-Richtung gemessen
	 * @param maximum wenn true, dann maximum, sonst minimum
	 * @return
	 */
	public Punkt gibExtremPunkt(boolean xRichtung, boolean maximum) {
		Punkt ergebnis = punkte[0];
		for(Punkt p:punkte) {
			if(xRichtung) {
				if(maximum) {
					if(p.gibX() > ergebnis.gibX()) ergebnis = p;
				}
				else {
					if(p.gibX() < ergebnis.gibX()) ergebnis = p;					
				}
			}
			else {
				if(maximum) {
					if(p.gibY() > ergebnis.gibY()) ergebnis = p;
				}
				else {
					if(p.gibY() < ergebnis.gibY()) ergebnis = p;					
				}
			}
		}
		return ergebnis;
	}
	
	public double abstandVomZentrum(int x, int y) {
		return Math.sqrt(1.0*Math.pow(x-zentrumX,2)+Math.pow(y-zentrumY,2));
	}

	/**
	 * erzeugt die Punkte zufaellig in einem Kreis 
	 * rund um das Zentrum
	 * @param pAnzahl
	 */
	public void punkteZufaelligErzeugen(int pAnzahl) {
		if(punkte != null) {
			for(Punkt p: punkte) {
				Leinwand.gibLeinwand().entferne(p);
			}
		}
		punkte = new Punkt[pAnzahl];
		for (int i = 0; i < punkte.length; i++) {
			int xNeu,yNeu;
			do{
				xNeu = zentrumX - radius + r.nextInt(2*radius);
				yNeu = zentrumY - radius + r.nextInt(2*radius);
			}while(abstandVomZentrum(xNeu, yNeu) > radius);
			punkte[i] = new Punkt(xNeu, yNeu);
		}
	}
	
	public void allePunkteAnzeigen() {
		for(Punkt p: punkte) {
			p.anzeigen();
		}
	}
	
	/**
	 * sortiert die Punkte nach x-Koordinate aufsteigend.
	 * Wenn die x-Koordinaten gleich sind, dann wird nach der y-Koordinate aufsteigend sortiert.
	 */
	public void sortieren() {
		Arrays.sort(punkte);
		for(Punkt p:punkte) {
			System.out.println(p);
		}
	}
	
	/**
	 * gibt den letzten Punkt eines Vector zurueck.
	 * @param v
	 * @return
	 */
	public Punkt letzterPunkt(Vector<Punkt> v) {
		if(v.isEmpty()) return null;
		return v.lastElement();
	}
	
	/**
	 * gibt den vorletzten Punkt eines Vector zurück.
	 * wenn der Vector kein oder nur ein Element hat, wird null zurückgegeben.
	 * @param v
	 * @return
	 */
	public Punkt vorletzterPunkt(Vector<Punkt> v) {
		if(v.size() <= 1) return null;
		return v.elementAt(v.size()-2);
	}
	
	/**
	 * loescht den letzten Punkt aus dem Vector.
	 * @param v
	 */
	private void letztenPunktLoeschen(Vector<Punkt> v) {
		if(v.isEmpty()) return;
		v.remove(v.size()-1);
		
	}

	public void linieZeichnen(Punkt p1, Punkt p2) {
		p1.anzeigen("rot");
		p2.anzeigen("rot");
		Linie l = new Linie();
		l.bewegeZuPosition((int)p1.gibX(), (int)p1.gibY(), (int)p2.gibX(), (int)p2.gibY());
		l.sichtbarMachen();		
	}
	
	

	public Double steigung(Punkt p1, Punkt p2) {
		if(p2.gibX() - p1.gibX() != 0) {
			return 1.0*(p2.gibY() - p1.gibY())/(p2.gibX() - p1.gibX());
		}
		if(p2.gibY() > p1.gibY()) {
			return Double.POSITIVE_INFINITY;
		}
		if(p2.gibY() < p1.gibY()) {
			return Double.NEGATIVE_INFINITY;
		}
		else return Double.NaN;
	}
	
	/**
	 * berechnet den Winkel des Punktes p im Verhaeltnis zum Punkt origin.
	 * Als Winkel 0 dient die positive x-Richtung.
	 * Der Winkel liegt im Intervall [0;360)
	 * Danke an CHat-GPT
	 * @param scheitelpunkt
	 * @param p
	 * @return
	 */
	public double winkelBerechnen(Punkt scheitelpunkt, Punkt p) {
	    double dx = p.gibX() - scheitelpunkt.gibX();
	    double dy = p.gibY() - scheitelpunkt.gibY();
	    double angle = Math.atan2(dy, dx);
	    if (angle < 0) {
	        angle += 2 * Math.PI;
	    }
	    return Math.toDegrees(angle);
	}

	/**
	 * berechnet den Winkel (von 0 bis 360)
	 * @param p1
	 * @param scheitelpunkt
	 * @param p2
	 * @return
	 */
	public double winkelBerechnen(Punkt p1, Punkt scheitelpunkt, Punkt p2) {
	    double winkel1 = winkelBerechnen(scheitelpunkt, p1);
	    double winkel2 = winkelBerechnen(scheitelpunkt, p2);
	    double angle = winkel2 - winkel1;
	    if (angle < 0) {
	        angle += 360;
	    }
	    return angle;
	}

	
	
	public static void main(String[] args) {
		KonvexeHuelleVorlage kh = new KonvexeHuelleVorlage();
	}
	
	public Punkt[] beispielPunkte20 = {
			new Punkt(185,127),
			new Punkt(191,279),
			new Punkt(216,186),
			new Punkt(225,180),
			new Punkt(225,339),
			new Punkt(254,278),
			new Punkt(268,220),
			new Punkt(292,86),
			new Punkt(330,293),
			new Punkt(334,155),
			new Punkt(336,109),
			new Punkt(336,110),
			new Punkt(348,98),
			new Punkt(349,74),
			new Punkt(365,154),
			new Punkt(369,69),
			new Punkt(369,77),
			new Punkt(393,168),
			new Punkt(396,155),
			new Punkt(429,203)	
	};

	public Punkt[] beispielPunkte10 = {
			new Punkt(185,110),
			new Punkt(185,127),
			new Punkt(292,86),
			new Punkt(336,109),
			new Punkt(336,110),
			new Punkt(365,154),
			new Punkt(369,69),
			new Punkt(369,77),
			new Punkt(393,168),
			new Punkt(429,203)	
	};
	
	public Punkt[] beispielPunkteGemein = {
			new Punkt(200,100),
			new Punkt(400,300),
			new Punkt(300,300),
			new Punkt(200,300),
			new Punkt(100,200),
	};
	
	
	
	private class Punkt implements Comparable<Punkt> {
		private double x;
		private double y;
		
		public Punkt(double x2, double y2) {
			this.x = x2;
			this.y = y2;
		}
		
		public double gibX() {
			return x;
		}
		
		public double gibY() {
			return y;
		}

		@Override
		public int compareTo(Punkt p) {
			return (int) (this.x*10000 + this.y - p.x*10000 - p.y);
		}

		public void anzeigen() {
			Kreis kreis = new Kreis();
			kreis.bewegeZuPosition((int) gibX(), (int) gibY());
			kreis.groesseAendern(3);
			kreis.farbeAendern("blau");
			kreis.sichtbarMachen();		
		}
		
		public void anzeigen(String farbe) {
			Kreis kreis = new Kreis();
			kreis.bewegeZuPosition((int) gibX(), (int) gibY());
			kreis.groesseAendern(3);
			kreis.farbeAendern(farbe);
			kreis.sichtbarMachen();		
		}
		
		
		public String toString() {
			return "{"+this.x+","+this.y+"}";
		}
	}
	
	
}
