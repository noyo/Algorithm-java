
public class Test {
	
	static Test instance = new Test();
	
	public void looseLinkTest() {
		
		LooseLink headLink = null;
		
		CREATE: {
			LooseLink head = null;
			LooseLink tail = null;
			LooseLink rear = null;
			for (int i = 2; i > 0; i--) {
				for (int j = 5; j > 0; j--) {
					LooseLink link = new LooseLink(i * j, rear);
					rear = link;
					if (j == 5) tail = link;
					if (j == 1) head = link;
				}
				tail.setHeadRear(head);
			}
			headLink = head;
		}
	
		PRINT:{
			while (true) {
				System.out.print(headLink.getValue());
				if (headLink.isTail()) {
					System.out.print(":" + headLink.getHeadRear().getValue());
				}
				headLink = headLink.getRear();
				if (headLink == null) break;
				System.out.print("->");
			}
		}
		
	}
	
	public static void main(String args[]) {
		
		instance.looseLinkTest();
		
	}
}
