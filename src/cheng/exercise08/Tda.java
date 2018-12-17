package cheng.exercise08;

class Account1 {
	int balance = 0;

	synchronized public void transfer(int money) {
		while (balance > 2000) {
			System.out.printf("媽媽看帳戶超過%d,暫停匯款\n", balance);
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		balance += money;
		System.out.printf("媽媽存了%d,帳戶共有:%d\n", money, balance);
		notify();
	}

	synchronized public void withdraw(int money) {
		while (balance < money) {
			System.out.println("熊大看到帳戶裡沒錢,暫停領錢");
			try {
				wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (balance < 1000) {
				System.out.println("熊大看到餘額在1000以下,要求匯款");
				notify();
			}

		}

		balance -= money;
		System.out.printf("熊大領了%d,於:%d\n", money, balance);

	}
}

class Mom extends Thread {
	Account1 money;

	public Mom(Account1 money) {
		this.money = money;
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			money.transfer(2000);
		}
	}
}

class Son extends Thread {
	Account1 money;

	public Son(Account1 money) {
		this.money = money;
	}

	public void run() {
		for (int i = 1; i <= 10; i++) {
			money.withdraw(1000);
		}
	}
}

public class Tda {
	public static void main(String[] args) {
		Account1 Account1 = new Account1();
		Mom mom = new Mom(Account1);
		Son son = new Son(Account1);

		mom.start();
		son.start();
	}

}