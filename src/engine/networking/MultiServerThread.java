package engine.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class MultiServerThread extends Thread {
	private Socket socket=null;
	private int playerID=0;

	public MultiServerThread(Socket socket, int playerID) {
		super("MultiServer Thread");
		this.socket=socket;
		this.playerID=playerID;
	}

	public void run() {

		try {
			PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String inputLine;

			out.println(playerID+"");

			while ((inputLine=in.readLine())!=null) {
				String[] arguements=inputLine.split(" ");
				String command=arguements[0];
				if (command.equalsIgnoreCase("leave")) {
					out.println("ACK, bye");
					break;
				}
				if (command.equalsIgnoreCase("set")) {
					String name=arguements[1], value=arguements[2];
					MultiServer.map.put(name, value);
					out.println("ACK, set");
					continue;
				}

				if (command.equalsIgnoreCase("get")) {
					String name=arguements[1];
					if (MultiServer.map.containsKey(name)) {
						out.println(MultiServer.map.get(name));
						continue;
					}
					if (arguements.length>2) {
						out.println(arguements[2]);
					}
					else {
						out.println("NAC: Key Not Found");
					}
					continue;
				}
				
				if (command.equalsIgnoreCase("resetPlayers")) {
					MultiServer.playerID=0;
					out.println("ACK, reset");
					continue;
				}
				
				out.println("NAC");

			}
			out.close();
			in.close();
			socket.close();

		} catch (

		IOException e) {
			e.printStackTrace();
		}
	}
}