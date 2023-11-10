package _test;


import gui.GUI;

import linear.CountingQueue;
import sonstiges.Celebrities;
import sonstiges.Celebrity;

public class QueueTest {
	private CountingQueue<Celebrity> celebritiesQueue;
	
	//private Queue<Auto> autoQueue;
	
	public QueueTest(){
		celebritiesQueue = (CountingQueue<Celebrity>) Celebrities.celebritiesCountingQueue();
		
		//autoQueue = Autos.autoQueue();
	}
	
	public int ausgeben() {
		System.out.println("*** ausgeben: anzahl ***");
		System.out.println(celebritiesQueue.getCount());
		return celebritiesQueue.getCount();
		//TODO
	}
	
	public void dequeue() {
		celebritiesQueue.dequeue();
	}
	
	public int anzahl() {
		return celebritiesQueue.getCount();
	}

	public static void main(String[] args) {
		QueueTest qt = new QueueTest();
		new GUI(qt);
	}
}
