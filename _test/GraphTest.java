package _test;


import java.util.Vector;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import graph.GraphWithViewer;
import gui.GUI;
import linear.List;
import linear.ListWithViewer;
import sun.util.resources.cldr.ext.LocaleNames_sr_Cyrl_BA;


public class GraphTest {

	
	public ListWithViewer<Vertex> kuerzesteStrecke = new ListWithViewer<>();
	public ListWithViewer<Vertex> streckeImMoment = new ListWithViewer<>();
	private double kuerzesteDistanz;

	public void kopiereInKuerzesteListe(List<Vertex> pList) {
		// alle rausschmeissen
		for(kuerzesteStrecke.toFirst();kuerzesteStrecke.hasAccess(); kuerzesteStrecke.remove());
		// uebertragen
		for(pList.toFirst();pList.hasAccess(); pList.next()) {
			kuerzesteStrecke.append(pList.getContent());
		}
	}
	
	public List<Vertex> kuerzesteStreckeTest(){
		return kuerzesteStrecke("Kassel", "Kassel");
	}

	public List<Vertex> kuerzesteStrecke(String pStart, String pZiel){
		kuerzesteDistanz = 1000000;
		
		kuerzesteStrecke(karte.getVertex(pStart), karte.getVertex(pZiel), 0.0, 0);
		
		return kuerzesteStrecke;
		
	}

