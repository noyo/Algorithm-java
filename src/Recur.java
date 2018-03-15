
public class Recur {

	private static Recur recur = new Recur();
	private final static char A = 'A';
	private final static char B = 'B';
	private final static char C = 'C';
	
	private void hanluota(char a, char b, char c, int n) {
		if (n == 1) {
			System.out.println(a + "->" + c);
		} else {
			hanluota(a, c, b, n - 1);
			System.out.println(a + "->" + c);
			hanluota(b, a, c, n - 1);
		}
	}
	
	public static void main(String args[]) {
		recur.hanluota(A, B, C, 5);
	}
}
