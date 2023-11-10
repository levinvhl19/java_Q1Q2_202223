package _test;

import gui.GUI;
import linear.List;
import linear.ListWithViewer;
import sonstiges.Autos;
import sonstiges.Celebrities;
import sonstiges.Celebrity;
import sonstiges.Person;
import sonstiges.Auto;

public class ListTest {
	private List<Celebrity> celebritiesList;
	//private List<Auto> autoList;
	
	public ListTest(){
		celebritiesList = Celebrities.celebritiesList();
		//autoList = Autos.autoList(); 
	}
	
	public void ausgeben() {
		System.out.println("*** ausgeben ***");
		//TODO
		celebritiesList.toFirst();
		while(celebritiesList.hasAccess()) {
			Celebrity c = celebritiesList.getContent();
			System.out.println(c);
			celebritiesList.next();
		}
	}
	
	
	public Celebrity dieReichste() {
		celebritiesList.toFirst();
		int max = 0;
		Celebrity dieReichste = null;
		while(celebritiesList.hasAccess()) {
			if(celebritiesList.getContent().getVermoegen() > max) {
				dieReichste = celebritiesList.getContent();
				max = celebritiesList.getContent().getVermoegen();
				dieReichste = celebritiesList.getContent();
			}
			celebritiesList.next();
		}
		
		return dieReichste;
		
	}
	
	
	public void loeschen(Celebrity pCelebrity) {
		celebritiesList.toFirst();
		while(celebritiesList.hasAccess()) {
			if(celebritiesList.getContent() == pCelebrity) {
				celebritiesList.remove();
			}
			celebritiesList.next();
		}

	}
	
	
	public void sortierenNachVermoegen() {
		List<Celebrity> zSpeicher = new ListWithViewer<>();
		celebritiesList.toFirst();
		while(celebritiesList.hasAccess()) {
			zSpeicher.append(dieReichste());
			loeschen(dieReichste());
			celebritiesList.toFirst();
			
		}
		
		while(zSpeicher.isEmpty() == false) {
			zSpeicher.toFirst();
			celebritiesList.append(zSpeicher.getContent());
			zSpeicher.remove();
		}
	}
	
	
	
	
	
	
	public void insertionsort() {
		celebritiesList.toFirst();
		List<Celebrity> h = new ListWithViewer<>();
		h.append(celebritiesList.getContent());
		celebritiesList.remove();
		while(celebritiesList.hasAccess()) {
			celebritiesList.toFirst();
			h.toFirst();
			if(celebritiesList.getContent().getVermoegen() > h.getContent().getVermoegen()) {
				h.next();
				h.insert(celebritiesList.getContent());
			}
			else {
				h.insert(celebritiesList.getContent());
			}
			celebritiesList.remove();
		}
			
	}


	
	
	
	public static void main(String[] args) {
		ListTest lt = new ListTest();
		new GUI(lt);
	}
}
