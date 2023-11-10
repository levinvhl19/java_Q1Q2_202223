package _test;
import linear.ListWithViewer;
import sonstiges.Person;
import datenbank.DatabaseConnector;
import datenbank.QueryResult;
import gui.GUI;


public class DatenbankTest {
	private DatabaseConnector connector;
	
	public DatenbankTest() {
		//                                    ip                       port database  user   password
		connector = new DatabaseConnector("localhost", 3306, "videodb", "root", "");
		String errorMessage = connector.getErrorMessage();
		if(errorMessage != null) System.err.println(errorMessage);
	}
	

	public ListWithViewer<String> zehnFilmeMit(String pDarsteller){
		ListWithViewer<String> ergebnis = new ListWithViewer<String>();
		String sqlStatement = 
		  " SELECT h.name AS hauptdarstellername, f.name AS filmname, f.oscars AS oscars "+
	      " FROM film f, film_has_hauptdarsteller fh, hauptdarsteller h "+
          " WHERE f.id = fh.film_id "+
	      " AND h.id = fh.hauptdarsteller_id "+
		  " AND h.name LIKE '%"+pDarsteller+"%' "+
	      " LIMIT 10 ";
		System.out.println(sqlStatement);

		connector.executeStatement(sqlStatement);

		String errorMessage = connector.getErrorMessage();
		if(errorMessage != null)
		{
			System.err.println(errorMessage);
			return null;
		}
		
		QueryResult queryResult = connector.getCurrentQueryResult();

		String[][] data = queryResult.getData();
		for (int i = 0; i < data.length; i++) {
			String hauptdarsteller = data[i][0];
			String filmName = data[i][1];
			String oscarsString = data[i][2];
			int oscars = Integer.parseInt(oscarsString);
			String zeile = hauptdarsteller+": "+filmName+" ("+oscars+" Oscars)";
			System.out.println(zeile);
			ergebnis.append(zeile);
		}
		return ergebnis;
	}
	
	public ListWithViewer<String> alleSchuelerDerKlasse(String pKlasse){
		ListWithViewer<String> ergebnis = new ListWithViewer<>();
		String statement = 
				" SELECT s.name as nachname, s.vorname as vorname"+
				" FROM schueler s, klasse k"+
				" WHERE k.id=s.klasse_id AND k.name ='"+pKlasse+"'";
		connector.executeStatement(statement);
		QueryResult q = connector.getCurrentQueryResult();
		String[][] data = q.getData();
		for(int i=0; i<data.length;i++) {
			ergebnis.append(data[i][0]+','+data[i][1]);
		}
		
		return ergebnis;
	}
	
	public ListWithViewer<Person> alleSchuelerDerKlasseAlsObjekte(String pKlasse){
		ListWithViewer<Person> ergebnis = new ListWithViewer<>();
		String statement = 
				" SELECT s.name as nachname, s.vorname as vorname"+
				" FROM schueler s, klasse k"+
				" WHERE k.id=s.klasse_id AND k.name ='"+pKlasse+"'";
		connector.executeStatement(statement);
		QueryResult q = connector.getCurrentQueryResult();
		String[][] data = q.getData();
		for(int i=0; i<data.length;i++) {
			Person p = new Person(data[i][0],data[i][1]);
		}
		
		return ergebnis;
	}
	
	public double anteilAmGesamtUnterricht(String pFach) {
		double ergebnis = 0;
		String pStatement = "select pF.stnd/aF.stnd*100 from(SELECT SUM(u.stunden) as stnd FROM unterricht u WHERE u.fach = '"+pFach+"') as pF join(SELECT SUM(u.stunden) as stnd FROM unterricht u) as aF";
		connector.executeStatement(pStatement);
		QueryResult q = connector.getCurrentQueryResult();
		String[][] data = q.getData();
		return Double.parseDouble(data[0][0]);
	}
	
	public ListWithViewer<String> anzahlDerStunden(String pFach){
		ListWithViewer<String> ergebnis = new ListWithViewer<>();
		String statement = 
				" SELECT SUM(u.stunden)"+
				" FROM unterricht u"+
				" WHERE u.fach ='"+pFach+"' GROUP BY u.fach";
		connector.executeStatement(statement);
		QueryResult q = connector.getCurrentQueryResult();
		String[][] data = q.getData();
		for(int i=0; i<data.length;i++) {
			ergebnis.append(data[i][0]);
		}
		
		return ergebnis;
	}
	
	public static void main(String[] args) {
		new GUI(new DatenbankTest());
	}
}
