package _wiederholungEF;

import gui.GUI;


public class Fahrkartenautomat {
    
    private double[] preisstufen = {1.20, 2.40, 3.60};
    private int preisstufe;
    private double eingeworfen;
    private double eingenommen;
    private String passwort;
    private FahrkartenDrucker vDrucker = new FahrkartenDrucker();
    
    public Fahrkartenautomat(){
        new GUI(this);
        eingeworfen = 0;
        eingenommen = 0;
        preisstufe = -1;
        passwort = "kaibel";
        
        System.out.println("Fahrkartenautomat ist bereit");
    }
    
    public void fahrschein_Drucken(){

        
        if(eingeworfen >= preisstufen[preisstufe]){
            System.out.println("Ihr Fahrschein der Preisstufe " + String.valueOf(preisstufe) + " wurde Gedruckt");
            if(eingeworfen - preisstufen[preisstufe] > 0){
                rueckgeld(eingeworfen - preisstufen[preisstufe]);
                
                eingeworfen = 0;
                eingenommen += preisstufen[preisstufe];
            	boolean result = vDrucker.drucken("HBF Koeln");
            	if (result == false) {
            		System.out.println("Fehler");
        			
        		}
            }
        }
        
        
    }
    
    public void zuruecksetzen(String pPasswort){
        if(pPasswort.equals(passwort)){
            
         System.out.println("Der eingenommene Betrag von: " + String.valueOf(eingenommen) + " wurde geleert.");
         eingenommen = 0;
        }
    }
    
    
    public void geldEinwerfen(double pBetrag) {
        
        eingeworfen += pBetrag;
        
        System.out.println("Eingeworfen: " + String.valueOf(pBetrag));
        
        
    }
    
    public void preisstufe_Waehlen(int pStufe) {
        preisstufe = pStufe-1;
    }
    
    public double rueckgeld(double pRueck){
        
        
        System.out.println("Zurückgegeben: " + String.valueOf(pRueck));
        return pRueck;
    }
    
        
    public double z_rueckgabe(){
        
        
        System.out.println("Zurückgegeben: " + String.valueOf(eingeworfen));
        return eingeworfen;
    }
    
    public static void main(String args[]) {
      
      Fahrkartenautomat F = new Fahrkartenautomat();

      
  }
}