import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class EchoClient {
	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 22222)) {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader br = new BufferedReader(isr);
			BufferedReader bcr = new BufferedReader(new InputStreamReader(System.in));
			//System.out.println(bcr.readLine());
			String msg;
			System.out.print("Client> ");
			while((msg = bcr.readLine()) != null){
				out.println(msg);
				if(msg.equals("exit")){
					break;
				}
				System.out.println("Server> " + br.readLine());
				System.out.print("Client> ");			
			}
			out.close();
			br.close();
			bcr.close();
			socket.close();
			
		}
	}
}














