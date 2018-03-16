
public class LooseLink {

	private int value;
	private LooseLink rear;
	private LooseLink headRear = null;
	
	public LooseLink() {}
	
	public LooseLink(int value, LooseLink rear) {
		
		this.value = value;
		this.rear = rear;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LooseLink getRear() {
		return rear;
	}

	public void setRear(LooseLink rear) {
		this.rear = rear;
	}

	public LooseLink getHeadRear() {
		return headRear;
	}

	public void setHeadRear(LooseLink headRear) {
		this.headRear = headRear;
	}
	
	public boolean isTail() {
		return headRear != null;
	}
}
