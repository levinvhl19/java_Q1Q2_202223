package _test;


import com.ctc.wstx.io.SingleByteAttrValueWriter;

import gui.GUI;
import linear.Stack;
import linear.Stack;
import linear.StackWithViewer;
import sonstiges.Auto;
import sonstiges.Autos;
import sonstiges.Celebrities;
import sonstiges.Celebrity;

public class LevinStackTest {
	private Stack<Celebrity> celebritiesStack;
	private Stack<Celebrity> hStack = new Stack();
	private Stack<Celebrity> ergebnis = new Stack();
	
	//private Stack<Auto> autoStack;
	
	public LevinStackTest(){
		celebritiesStack = Celebrities.celebritiesStack();
		
		//autoStack = Autos.autoStack();
	}
	
	public void ausgeben() {
		System.out.println("*** ausgeben ***");
		//TODO
	}

	
	public int gesamtVermoegen() {
		int gesamt = 0;
		while(celebritiesStack.isEmpty() == false) {
			Celebrity topc = celebritiesStack.top();
			gesamt += topc.getVermoegen();
			celebritiesStack.pop();
		}
		return gesamt;
	}
	
	
	public int gesamtVermoegenNew() {
		int gesamt = 0;
		Stack<Celebrity> cSpeicher = new StackWithViewer<Celebrity>();
		while(celebritiesStack.isEmpty() == false) {
			Celebrity topc = celebritiesStack.top();
			gesamt += topc.getVermoegen();
			cSpeicher.push(topc);
			celebritiesStack.pop();
		}
		
		while(cSpeicher.isEmpty() == false) {
			Celebrity topc = cSpeicher.top();
			celebritiesStack.push(topc);
			cSpeicher.pop();
		}
		return gesamt;
	}
	
	public int hoechtestVermoegen() {
		int vermoegen = 0;
		while(celebritiesStack.isEmpty() == false) {
			Celebrity topc = celebritiesStack.top();
			if(topc.getVermoegen() > vermoegen) {
				vermoegen = topc.getVermoegen();
			}
		}
		return vermoegen;
	}
	
	public int vermoegenVon(String pName) {
		int vermoegen = 0;

		while(celebritiesStack.isEmpty() == false) {
			Celebrity topc = celebritiesStack.top();
			if(topc.getName().equals(pName)) {
				vermoegen = topc.getVermoegen();
			}
			celebritiesStack.pop();
		}
		return vermoegen;
	}
	
	
	
		
		
		
	public void sortReichsteDesc(){
		
		Stack<Celebrity> cSort = new Stack<Celebrity>();

		while(!celebritiesStack.isEmpty()){
			Celebrity max = getReichste();
			celebrityLoeschen(max);
			cSort.push(max);
		}
		while(!cSort.isEmpty()){
			celebritiesStack.push(cSort.top());
			cSort.pop();
		}
		
	}

	public Celebrity getReichste(){

		Stack<Celebrity> cSpeicher = new Stack<Celebrity>();
		Celebrity maxC = celebritiesStack.top();

		while(!celebritiesStack.isEmpty()){

			Celebrity topC = celebritiesStack.top();

			if(topC.getVermoegen() > maxC.getVermoegen()){
				maxC = topC;
			}

			cSpeicher.push(topC);
			celebritiesStack.pop();
		}
		while(!cSpeicher.isEmpty()){

			Celebrity topC = cSpeicher.top();

			celebritiesStack.push(topC);
			cSpeicher.pop();
		}

		return maxC;
	}

	public void celebrityLoeschen(Celebrity pCelebrity){

		Stack<Celebrity> cSpeicher = new Stack<Celebrity>();

		while(!celebritiesStack.isEmpty()){

			if(celebritiesStack.top() == pCelebrity){
				celebritiesStack.pop();
				if(celebritiesStack.isEmpty()){
					break;
				}
			}
			cSpeicher.push(celebritiesStack.top());
			celebritiesStack.pop();
		}
		while(!cSpeicher.isEmpty()){

			celebritiesStack.push(cSpeicher.top());
			cSpeicher.pop();
		}
		
	}
		


		
		
		


			
			
	
	public void hinzufuegen(String pName, String pVorname, int pVermoegen) {
		Celebrity c = new Celebrity(pName, pVorname, pVermoegen);
		celebritiesStack.push(c);
	}

	public static void main(String[] args) {
		LevinStackTest st = new LevinStackTest();
		new GUI(st);
	}
}
