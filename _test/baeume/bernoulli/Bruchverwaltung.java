package _test.baeume.bernoulli;

import linear.List;
import linear.ListWithViewer;
import baeume.BinaryTree;
import baeume.TreeViewer;
import gui.GUI;

public class Bruchverwaltung {
	private BinaryTree<Bruch> baum;

	public Bruchverwaltung() {
		neuerBaum(4, 1, 6);
		new GUI(this);
	}

		
	public Bruch summe(Bruch pBruch){
		BinaryTree<Bruch> bruchBaum = baum;
		
		if(bruchBaum.isEmpty()) {
			return new Bruch(0,1);
		}
		if(!bruchBaum.isEmpty()) {
			return bruchBaum.getContent();
		}
		Bruch ergebnis = summe(bruchBaum.getLeftTree().getContent()).addierenMit(summe(bruchBaum.getRightTree().getContent()).addierenMit(bruchBaum.getContent()));
		
		
		return ergebnis;
	}

	public Bruch finde(String pLinie){
		//TODO
		return null;
	}
	
	public List<Bruch> zweiErfolg(){
		return zweiErfolg(baum, 0);
	
	}
	public List<Bruch> zweiErfolg(BinaryTree<Bruch> pTree, int pErfolg){
		//TODO
		
		List<Bruch> ergebnis = new ListWithViewer<>();
		if(pTree.isEmpty()) {
			return ergebnis;
		}
		if(pErfolg==2) {
			ergebnis.append(pTree.getContent());
			return ergebnis;
		}
		ergebnis.concat(zweiErfolg(pTree.getLeftTree(), pErfolg));
		ergebnis.concat(zweiErfolg(pTree.getRightTree(), pErfolg+1));
		return ergebnis;
	}
	
	private BinaryTree<Bruch> findeStartKnoten(int pEtage){
		BinaryTree<Bruch> b = baum;
		for(int i=0;i<pEtage;i++) {
			b = baum.getLeftTree();
		}
		return b;
	}

	private BinaryTree<Bruch> findeEndKnoten(int pEtage){
		BinaryTree<Bruch> b = baum;
		for(int i=0;i<pEtage;i++) {
			b = baum.getRightTree();
		}
		return b;
	}

	public List<Bruch> etagenListe(int pEtage){
		BinaryTree<Bruch> b = baum;
		List<BinaryTree<Bruch>> baumListe = new ListWithViewer<>();
		baumListe.append(b);
		baumListe.toFirst();
		while(baumListe.hasAccess()) {
			b = baumListe.getContent();
			if(!b.getLeftTree().isEmpty()) {
				baumListe.append(b.getLeftTree());
			}
			if(!b.getRightTree().isEmpty()) {
				baumListe.append(b.getRightTree());
			}
			baumListe.next();
		}
		baumListe.toFirst();
		List<Bruch> ergebnis = new ListWithViewer<>();
		while(baumListe.hasAccess()) {
			if(baumListe.getContent()==findeStartKnoten(pEtage)) {
				baumListe.next();
				ergebnis.append(findeStartKnoten(pEtage).getContent());
				while(baumListe.getContent()!=findeEndKnoten(pEtage)) {
					ergebnis.append(baumListe.getContent().getContent());
					baumListe.next();
				}
				return ergebnis;
			}
			baumListe.next();
			
		}

 		return ergebnis;
	}
	
	
	
	public void neuerBaum(int pZahl, int pZ1, int pZ2){
		Bruch b1 = new Bruch(pZ1, pZ2);
		Bruch b2 = new Bruch(pZ2 - pZ1, pZ2);

		Bruch eins = new Bruch(1,1);
		// Hier wird auf das Attribut baum zugegriffen!
		baum = new BinaryTree<Bruch>(eins);

		List<BinaryTree<Bruch>> listeA = new List<BinaryTree<Bruch>>();
		listeA.append(baum);
		// *** x1 ***
		for(int i=0; i<pZahl; i++){
			List<BinaryTree<Bruch>> listeB = new List<BinaryTree<Bruch>>();
			for(listeA.toFirst(); listeA.hasAccess(); listeA.next()){
				BinaryTree<Bruch> derBaum = listeA.getContent();
				Bruch derBruch = derBaum.getContent();
				Bruch neuLinks = derBruch.multipliziereMit(b2);
				BinaryTree<Bruch> neuBaumLinks = new BinaryTree<Bruch>(neuLinks);
				derBaum.setLeftTree(neuBaumLinks);
				listeB.append(neuBaumLinks);
				Bruch neuRechts = derBruch.multipliziereMit(b1);
				BinaryTree<Bruch> neuBaumRechts = 
						new BinaryTree<Bruch>(neuRechts);
				derBaum.setRightTree(neuBaumRechts);
				listeB.append(neuBaumRechts);
			}
			// *** x2 ***
			listeA = listeB;
		}
		TreeViewer.showTree(baum, 1000,600);
	}



	public static void main(String[] args) {
		new Bruchverwaltung();
	}
}
