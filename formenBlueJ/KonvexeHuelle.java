package formenBlueJ;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

import gui.GUI;

public class KonvexeHuelle {
	private Punkt[] punkte;
	int zentrumX = 300;
	int zentrumY = 200; 
	int radius = 150;
	
	Random r;
	
	private Vector<Punkt> konvexeHuelle;
	
	public KonvexeHuelle() {
		r = new Random();
		new GUI(this);
		ausfuehren();
	}
	
	public static double calculateAngle(Punkt point1, Punkt point2, Punkt point3) {
	    double[] vector1 = new double[2];
	    double[] vector2 = new double[2];
	    vector1[0] = point2.gibX() - point1.gibX();
	    vector1[1] = point2.gibY() - point1.gibY();
	    vector2[0] = point3.gibX() - point2.gibX();
	    vector2[1] = point3.gibY() - point2.gibY();
	    double dotProduct = vector1[0]*vector2[0] + vector1[1]*vector2[1];
	    double magVector1 = Math.sqrt(vector1[0]*vector1[0] + vector1[1]*vector1[1]);
	    double magVector2 = Math.sqrt(vector2[0]*vector2[0] + vector2[1]*vector2[1]);
	    double cosTheta = dotProduct / (magVector1 * magVector2);
	    double theta = Math.acos(cosTheta);
	    return Math.toDegrees(theta);
	}
	
	public void ausfuehren() {
//		punkte = beispielPunkte10;
		punkte = beispielPunkte20;
//		punkteZufaelligErzeugen(100);
		allePunkteAnzeigen();
		sortieren();
		konvexeHuelleBerechnen();
		punkteVerbinden(konvexeHuelle);
		
//		Punkt[] extremPunkte = berechneExtremPunkte();
//		for(Punkt p:extremPunkte) {
//			p.anzeigen("rot");
//		}
//		Punkt mitte = gibSchnittpunkt(extremPunkte[0], extremPunkte[1], extremPunkte[2], extremPunkte[3]);
//		mitte.anzeigen("rot");
	}
	
	/**
	 * Voraussetzung: die Punkte sind sortiert
	 */
	public void konvexeHuelleBerechnen() {
		konvexeHuelle = new Vector<>();
		konvexeHuelle.add(punkte[0]);
		konvexeHuelle.add(punkte[1]);
		//TODO
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
		Linie l = new Linie();
		l.bewegeZuPosition((int)p1.gibX(), (int)p1.gibY(), (int)p2.gibX(), (int)p2.gibY());
		l.sichtbarMachen();		
	}
	
	public void punkteVerbinden(Vector<Punkt> v) {
		for(int i=0; i<v.size()-1; i++) {
			Punkt p1 = v.elementAt(i);
			Punkt p2 = v.elementAt(i+1);
			linieZeichnen(p1, p2);
		}
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
	 * @param origin
	 * @param p
	 * @return
	 */
	public double winkelBerechnen(Punkt origin, Punkt p) {
	    double dx = p.gibX() - origin.gibX();
	    double dy = p.gibY() - origin.gibY();
	    double angle = Math.atan2(dy, dx);
	    if (angle < 0) {
	        angle += 2 * Math.PI;
	    }
	    return angle*180/Math.PI;
	}


	
	
	public static void main(String[] args) {
		KonvexeHuelle kh = new KonvexeHuelle();
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
