package cheng.exercise08;

class Account {
	private int balance = 0;

	synchronized public void withdraw(int money) {
		while (balance < money) {
			System.out.println("熊大看到帳戶沒錢，暫停提款");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		balance -= money;
		System.out.println("熊大領了" + money + "，帳戶共有:" + balance);
		if (balance < 1000) {
			System.out.println("熊大看到餘額在1000以下，要求匯款");
			notify();
			System.out.println("媽媽被熊大要求匯款");
		}
	}

	synchronized public void deposit(int money) {
		while (balance > 2000) {
			try {
				System.out.println("媽媽看到餘額在2000以上，暫停匯款");
				notify();
				System.out.println("熊大被老媽告知帳戶已經有錢!");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		balance += money;
		System.out.println("媽媽存了" + money + "，帳戶共有：" + balance);
	}
}

class Bear extends Thread {
	Account account;

	public Bear(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("雄大" + i);
			account.withdraw(1000);
		}
		if(BearMom.currentThread().isAlive())
		this.notify();
	}
}

class BearMom extends Thread {
	Account account;

	public BearMom(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("熊媽媽" + i);
			account.deposit(2000);
		}
		if(Bear.currentThread().isAlive())
		this.notify();
	}
}

public class AccountTest {

	public static void main(String[] args) {
		Account account = new Account();
		BearMom bearMom = new BearMom(account);
		Bear bear = new Bear(account);

		bearMom.start();
		bear.start();
	}
}
