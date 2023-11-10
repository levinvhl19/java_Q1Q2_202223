package _test.linear.buero;

public class Auftrag {
	private String chefname;
	private String text;
	private boolean dringlich;
	public Auftrag(String chefname, String text, boolean dringlich) {
		super();
		this.chefname = chefname;
		this.text = text;
		this.dringlich = dringlich;
	}
	public String getChefname() {
		return chefname;
	}
	public String getText() {
		return text;
	}
	public boolean isDringlich() {
		return dringlich;
	}
	@Override
	public String toString() {
		return text;
	}
	

}