	private void kuerzesteStrecke(Vertex pStart, Vertex pZiel, double pKmBisher, int schritt) {
		if(pKmBisher>kuerzesteDistanz) {
			return;
		}
		if(pStart==pZiel && pKmBisher>0 && schritt > 2) {
			kopiereInKuerzesteListe(streckeImMoment);
			kuerzesteStrecke.append(pZiel);
			kuerzesteDistanz=pKmBisher;
			System.out.println(kuerzesteDistanz);
			return;
		}
		if(pStart.isMarked()){
			return;
		}
		pStart.setMark(true);
		streckeImMoment.append(pStart);
		List<Vertex> nachbarn = karte.getNeighbours(pStart);
		for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
			Vertex n = nachbarn.getContent();

			Edge e = karte.getEdge(pStart, n);
			if(e.isMarked()) {
				kuerzesteStrecke(n,pZiel,pKmBisher+e.getWeight(),schritt+1);
			}else {
				e.setMark(true);
				kuerzesteStrecke(n,pZiel,pKmBisher+e.getWeight(),schritt+1);
				e.setMark(false);
			}
			
			
			
			
		}
		pStart.setMark(false);
		streckeImMoment.toLast();
		streckeImMoment.remove();		
	}

	
	
	
	
	
	
	public List<Vertex> kuerzesteRundreiseTest(){
		return kuerzesteRundreise1("Bad Honnef");
	}

	public List<Vertex> kuerzesteRundreise1(String pStart){
		kuerzesteDistanz = 1000000;
		
		kuerzesteRundreise(karte.getVertex(pStart), karte.getVertex(pStart), 0.0, 0);
		
		return kuerzesteStrecke;
		
	}
	
	
	private void kuerzesteRundreise(Vertex pStart, Vertex pZiel, double pKmBisher, int schritt) {
		if(pKmBisher>kuerzesteDistanz) {
			return;
		}
		if(pStart==pZiel && pKmBisher>0 && schritt > 2) {
			kopiereInKuerzesteListe(streckeImMoment);
			kuerzesteStrecke.append(pZiel);
			kuerzesteDistanz=pKmBisher;
			System.out.println(kuerzesteDistanz);
			return;
		}
		if(pStart.isMarked()){
			return;
		}
		pStart.setMark(true);
		streckeImMoment.append(pStart);
		List<Vertex> nachbarn = karte.getNeighbours(pStart);
		for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
			Vertex n = nachbarn.getContent();
			Edge e = karte.getEdge(pStart, n);
			if(e.isMarked()) {
				kuerzesteStrecke(n,pZiel,pKmBisher+e.getWeight(),schritt+1);
			}else {
				e.setMark(true);
				kuerzesteStrecke(n,pZiel,pKmBisher+e.getWeight(),schritt+1);
				e.setMark(false);
			}		
		}
		pStart.setMark(false);
		streckeImMoment.toLast();
		streckeImMoment.remove();		
	}

	
	
	
	
	
	
	
	public List<Vertex> travellingTest(){
		return kuerzesteTravelling1("Muenchen");
	}

	public List<Vertex> kuerzesteTravelling1(String pStart){
		kuerzesteDistanz = 1000000;
		
		kuerzesteTravelling(karte.getVertex(pStart), karte.getVertex(pStart), 0.0, 0);
		
		return kuerzesteStrecke;
		
	}
	
	public int wieLangIstListe(List<Vertex> pList) {
		int i = 0;
		for(pList.toFirst();pList.hasAccess();pList.next()) {
			i++;
		}
		return i;
	}
	
	
	private void kuerzesteTravelling(Vertex pStart, Vertex pZiel, double pKmBisher, int schritt) {
		if(pKmBisher>kuerzesteDistanz) {
			return;
		}
		int laenge = wieLangIstListe(karte.getVertices());
		if(pStart==pZiel && pKmBisher>0 && schritt > laenge-1) {
			kopiereInKuerzesteListe(streckeImMoment);
			kuerzesteStrecke.append(pZiel);
			kuerzesteDistanz=pKmBisher;
			System.out.println(kuerzesteDistanz);
			return;
		}
		if(pStart.isMarked()){
			return;
		}
		pStart.setMark(true);
		streckeImMoment.append(pStart);
		List<Vertex> nachbarn = karte.getNeighbours(pStart);
		for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
			Vertex n = nachbarn.getContent();
			Edge e = karte.getEdge(pStart, n);
			if(e.isMarked()) {
				kuerzesteTravelling(n,pZiel,pKmBisher+e.getWeight(),schritt+1);
			}else {
				e.setMark(true);
				kuerzesteTravelling(n,pZiel,pKmBisher+e.getWeight(),schritt+1);
				e.setMark(false);
			}		
		}
		pStart.setMark(false);
		streckeImMoment.toLast();
		streckeImMoment.remove();		
	}
	
	
	
	
	
	
	
	// Attribut, in dem die Karte gespeichert wird.
	public GraphWithViewer karte;

	// Rahmenmethode zum testen
	public List<Vertex> tiefendurchlaufVonKassel(){
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		return tiefendurchlauf(vKassel);
	}
	
	// rekursive Methode
	private List<Vertex> tiefendurchlauf(Vertex pVertex) {
		List<Vertex> ergebnis = new ListWithViewer<>();
//		if(pVertex.isMarked()) {
//			return ergebnis;
//		}
		ergebnis.append(pVertex);
		List<Vertex> nachbarn = karte.getNeighbours(pVertex);
		for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
			if(!nachbarn.getContent().isMarked()) {
				nachbarn.getContent().setMark(true);
				ergebnis.concat(tiefendurchlauf(nachbarn.getContent()));
				
			}
		}
		return ergebnis;
	}
	
	public List<Vertex> erreichbarInVonKassel(double pKm){
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		return erreichbarIn(vKassel, pKm);
	}
	
	private List<Vertex> erreichbarIn(Vertex pVertex, double pKm) {
		List<Vertex> ergebnis = new ListWithViewer<>();
		ergebnis.append(pVertex);
		List<Vertex> nachbarn = karte.getNeighbours(pVertex);
		for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
			if(!nachbarn.getContent().isMarked()) {
				if(!(karte.getEdge(nachbarn.getContent(), pVertex).getWeight()>pKm)) {
					List<Vertex> n = erreichbarIn(nachbarn.getContent(), pKm-karte.getEdge(nachbarn.getContent(), pVertex).getWeight());
					nachbarn.getContent().setMark(true);
					ergebnis.concat(n);
				}
			}
		}
		return ergebnis;
	}
	
	
	
	
	public List<Vertex> kuerzesteRundreise(String pStart){
		Vertex vKassel = karte.getVertex(pStart);
		List<Vertex> eg = kuerzesteRundreise(vKassel, vKassel);
		return eg;
	}
	
	public List<Vertex> kuerzesteRundreise(Vertex pVertex, Vertex pStart){
		List<Vertex> ergebnis = new ListWithViewer<>();
		List<Vertex> nachbarZuStart= new ListWithViewer<>();
//		ergebnis.append(pVertex);
		List<Vertex> nachbarn = karte.getNeighbours(pVertex);
		for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
			if(!nachbarn.getContent().isMarked()) {
				
					if(nachbarn.getContent()==pStart) {
						nachbarZuStart.append(pVertex);
						pVertex.setMark(true);
						return nachbarZuStart;
					}
					
//					nachbarn.getContent().setMark(true);
					
					List<Vertex> n = kuerzesteRundreise(nachbarn.getContent(), pStart);
					ergebnis.concat(n);
				
			}
		}
		return ergebnis;
	}
	
	
