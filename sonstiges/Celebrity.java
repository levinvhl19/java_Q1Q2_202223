package sonstiges;

import baeume.ComparableContent;

public class Celebrity extends Person implements ComparableContent<Celebrity>{
	private int vermoegen;
	
	public Celebrity(String pName, String pVorname, int pVermoegen){
		super(pName, pVorname);
		this.vermoegen = pVermoegen;
	}

	
	public int getVermoegen() {
		return vermoegen;
	}

	public void setVermoegen(int pVermoegen) {
		this.vermoegen = pVermoegen;
	}
	
	public String toString(){
		return (super.toString()+", "+this.vermoegen);
	}

	public boolean istAlphabetischNach(Celebrity pPerson) {
		return this.getName().compareTo(pPerson.getName()) > 0;
	}


	@Override
	public boolean isGreater(Celebrity pContent) {
		return (this.getVermoegen()>((Celebrity) pContent).getVermoegen());
	}


	@Override
	public boolean isEqual(Celebrity pContent) {
		return (this.getVermoegen()==((Celebrity) pContent).getVermoegen());
		
	}


	@Override
	public boolean isLess(Celebrity pContent) {
		return (this.getVermoegen()<((Celebrity) pContent).getVermoegen());
	}


}
