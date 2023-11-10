package linear;

public class CountingQueue<ContentType> extends QueueWithViewer<ContentType> {
	private int count;
	
	public CountingQueue() {
		// TODO Auto-generated constructor stub
		super();
		count = 0;
	}
	
	public void enqueue(ContentType pContent) {
		super.enqueue(pContent);
		count++;
	}
	
	public void dequeue() {
		if(count == 0) {
			return;
		}
		super.dequeue();
		count--;
	}
	
	public int getCount() {
		return count;
	}
}