//	public List<Vertex> erreichbarIn (Vertex pStart, int pKm){
//		
//	}

	// Rahmenmethode zum testen
	public List<Vertex> breitendurchlaufVonKassel(){
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		return breitendurchlauf(vKassel);
	}
	
	// NICHT-rekursive Methode
	private List<Vertex> breitendurchlauf(Vertex pVertex) {
		List<Vertex> ergebnis = new ListWithViewer<>();
		karte.setAllVertexMarks(false);
		Vertex vStart = pVertex;
		vStart.setMark(true);
		ergebnis.append(vStart);
		List<Vertex> nachbarn = karte.getNeighbours(vStart);
		ergebnis.concat(nachbarn);
		for(ergebnis.toFirst();ergebnis.hasAccess();ergebnis.next()) {
			Vertex aktuell = ergebnis.getContent();
			List<Vertex> nachbarnVonAktuell= karte.getNeighbours(aktuell);
			for(nachbarnVonAktuell.toFirst();nachbarnVonAktuell.hasAccess();nachbarnVonAktuell.next()) {
				if(!nachbarnVonAktuell.getContent().isMarked()) {
					ergebnis.append(nachbarnVonAktuell.getContent());
					nachbarnVonAktuell.getContent().setMark(true);
				}
			}
		}
		
		return ergebnis;

	}

	
	
	
	public List<Vertex> breiten2durchlaufVonKassel(){
		karte.setAllVertexMarks(false);
		karte.setAllEdgeMarks(false);
		Vertex vKassel = karte.getVertex("Kassel");
		return breiten2(vKassel);
	}
	
	public List<Vertex> breiten2(Vertex pVertex){
		List<Vertex> ergebnis = new ListWithViewer<>();
		karte.setAllVertexMarks(false);
		ergebnis.append(pVertex);
		pVertex.setMark(true);
		List<Vertex> nachbarStart = karte.getNeighbours(pVertex);
		for(ergebnis.toFirst();ergebnis.hasAccess();ergebnis.next()) {
			Vertex aktuellV = ergebnis.getContent();
			List<Vertex> nachbarn = karte.getNeighbours(aktuellV);
			for(nachbarn.toFirst();nachbarn.hasAccess();nachbarn.next()) {
				Vertex nA = nachbarn.getContent();
				if(!nA.isMarked()) {
					ergebnis.append(nA);
					nA.setMark(true);
					karte.getEdge(aktuellV, nA).setMark(true);
					
				}
			}
			
		}
		return ergebnis;
	}
	
	
	private List<Vertex> erreichbarIn2(Vertex pVertex, int pKm) {
		List<Vertex> ergebnis = new ListWithViewer<>();
		
		karte.setAllVertexMarks(false);
		Vertex vStart = pVertex;
		vStart.setMark(true);
		ergebnis.append(vStart);
		List<Vertex> nachbarn = karte.getNeighbours(vStart);
		ergebnis.concat(nachbarn);
		for(ergebnis.toFirst();ergebnis.hasAccess();ergebnis.next()) {
			Vertex aktuell = ergebnis.getContent();
			List<Vertex> nachbarnVonAktuell= karte.getNeighbours(aktuell);
			for(nachbarnVonAktuell.toFirst();nachbarnVonAktuell.hasAccess();nachbarnVonAktuell.next()) {
				if(!nachbarnVonAktuell.getContent().isMarked()) {
					ergebnis.append(nachbarnVonAktuell.getContent());
					nachbarnVonAktuell.getContent().setMark(true);
				}
			}
		}
		
		return ergebnis;

	}


	public GraphTest(){
		karte = new GraphWithViewer();
		Vertex dortmund = new Vertex("Dortmund");
		karte.addVertex(dortmund);
		Vertex koeln = new Vertex("Koeln");
		karte.addVertex(koeln);
		Vertex frankfurt = new Vertex("Frankfurt");
		karte.addVertex(frankfurt);
		Vertex kassel = new Vertex("Kassel");
		karte.addVertex(kassel);
		Vertex wuerzburg = new Vertex("Wuerzburg");
		karte.addVertex(wuerzburg);

		Edge kassel_dortmund = new Edge(kassel, dortmund, 160);
		karte.addEdge(kassel_dortmund);
		Edge dortmund_koeln = new Edge(dortmund, koeln, 93);
		karte.addEdge(dortmund_koeln);
		Edge frankfurt_kassel = new Edge(frankfurt, kassel, 193);
		karte.addEdge(frankfurt_kassel);
		Edge kassel_wuerzburg = new Edge(kassel, wuerzburg, 209);
		karte.addEdge(kassel_wuerzburg);
		Edge wuerzburg_frankfurt = new Edge(wuerzburg, frankfurt, 119);
		karte.addEdge(wuerzburg_frankfurt);
		Edge frankfurt_koeln = new Edge(frankfurt, koeln, 189);
		karte.addEdge(frankfurt_koeln);

		// *** weitere Vertices und Edges! ***
		
		Vertex hamburg = new Vertex("Hamburg");
		karte.addVertex(hamburg);
		Vertex berlin = new Vertex("Berlin");
		karte.addVertex(berlin);
		Vertex bremen = new Vertex("Bremen");
		karte.addVertex(bremen);
		Vertex hannover = new Vertex("Hannover");
		karte.addVertex(hannover);
		Vertex leipzig = new Vertex("Leipzig");
		karte.addVertex(leipzig);
		Vertex nuernberg = new Vertex("Nuernberg");
		karte.addVertex(nuernberg);
		Vertex stuttgart = new Vertex("Stuttgart");
		karte.addVertex(stuttgart);
		Vertex muenchen = new Vertex("Muenchen");
		karte.addVertex(muenchen);
		Vertex karlsruhe = new Vertex("Karlsruhe");
		karte.addVertex(karlsruhe);
		Vertex aachen = new Vertex("Bad Honnef");
		karte.addVertex(aachen);

		Edge e = new Edge(berlin, hamburg, 289);
		karte.addEdge(e);
		Edge hamburg_bremen = new Edge(hamburg, bremen, 119);
		karte.addEdge(hamburg_bremen);
		Edge bremen_hannover = new Edge(bremen, hannover, 122);
		karte.addEdge(bremen_hannover);
		Edge hannover_hamburg = new Edge(hannover, hamburg, 150);
		karte.addEdge(hannover_hamburg);
		Edge berlin_hannover = new Edge(berlin, hannover, 290);
		karte.addEdge(berlin_hannover);
		Edge berlin_leipzig = new Edge(berlin, leipzig, 188);
		karte.addEdge(berlin_leipzig);
		Edge hannover_kassel = new Edge(hannover, kassel, 167);
		karte.addEdge(hannover_kassel);
		Edge leipzig_kassel = new Edge(leipzig, kassel, 250);
		karte.addEdge(leipzig_kassel);
		Edge dortmund_bremen = new Edge(dortmund, bremen, 234);
		karte.addEdge(dortmund_bremen);
		Edge dortmund_hannover = new Edge(dortmund, hannover, 210);
		karte.addEdge(dortmund_hannover);
		Edge leipzig_nuernberg = new Edge(leipzig, nuernberg, 278);
		karte.addEdge(leipzig_nuernberg);
		Edge wuerzburg_nuernberg = new Edge(wuerzburg, nuernberg, 110);
		karte.addEdge(wuerzburg_nuernberg);
		Edge nuernberg_muenchen = new Edge(nuernberg, muenchen, 166);
		karte.addEdge(nuernberg_muenchen);
		Edge muenchen_stuttgart = new Edge(muenchen, stuttgart, 223);
		karte.addEdge(muenchen_stuttgart);
		Edge nuernberg_stuttgart = new Edge(nuernberg, stuttgart, 208);
		karte.addEdge(nuernberg_stuttgart);
		Edge stuttgart_karlsruhe = new Edge(stuttgart, karlsruhe, 82);
		karte.addEdge(stuttgart_karlsruhe);
		Edge karlsruhe_frankfurt = new Edge(karlsruhe, frankfurt, 147);
		karte.addEdge(karlsruhe_frankfurt);
		Edge aachen_frank = new Edge(aachen, frankfurt, 151);
		karte.addEdge(aachen_frank);
		Edge aachen_dortmund = new Edge(aachen, dortmund, 127);
		karte.addEdge(aachen_dortmund);
		Edge aachen_koelle = new Edge(aachen, koeln, 52);
		karte.addEdge(aachen_koelle);


		// auf ein geeignetes Layout umstellen
		karte.switchToISOMLayout();
	}
	
	public void printMatrix() {
		int anzahlStaedte = 0;
		Vector<String> staedte = new Vector<String>();
		String staedteString = "staedte = [";
		List<Vertex> vertices = karte.getVertices();
		for(vertices.toFirst(); vertices.hasAccess(); vertices.next()) {
			String stadt = vertices.getContent().getID();
			staedteString += "'"+stadt+"'"+",";
			staedte.add(stadt);
			anzahlStaedte++;
		}
		staedteString = staedteString.substring(0,staedteString.length()-1);
		staedteString += "]";
		System.out.println(staedteString);
		int[][] entfernungen = new int[anzahlStaedte][anzahlStaedte];
		List<Edge> edges = karte.getEdges();
		for(edges.toFirst(); edges.hasAccess(); edges.next()) {
			Edge e = edges.getContent();
			double weight = e.getWeight();
			Vertex v0 = e.getVertices()[0];
			Vertex v1 = e.getVertices()[1];
			int index0 = staedte.indexOf(v0.getID());		
			int index1 = staedte.indexOf(v1.getID());
			entfernungen[index0][index1] = (int)weight;
			entfernungen[index1][index0] = (int)weight;
		}
		System.out.println();
		System.out.println("entfernungen = []");
		for(int[] zeile:entfernungen) {
			String z = "entfernungen.add([";
			for(int zelle:zeile) {
				z += zelle + ",";
			}
			z = z.substring(0,z.length()-1);
			z += "])";
			System.out.println(z);
			
		}
		
	}
	

	
	public static void main(String[] args) {
		GraphTest gt = new GraphTest();
		new GUI(gt);
	}

}
