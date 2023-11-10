package _test.baeume.ahnenverwaltung;
import gui.GUI;
import linear.List;
import linear.ListWithViewer;
import linear.Queue;
import baeume.BinaryTree;
import baeume.TreeViewer;


public class Ahnenverwaltung {
	public BinaryTree<Ahne> ahnenBaum;
	private Ahne JustusNeu = new Ahne(null, "JustusNeu", "m", null);

	public Ahnenverwaltung(){
		init();
	}

	public void init(){
		Ahne Justus = new Ahne(null, "Justus", "m", null);
		
		Ahne Margit = new Ahne(null, "Margit", "f", null);
		Ahne Antonie = new Ahne(null, "Antonie", "f", null);
		Ahne Robert = new Ahne(null, "Robert", "m", null);
		Ahne Werner = new Ahne(null, "Werner", "m", null);
		Ahne Elisabeth = new Ahne(null, "Elisabeth", "f", null);
		Ahne Bernhard = new Ahne(null, "Bernhard", null, null);
		Ahne Henry = new Ahne(null, "Henry", "m", null);
		Ahne Lotte1 = new Ahne(null, "Lotte", "f", null);
		Ahne Heinz = new Ahne(null, "Heinz", "m", null);
		Ahne Lotte2 = new Ahne(null, "Lotte", "f", null);

		ahnenBaum = new BinaryTree<Ahne>(Justus);
		BinaryTree<Ahne> margitTree = new BinaryTree<Ahne>(Margit);
		BinaryTree<Ahne> antonieTree = new BinaryTree<Ahne>(Antonie);
		BinaryTree<Ahne> robertTree = new BinaryTree<Ahne>(Robert);
		BinaryTree<Ahne> wernerTree = new BinaryTree<Ahne>(Werner);
		BinaryTree<Ahne> elisabethTree = new BinaryTree<Ahne>(Elisabeth);
		BinaryTree<Ahne> bernhardTree = new BinaryTree<Ahne>(Bernhard);
		BinaryTree<Ahne> henryTree = new BinaryTree<Ahne>(Henry);
		BinaryTree<Ahne> lotte1Tree = new BinaryTree<Ahne>(Lotte1);
		BinaryTree<Ahne> heinzTree = new BinaryTree<Ahne>(Heinz);
		BinaryTree<Ahne> lotte2Tree = new BinaryTree<Ahne>(Lotte2);

		ahnenBaum.setLeftTree(margitTree);
		margitTree.setLeftTree(antonieTree);
		antonieTree.setRightTree(robertTree);
		margitTree.setRightTree(wernerTree);
		wernerTree.setLeftTree(elisabethTree);
		wernerTree.setRightTree(bernhardTree);
		ahnenBaum.setRightTree(henryTree);
		henryTree.setLeftTree(lotte1Tree);
		henryTree.setRightTree(heinzTree);
		heinzTree.setLeftTree(lotte2Tree);
		show();
	}

	public void show(){
		TreeViewer.showTree(ahnenBaum,600,400);
	}

	/**
	 * LK Aufgabe c)
	 */
	public void fuegeHinzu(){
		fuegeHinzu(JustusNeu, "mfm");
	}
	
	public void fuegeHinzu(Ahne pAhne, String pLinie){
		BinaryTree<Ahne> aB = ahnenBaum;
		BinaryTree<Ahne> test = new BinaryTree<Ahne>(pAhne);
		for(int i=0; i<pLinie.length(); i++) {
			
				if(pLinie.charAt(i) =='f') {
					aB = aB.getLeftTree();
				}
				else {
					aB = aB.getRightTree();
				}
		}
		if(pAhne.gibGender().equals("m")) {
			BinaryTree<Ahne> rechts = aB.getRightTree();
			aB.setRightTree(test);
			aB.getRightTree().setRightTree(rechts);
		}
		else {
			BinaryTree<Ahne> links = aB.getLeftTree();
			aB.setLeftTree(test);
			aB.getLeftTree().setLeftTree(links);
		}
	}
	
	public List<String> alleVornamen() {
		return alleVornamen(ahnenBaum);
	}
	
	public List<String> alleVornamen(BinaryTree<Ahne> pTree) {
		List<String> ergebnis = new ListWithViewer<String>();
		//TODO
		if(pTree.isEmpty()) {
			return ergebnis;
		}
		ergebnis.append(pTree.getContent().gibVorname());
		ergebnis.concat(alleVornamen(pTree.getLeftTree()));
		ergebnis.concat(alleVornamen(pTree.getRightTree()));
		return ergebnis;
	}

	public List<Ahne> fehlendeVorfahren() {
		List<Ahne> ergebnis = new List<Ahne>();
		//TODO
		return ergebnis;
	}
	
