package engine.networking;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
	
	private static Socket kkSocket=null;
	private static PrintWriter out=null;
	private static BufferedReader in=null;
	private static int playerID;
	public static String serverIP="192.168.1.102";
	
	public static void clientInit() {
		try {
			kkSocket=new Socket(serverIP, 5560);
			out=new PrintWriter(kkSocket.getOutputStream(), true);
			in=new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
			playerID=Integer.parseInt(getIDFromServer());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: localhost.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection.");
			System.exit(1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scanner s=new Scanner(System.in);
		String fromServer;

		while (true) {
			String input=s.nextLine();
			if (input.equals("break")) {
				break;
			}
			out.println(input);
			System.out.println(in.readLine());
		}
		
		System.out.println("Closing everything...");
		out.close();
		in.close();
		kkSocket.close();
		
	}
	
	private static String getIDFromServer() {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ERROR when getting playerID";
	}
	
	public static String sendCommand(String command) {
		out.println(command);
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "ERROR";
	}
	
	public static int getPlayerID() {
		return playerID;
	}
	
}