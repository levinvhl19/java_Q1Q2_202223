package _test;
import javax.swing.JOptionPane;

import linear.Ring;

public class RingTest {
	public static void ausgeben(Ring<String> r){
		JOptionPane.showMessageDialog(null, "message");
		if(r.isEmpty() == true){
			System.out.println("--leer--");
			return;
		}
		Object start = r.getContent();
		System.out.print(start+"--");
		r.next();
		Object next = r.getContent();
		while(next != start){
			System.out.print(next+"--");
			r.next();
			next = r.getContent();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Ring<String> r = new Ring<String>();
//		r.insert("A");
//		r.insert("B");
//		r.insert("C");
//		ausgeben(r);
//		r.next();
//		ausgeben(r);
//		r.next();
//		ausgeben(r);
//		r.next();
		System.out.println("*** leerer Ring: ***");
		ausgeben(r);
		
		r.insert("A");
		System.out.println("*** ein Element ***");
		ausgeben(r);
		
		r.insert("B");
		r.insert("C");
		System.out.println("*** drei Elemente ***");
		ausgeben(r);
		System.out.println("*** Das aktuelle Element sollte C sein! ***");
		System.out.println(r.getContent());
		
		// zwei Elemente wegnehmen: C und A
		r.remove();
		r.remove();
		System.out.println("*** nur noch B sollte uebrig sein: ***");
		ausgeben(r);
		
		r.insert("D");
		System.out.println("*** B und D sollten enthalten sein ***");
		ausgeben(r);
		
		r.remove();
		r.remove();
		System.out.println("*** Ring ist wieder leer *** ");
		ausgeben(r);
		
	}
}
