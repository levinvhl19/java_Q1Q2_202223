package _test;
import linear.List;
import linear.ListWithViewer;
import gui.GUI;
import baeume.BinaryTree;
import baeume.TreeViewer;


public class BinaryTreeTest {
	public BinaryTree<Integer> suchbaum;
	public BinaryTree<String> rechenbaum;
	
	public BinaryTreeTest(){
        suchbaum = new BinaryTree<Integer>(59);
        rechenbaum = new BinaryTree<String>("+");
        BinaryTree<String> bGeteilt = new BinaryTree<>("/");
        BinaryTree<String> bMal = new BinaryTree<>("*");
        BinaryTree<String> b12zwei = new BinaryTree<>("12");
        BinaryTree<String> b4zwei = new BinaryTree<>("4");
        BinaryTree<String> b2 = new BinaryTree<>("2");
        BinaryTree<String> b3 = new BinaryTree<>("3");
        
        rechenbaum.setLeftTree(bGeteilt);
        rechenbaum.setRightTree(bMal);
        bGeteilt.setLeftTree(b12zwei);
        bGeteilt.setRightTree(b4zwei);
        bMal.setLeftTree(b2);
        bMal.setRightTree(b3);
        
        		
        		
        		
        		
        BinaryTree<Integer> b4= new BinaryTree<Integer>(4);
        BinaryTree<Integer> b34 = new BinaryTree<Integer>(34);
        BinaryTree<Integer> b16 = new BinaryTree<Integer>(16);
        BinaryTree<Integer> b7= new BinaryTree<Integer>(7);
        BinaryTree<Integer> b6 = new BinaryTree<Integer>(6);
        BinaryTree<Integer> b12 = new BinaryTree<Integer>(12);
        BinaryTree<Integer> b18= new BinaryTree<Integer>(18);
        BinaryTree<Integer> b17= new BinaryTree<Integer>(17);
        BinaryTree<Integer> b53 = new BinaryTree<Integer>(53);
        BinaryTree<Integer> b45 = new BinaryTree<Integer>(45);
        BinaryTree<Integer> b78 = new BinaryTree<Integer>(78);
        BinaryTree<Integer> b62 = new BinaryTree<Integer>(62);
        BinaryTree<Integer> b61= new BinaryTree<Integer>(61);
        BinaryTree<Integer> b71 = new BinaryTree<Integer>(71);
        BinaryTree<Integer> b73 = new BinaryTree<Integer>(73);
        
        suchbaum.setLeftTree(b4);
        b4.setRightTree(b34);
        b7.setLeftTree(b6);
        b7.setRightTree(b12);
        b16.setRightTree(b18);
        b18.setLeftTree(b17);
        b16.setLeftTree(b7);
        b34.setLeftTree(b16);
        b34.setRightTree(b53);
        b53.setLeftTree(b45);
        suchbaum.setRightTree(b78);
        b78.setLeftTree(b62);
        b62.setLeftTree(b61);
        b62.setRightTree(b71);
        b71.setRightTree(b73);
        TreeViewer.showTree(suchbaum, 600, 400);
	}
	
	public void loesche() {
		loesche(suchbaum, 34);
	}
	
	public void loesche(BinaryTree<Integer> pTree, int pZahl) {
		BinaryTree<Integer> b = suchbaum;
		while(!(b.getContent()==pZahl)) {
			if(pZahl>b.getContent()) {
				b = b.getRightTree();
			}
			if(pZahl<b.getContent()) {
				b = b.getLeftTree();
			}
		}
		BinaryTree<Integer> links = b.getLeftTree();
		kleinsterRechts(b).setLeftTree(links);
		
	}
	
	public BinaryTree<Integer> kleinsterRechts() {
		return kleinsterRechts(suchbaum);
	}
	
	public BinaryTree<Integer> kleinsterRechts(BinaryTree<Integer> pTree) {
		BinaryTree<Integer> right = pTree.getRightTree();

		while(!right.getLeftTree().isEmpty()) {
			
			right = right.getLeftTree();
		}
		return right;
	}
	
