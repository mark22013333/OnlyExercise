package cheng.exercise07;

public class Play {

	public static void main(String[] args) {

		int max = 5;
		for (int i = 1; i <= max; i++) {
			for (int j = max; j > i; j--) {
				System.out.print(" ");
			}

			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