	/**
	 * LK Aufgabe d)
	 */
	public int ermittleGeneration(String pVorname) {
		return ermittleGeneration(ahnenBaum, pVorname, 0);
	}
	public int ermittleGeneration(BinaryTree<Ahne> pTree, String pVorname, int pT){
		pT+=1;
		int ergebnis = 0;
		if (pTree.isEmpty()){
			return 0;
		}
		if (pTree.getContent().gibVorname().equals(pVorname)){
			return pT;
		}
		ergebnis += ermittleGeneration(pTree.getLeftTree(), pVorname, pT);
		ergebnis += ermittleGeneration(pTree.getRightTree(), pVorname, pT);
		return ergebnis;
	}

	public int ermittleGenNeu(String pVorname) {

		List<Integer> sideL = new ListWithViewer<>();
		List<BinaryTree<Ahne>> bL = new ListWithViewer<>();
		sideL.append(1);
		sideL.toFirst();
		bL.append(ahnenBaum);
		for(bL.toFirst();bL.hasAccess();bL.next()) {
			BinaryTree<Ahne> b = bL.getContent();
			if(!b.getLeftTree().isEmpty()) {
				bL.append(b.getLeftTree());
				sideL.append(sideL.getContent()+1);
				
			}
			if(!b.getRightTree().isEmpty()) {
				bL.append(b.getRightTree());
				sideL.append(sideL.getContent()+1);
				
			}
			sideL.next();
			
		}
		sideL.toFirst();
		for(bL.toFirst();bL.hasAccess();bL.next()) {
			
			if(bL.getContent().getContent().gibVorname().equals(pVorname)) {
				return sideL.getContent();
			}
			sideL.next();
		}
		return 0;
	}
	
	public List<String> ermittleNamenAusGen(int pGen) {

		List<Integer> sideL = new ListWithViewer<>();
		List<BinaryTree<Ahne>> bL = new ListWithViewer<>();
		List<String> ergebnis = new ListWithViewer<>();
		sideL.append(1);
		sideL.toFirst();
		bL.append(ahnenBaum);
		for(bL.toFirst();bL.hasAccess();bL.next()) {
			BinaryTree<Ahne> b = bL.getContent();
			if(!b.getLeftTree().isEmpty()) {
				bL.append(b.getLeftTree());
				sideL.append(sideL.getContent()+1);
				
			}
			if(!b.getRightTree().isEmpty()) {
				bL.append(b.getRightTree());
				sideL.append(sideL.getContent()+1);
				
			}
			sideL.next();
			
		}
		sideL.toFirst();
		bL.toFirst();
		for(sideL.toFirst();sideL.hasAccess();sideL.next()) {
			if(sideL.getContent()==pGen) {
				ergebnis.append(bL.getContent().getContent().gibVorname());
			}
			bL.next();
		}
		return ergebnis;
	}
	

	/**
	 * Gk Aufgabe c
	 */
	public int wasTueIchGk (String vorname){ 
		return wasTueIchGk(ahnenBaum, 1, null, vorname, null);
	}

	/**
	 * Gk Aufgabe c
	 */
	public int wasTueIchGk(BinaryTree<Ahne> pBaum, int pT, String pName, String pVorname, String pGebDatum){
		System.out.println(pBaum.getContent()+","+pT+","+pName+","+pVorname+","+pGebDatum+")");
		if(pBaum.isEmpty()){
			System.out.println("null -> 0");
			return 0;
		}
		else{
			Ahne lAhne = (Ahne)pBaum.getContent();
			// Auf Vergleich von Name und Geburtsdatum wird verzichtet.
			if(pVorname.equals(lAhne.gibVorname())){
				System.out.println(lAhne.gibVorname()+" -> "+pT);
				return pT;
			}
			else{
				int ergebnis =  
						this.wasTueIchGk(pBaum.getLeftTree(), pT+1, pName, pVorname, pGebDatum) 
						+ this.wasTueIchGk(pBaum.getRightTree(), pT+1, pName, pVorname, pGebDatum);
				System.out.println(lAhne.gibVorname()+" -> "+ergebnis);
				return ergebnis;
			}
		}
	}

	public String wasTueIchLk() {
		String lString ="";
		Queue<BinaryTree<Ahne>> lQueue = new Queue<BinaryTree<Ahne>>();
		lQueue.enqueue(ahnenBaum);
		while (!lQueue.isEmpty()) {
			BinaryTree<Ahne> lBaum = lQueue.front();
			lQueue.dequeue();
			Ahne lAhne = (Ahne)lBaum.getContent();
			lString = lString + " " + lAhne.gibVorname();
			if (!lBaum.getLeftTree().isEmpty()) {
				lQueue.enqueue(lBaum.getLeftTree());
			}
			if (!lBaum.getRightTree().isEmpty()) {
				lQueue.enqueue(lBaum.getRightTree());
			}
		}
		return lString;
	}

	public static void main(String[] args) {
		new GUI(new Ahnenverwaltung());
	}



}
