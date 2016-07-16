package engine.networking;

import java.util.ArrayList;

import engine.entities.Bullet;
import engine.entities.Point;
import engine.entities.Runner;
import engine.entities.Sniper;
import engine.entities.Soldier;
import engine.entities.Tank;

public class NetworkManager {
	
	public static void initAsClient() {
		Client.clientInit();
	}
	
	public static void sendSoldierPositions(ArrayList<Soldier> soldiers, ArrayList<Bullet> bullets) {
		String soldierPositions="";
		for (Soldier s:soldiers) {
			Point position=s.getPosition();
			int playerType=0;
			String soldierString="";
			int redValue=s.getColor().getRed();
			int greenValue=s.getColor().getGreen();
			int blueValue=s.getColor().getBlue();
			
			if (s instanceof Runner) {
				playerType=1;
				soldierString=playerType+","+position.x+","+position.y+","+redValue+","+greenValue+","+blueValue+";";
			}
			else if (s instanceof Tank) {
				playerType=2;
				double shieldAngle=((Tank)s).getShieldAngle();
				soldierString=playerType+","+position.x+","+position.y+","+redValue+","+greenValue+","+blueValue+","+shieldAngle+";";
			}
			else if (s instanceof Sniper) {
				playerType=3;
				double gunAngle=((Sniper)s).getGunAngle();
				soldierString=playerType+","+position.x+","+position.y+","+redValue+","+greenValue+","+blueValue+","+gunAngle+";";
			}
			soldierPositions+=soldierString;
		}
		if (!soldiers.isEmpty()) 
			soldierPositions=soldierPositions.substring(0, soldierPositions.length()-1);//take off the last ';'
		soldierPositions+="|";
		
		String totalBulletString="";
		for (Bullet b:bullets) {
			Point position=b.getPosition();
			double direction=b.getAngle();
			String bulletString=position.x+","+position.y+","+direction+";";
			totalBulletString+=bulletString;
		}
		if (!bullets.isEmpty()) 
			totalBulletString=totalBulletString.substring(0, totalBulletString.length()-1);//take off the last ;
		
		soldierPositions+=totalBulletString;
		//System.out.println(soldierPositions);
		Client.sendCommand("set soldierPositions "+soldierPositions);
	}

	public static String getData() {
		return Client.sendCommand("get soldierPositions |");
	}
	
	public static ArrayList<ClientSoldier> getSoldiers(String data) {
		String soldierPositions=data.split("\\|")[0];
		ArrayList<ClientSoldier> toReturn=new ArrayList<ClientSoldier>();
		if (!soldierPositions.contains(",")) {
			return toReturn;
		}
		String[] soldierStringArray=soldierPositions.split(";");
		for (String soldierString:soldierStringArray) {
			String[] arguements=soldierString.split(",");
			String type=arguements[0];
			double x=Double.parseDouble(arguements[1]);
			double y=Double.parseDouble(arguements[2]);
			int r=Integer.parseInt(arguements[3]);
			int g=Integer.parseInt(arguements[4]);
			int b=Integer.parseInt(arguements[5]);
			if (type.equals("1")) {
				toReturn.add(new ClientSoldier(x, y, r, g, b));
			}
			else if (type.equals("2")) {
				double shieldAngle=Double.parseDouble(arguements[6]);
				toReturn.add(new ClientTank(x, y, r, g, b, shieldAngle));
			}
			else if (type.equals("3")) {
				double gunAngle=Double.parseDouble(arguements[6]);
				toReturn.add(new ClientSniper(x, y, r, g, b, gunAngle));
			}
		}
		return toReturn;
	}
	
	public static ArrayList<ClientBullet> getBullets(String data) {
		ArrayList<ClientBullet> toReturn=new ArrayList<ClientBullet>();
		if (data.split("\\|").length<2) {
			return toReturn;
		}
		String bulletPositions=data.split("\\|")[1];			
		if (!bulletPositions.contains(",")) {
			return toReturn;
		}
		String[] bulletStringArray=bulletPositions.split(";");
		for (String bulletString:bulletStringArray) {
			String[] arguements=bulletString.split(",");
			String type=arguements[0];
			double x=Double.parseDouble(arguements[0]);
			double y=Double.parseDouble(arguements[1]);
			double direction=Double.parseDouble(arguements[2]);
			toReturn.add(new ClientBullet(x, y));
		}
		return toReturn;
	}
	
	public static void sendKeyboardInputs(String inputs) {
		int player=Client.getPlayerID();
		Client.sendCommand("set "+player+" "+inputs);
	}
	
	public static String getKeyboardInputs(int player) {
		return Client.sendCommand("get "+player+" .");
	}
	
	public static void resetPlayers() {
		Client.sendCommand("resetPlayers");
	}
	
}
