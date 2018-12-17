package cheng.exercise08;

public class Test0808 {

	public class ClassA {
		private int a;
		protected int b;
		int c;
		public int d;
	}

	public class ClassB extends ClassA {
		public void methodA() {
			System.out.println(this.b);
		}
	}

	public static int in(int a) {
		return a++;
	}

	public static void main(String[] args) {

		int a = 5;
		int b = Test0808.in(a);
		
		System.out.println(b);

		try {

		} catch (Exception e) {
			// TODO: handle exception
		}

		finally {
			// TODO: handle finally clause
		}
		// for (int i = 0; i <= 4; i++) {
		// for (int j = 0; j <= 4 - i; j++) {
		// System.out.print("0");
		// }
		// for (int j = -1; j <= (2 * i - 1); j++) {
		// System.out.print("*");
		// }
		// System.out.println();
		// }

		// int a = 0, b = 2;
		// int x = 0, y = 0, z = 0;
		// x = a++;
		// y = --b;
		// z = ++a;
		// System.out.println(a);
		// System.out.println(x);
		// System.out.println(y);
		// System.out.println(z);
		//

	}
}
