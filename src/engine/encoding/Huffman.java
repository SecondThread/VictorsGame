package engine.encoding;

import java.util.ArrayList;
import java.util.Collections;

public class Huffman {

	public static String encode(String message) {
		ArrayList<HuffmanNode> nodes=getBaseNodes(message);
		HuffmanNode root=buildTree(nodes);
		String toReturnString=encodeMessage(message, root);
		System.out.println(toReturnString);
		byte[] toReturnBytes=getAsBytes(toReturnString);
		for (byte b:toReturnBytes) {
			System.out.print(b);
		}
		System.out.println();
		return new String(toReturnBytes);
	}

	private static ArrayList<HuffmanNode> getBaseNodes(String message) {
		ArrayList<Character> characters=new ArrayList<Character>();
		ArrayList<Integer> counts=new ArrayList<Integer>();
		for (int i=0; i<message.length(); i++) {
			char c=message.charAt(i);
			if (characters.contains(c)) {
				int index=characters.indexOf(c);
				counts.set(index, counts.get(index)+1);
			}
			else {
				characters.add(c);
				counts.add(1);
			}
		}

		ArrayList<HuffmanNode> toReturn=new ArrayList<>();
		for (int i=0; i<characters.size(); i++) {
			toReturn.add(new HuffmanNode(characters.get(i), counts.get(i)));
		}
		return toReturn;
	}

	private static HuffmanNode buildTree(ArrayList<HuffmanNode> nodes) {
		while (nodes.size()>1) {
			Collections.sort(nodes);
			nodes.add(new HuffmanNode(nodes.get(0), nodes.get(1)));
			nodes.remove(0);
			nodes.remove(0);
		}
		return nodes.get(0);
	}

	private static String encodeMessage(String message, HuffmanNode tree) {
		String toReturn="";
		for (int messageIndex=0; messageIndex<message.length(); messageIndex++) {
			char toAdd=message.charAt(messageIndex);
			toReturn+=tree.getCodeForChar(toAdd);
		}
		return toReturn;
	}

	private static byte[] getAsBytes(String message) {
		byte[] toReturn=new byte[((message.length()-1)/8+1)];
		for (int i=0; i<toReturn.length; i++) {
			int endIndex=Math.min(message.length(), 8*i+8);
			toReturn[i]=getAsByte(message.substring(8*i, endIndex));
		}
		return toReturn;
	}

	private static byte getAsByte(String shortMessage) {
		if (shortMessage.length()<8) {
			return getAsByte(shortMessage+"0");
		}
		byte total=0;
		for (int i=0; i<8; i++) {
			boolean is1=shortMessage.charAt(shortMessage.length()-1-i)=='1';
			if (is1) {
				total+=Math.pow(2, i);
			}
		}
		return total;
	}
	
	private static String getFromBytes(byte[] asBytes) {
		String toReturn="";
		for (int byteIndex=0; byteIndex<asBytes.length; byteIndex++) {
			byte current=asBytes[byteIndex];
			for (int i=0; i<8; i++) {
				if (isBitSet(current, i)) {
					toReturn+="1";
				}
				else {
					toReturn+="0";
				}
			}
		}
		return toReturn;
	}
	
	private static Boolean isBitSet(byte b, int bit) {
	    return (b & (1 << bit)) != 0;
	}
	
	private static String decode(String finalString) {
		byte[] asBytes=finalString.getBytes();
		String asBits=getFromBytes(asBytes);
		System.out.println(asBits);
		return "";
	}

	public static void main(String[] args) {
		String toDecode="a man a plan a canal panama";
		String done=encode(toDecode);
		System.out.println(done);
		for (byte b:done.getBytes()) {
			System.out.print(b);
		}
		System.out.println();
		
		decode(done);
		
	}
}