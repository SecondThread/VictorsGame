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
			String x=(int)(position.x)+"", y=(int)(position.y)+"";
			int playerType=0;
			String soldierString="";
			int team=s.getTeam();
			String xVelocity=s.getVelocity().x+"";
			String yVelocity=s.getVelocity().y+"";
			if (xVelocity.length()>4) {
				xVelocity=xVelocity.substring(0, 4);
			}
			if (yVelocity.length()>4) {
				yVelocity=yVelocity.substring(0, 4);
			}
			
			if (s instanceof Runner) {
				playerType=1;
				soldierString=playerType+","+x+","+y+","+team+","+xVelocity+","+yVelocity+";";
			}
			else if (s instanceof Tank) {
				playerType=2;
				double shieldAngle=((Tank)s).getShieldAngle();
				soldierString=playerType+","+x+","+y+","+team+","+xVelocity+","+yVelocity+","+shieldAngle+";";
			}
			else if (s instanceof Sniper) {
				playerType=3;
				double gunAngle=((Sniper)s).getGunAngle();
				soldierString=playerType+","+x+","+y+","+team+","+xVelocity+","+yVelocity+","+gunAngle+";";
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
		Client.sendCommand("set soldierPositions "+soldierPositions);
		//System.out.println("\tcharacters sent: "+soldierPositions.length());
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
			int team=Integer.parseInt(arguements[3]);
			double xVelocity=Double.parseDouble(arguements[4]);
			double yVelocity=Double.parseDouble(arguements[5]);
			if (type.equals("1")) {
				toReturn.add(new ClientSoldier(x, y, team, xVelocity, yVelocity));
			}
			else if (type.equals("2")) {
				double shieldAngle=Double.parseDouble(arguements[6]);
				toReturn.add(new ClientTank(x, y, team, xVelocity, yVelocity, shieldAngle));
			}
			else if (type.equals("3")) {
				double gunAngle=Double.parseDouble(arguements[6]);
				toReturn.add(new ClientSniper(x, y, team, xVelocity, yVelocity, gunAngle));
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
		for (int i=0; i<10; i++) {
			Client.sendCommand("set "+i+"type -1");	
		}
	}
	
	public static void sendSoldierType(int player, int soldierType) {
		Client.sendCommand("set "+player+"type "+soldierType);
	}
	
	public static int getSoldierType(int player) {
		return Integer.parseInt(Client.sendCommand("get "+player+"type -1"));
	}
	
}
