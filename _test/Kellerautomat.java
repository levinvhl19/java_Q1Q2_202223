package _test;
import gui.GUI;
import linear.Stack;
import linear.StackWithViewer;

public class Kellerautomat{

    private StackWithViewer<Character> keller;

    public static void main(String[] args) {
        Kellerautomat ka = new Kellerautomat();
        new GUI(ka);
        
    }

    private boolean parse(String wort){
        keller = new StackWithViewer<>();
        keller.push('#');
        wort+='€';
        int zustand = 0;
        for(int i=0; i<wort.length();i++){
            char c = wort.charAt(i);
            char kellerChar = keller.top();
            if(zustand == 0){
                if(c=='a'){
                    keller.push('A');
                    continue;
                }
                else if(c=='b' && kellerChar=='A'){
                    keller.pop();
                    zustand = 1;
                    continue;
                }
                else{
                    return false;
                }
            }
            if(zustand == 1){
                if(c=='b' && kellerChar=='A'){
                    keller.pop();
                    continue;
                }
                else if(c=='€' && kellerChar=='#'){
                    
                    zustand = 2;
                    continue;
                }
                else{
                    return false;
                }
            }
            
        }
          return zustand==2;  
    }


}