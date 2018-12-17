package Practice;

public class ClientCode {
	
	public static void main(String[] args) {
		//建立物件，傳入IP和Port並執行等待接受連線的動作
		new ClientThreadCode("127.0.0.1", 8888).start();
	}
}