	public double rechne(BinaryTree<String> pTree) {
		double ergebnis = 0;
		List<String> ergebnisListe = new ListWithViewer<>();
		if(pTree.isEmpty()) {
			return ergebnis;
		}
		String wurzel = pTree.getContent();
		List<String> links = rechne(pTree.getLeftTree());
		List<String> right = rechne(pTree.getRightTree());
		

		ergebnisListe.concat(links);
		ergebnisListe.append(wurzel);
		ergebnisListe.concat(right);
		return ergebnis;
		
	}
	
	// Rahmenmethode
	public int groesstesElement() {
		return groesstesElement(suchbaum, 0);
	}
		
	
	public int groesstesElement(BinaryTree<Integer> pTree, int ergebnis) {
		if(pTree.isEmpty()) {
			return ergebnis;
		}
		if(pTree.getContent() > ergebnis) {
			ergebnis = pTree.getContent();
		}
		ergebnis = groesstesElement(pTree.getLeftTree(), ergebnis);
		ergebnis = groesstesElement(pTree.getRightTree(), ergebnis);
		return ergebnis;
	}
	
	public int anzahlKnoten() {
		return anzahlKnoten(suchbaum);
	}
	
	public int anzahlKnoten(BinaryTree<Integer> pTree) {
		int ergebnis = 0;
		if(pTree.isEmpty()) {
			return 0;
		}
		ergebnis++;
		ergebnis+=anzahlKnoten(pTree.getLeftTree());
		ergebnis+=anzahlKnoten(pTree.getRightTree());
		return ergebnis;
		
	}
	
	
	public int summe(){
		return summe(suchbaum);
	}
	private int summe(BinaryTree<Integer> pTree) {
		int ergebnis = 0;
		
		if(pTree.isEmpty()) {
			return 0;
		}
		// TODO programmieren:
		// Abbruchbedingung, Wurzelbehandlung, 2 rekursive Aufrufe, Sachlogik
		int wurzel = 0;
		wurzel = pTree.getContent();
		int pTreeLinks = summe(pTree.getLeftTree());
		int pTreeRechts = summe(pTree.getRightTree());
		ergebnis=wurzel+pTreeLinks+pTreeRechts;
		return ergebnis;
		
	}
	
	public List<Integer> levelorder(){
		List<Integer> ergebnis = new ListWithViewer<>();
		List<BinaryTree<Integer>> bL = new ListWithViewer<>();
		bL.append(suchbaum);
		for(bL.toFirst();bL.hasAccess();bL.next()) {
			BinaryTree<Integer> b = bL.getContent();
			if(!b.getLeftTree().isEmpty()) {
				bL.append(b.getLeftTree());
				
				
			}
			if(!b.getRightTree().isEmpty()) {
				bL.append(b.getRightTree());
				
			}
			
		}
		
		for(bL.toFirst();bL.hasAccess();bL.next()) {
			ergebnis.append(bL.getContent().getContent());
		}
		return ergebnis;
	}
	
	
	public void einfuegen(int pZahl) {
		BinaryTree<Integer> b = suchbaum;
		while(!b.isEmpty()) {
			if(pZahl>b.getContent()) {
				b = b.getRightTree();
			}
			if(pZahl<b.getContent()) {
				b = b.getLeftTree();
			}
		}
		b.setContent(pZahl);
	}
	
	
	public List<Integer> preorder(){
		return preorder(suchbaum);
	}
	public List<Integer> preorder(BinaryTree<Integer> pTree){
		List<Integer> ergebnis = new ListWithViewer<>();
		if(pTree.isEmpty()) {
			return ergebnis;
		}
		Integer wurzel = pTree.getContent();
		List<Integer> links = preorder(pTree.getLeftTree());
		List<Integer> right = preorder(pTree.getRightTree());
		
		ergebnis.append(wurzel);
		ergebnis.concat(links);
		ergebnis.concat(right);
		return ergebnis;
		
		
	}
		
		
	
	

	public static void main(String[] args) {
		new GUI(new BinaryTreeTest());
	}
}
