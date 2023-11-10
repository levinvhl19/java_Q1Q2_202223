package _test.linear.buero;

import linear.Queue;
import linear.QueueWithViewer;

public class Buero {

	private Chef[] dieChefs;
	private Sachbearbeiter[] dieSachbearbeiter;
	private Queue<Auftrag> ablage;
	private Queue<Auftrag> dringendeAblage;
	private Queue<Auftrag> unwichtigeAblage;

	public Buero() {
		// die Chefs erzeugen
		dieChefs = new Chef[2];
		dieChefs[0] = new Chef("Sabine", this);
		dieChefs[1] = new Chef("Otto", this);
		
		ablage = new QueueWithViewer<>();
		dringendeAblage = new Queue<>();
		unwichtigeAblage = new Queue<>();
		
		
		// die Sachbearbeiter erzeugen
		dieSachbearbeiter = new Sachbearbeiter[3];
		dieSachbearbeiter[0] = new Sachbearbeiter("Georg", this);
		dieSachbearbeiter[1] = new Sachbearbeiter("Luise", this);
		dieSachbearbeiter[2] = new Sachbearbeiter("Christoph", this);
	}
	
	public void newAuftragAblegen(Auftrag pAuftrag) {
		if(pAuftrag.isDringlich()==true) {
			dringendeAblage.enqueue(pAuftrag);
		}
		else {
			unwichtigeAblage.enqueue(pAuftrag);
		}
		ablageUpdate();
	}
	
	public void ablageUpdate() {
		
	}
	
	public Auftrag auftragHolen() {
		
		if(!dringendeAblage.isEmpty()){
			dringendeAblage.front();
			
		}
		else {
			
		}
		ablageUpdate();
	}
	
	
	public void hintenAnhaengen() {
		Auftrag a = ablage.front();
		
				
	}
	
	
}
