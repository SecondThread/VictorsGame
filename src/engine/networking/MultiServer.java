package engine.networking;
import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;

public class MultiServer {
	
	public static volatile ConcurrentHashMap<String, String> map=new ConcurrentHashMap<String, String>();
	
	public static volatile int playerID=0;
	
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket=null;
		boolean listening=true;
		short port=7555;

		try {
			serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Could not listen on port: "+port+"...");
			System.exit(-1);
		}

		while (listening) {
			new MultiServerThread(serverSocket.accept(), playerID).start();
			playerID++;
		}

		serverSocket.close();
	}
}