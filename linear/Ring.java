package linear;

public class Ring<ContentType>{

	private Node<ContentType> preCurrent;

	public Ring(){
		
	}

	public boolean isEmpty() {
		if(preCurrent == null) {
			return true;
		}
		return false;
	}

	public void insert(ContentType pContent) {
		
		Node<ContentType> n = new Node<ContentType>(pContent);
		if(isEmpty()) {
			preCurrent = n;
			preCurrent.setNext(n);
		}
		else {
			Node<ContentType> current = preCurrent.getNext();
			preCurrent = n;
			preCurrent.setNext(current);
//			Node<ContentType> preCurrentSave = preCurrent;
//			preCurrent = n;
//			preCurrent.setNext(preCurrentSave);
			
		}
	}
	
	public void remove() {
		Node<ContentType> current = preCurrent.getNext();
		if(isEmpty()) {
			return;
		}
		if(preCurrent == preCurrent.getNext()) {
			preCurrent = null;
			return;
		}
		pppppp
		preCurrent.setNext(current.getNext());
	}
	
	public ContentType getContent() {
		return preCurrent.getNext().getValue();
	}



	public void next(){
		if((preCurrent == preCurrent.getNext()) || isEmpty()) {
			return;
		}
		Node<ContentType> current = preCurrent.getNext();
		preCurrent = current;
	}

}