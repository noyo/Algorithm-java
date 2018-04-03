
public class DoublyLink {
	
	private int value;
	private DoublyLink assistance;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public DoublyLink getAssistance() {
		return assistance;
	}
	public void setAssistance(DoublyLink assistance) {
		this.assistance = assistance;
	}
	
	public void setRear(DoublyLink pre, DoublyLink rear) {
		assistance = null;
	}
	
	public DoublyLink getPre() {
		
		return null;
	}
	
	public DoublyLink getRear() {
		return null;
	}
	
}
