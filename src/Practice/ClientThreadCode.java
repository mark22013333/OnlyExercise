package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientThreadCode extends Thread {

	private Socket socket;// 和伺服器端進行連線

	public ClientThreadCode(String ip, int port) {
		try {
			socket = new Socket(ip, port);// 建立連線。(ip和port為伺服器端的開啟的ip和port)
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		if (socket != null) {
			// 連線成功才繼續往下執行
			try {
				//由於Server端使用PrintStream和BufferedReader來接收和寄送訊息，所以Client端也要相同
				PrintStream writer = new PrintStream(socket.getOutputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				writer.println("現在時間是？");
				writer.flush();
				System.out.println("Server:"+reader.readLine());
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		}
	}

}
