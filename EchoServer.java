import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {
	public static void main(String[] args) throws Exception {
		try (ServerSocket serverSocket = new ServerSocket(22222)) {
			while (true) {
				Socket socket = serverSocket.accept();
					ServerThread st = new ServerThread(socket);
					st.start();
					/*
					 * String address =
					 * socket.getInetAddress().getHostAddress();
					 * System.out.printf("Client connected: %s%n", address);
					 * OutputStream os = socket.getOutputStream(); PrintStream
					 * out = new PrintStream(os, true, "UTF-8"); BufferedReader
					 * br = new BufferedReader(new
					 * InputStreamReader(socket.getInputStream()));
					 * //out.printf("Hi %s, thanks for connecting!%n", address);
					 * String msg; while((msg = br.readLine()) != null){
					 * out.println(msg); if(msg.equals("exit")){
					 * System.out.printf("Client disconnected: %s%n", address);
					 * } }
					 */
				}
			}
		}
	}


class ServerThread extends Thread {
	Socket socket = null;
	BufferedReader br = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		String address = socket.getInetAddress().getHostAddress();
		System.out.printf("Client connected: %s%n", address);

			OutputStream os;
			try {
				os = socket.getOutputStream();
				PrintStream out = new PrintStream(os, true, "UTF-8");
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String msg;
				try {
					while ((msg = br.readLine()) != null) {
						out.println(msg);
						if (msg.equals("exit")) {
							System.out.printf("Client disconnected: %s%n", address);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("Messaging error");
				
			}
		// out.printf("Hi %s, thanks for connecting!%n", address);
		} catch(IOException e1){
			e1.printStackTrace();
		}		
	}
}