package engine.encoding;

public class HuffmanNode implements Comparable<HuffmanNode> {
	public HuffmanNode left=null, right=null;
	public char character;
	private int value=0;
	
	public HuffmanNode(char character, int value) {
		left=null;
		right=null;
		this.character=character;
		this.value=value;
	}
	
	public HuffmanNode(HuffmanNode left, HuffmanNode right) {
		this.left=left;
		this.right=right;
	}
	
	public int getValue() {
		if (left==null) {
			return value;
		}
		return left.getValue()+right.getValue();
	}


	public int compareTo(HuffmanNode other) {
		return getValue()-other.getValue();
	}

	public String getCodeForChar(char toCheck) {
		if (left==null) {
			return "";
		}
		if (left.containsChar(toCheck)) {
			return "0"+left.getCodeForChar(toCheck);
		}
		else {
			return "1"+right.getCodeForChar(toCheck);
		}
	}
	
	private boolean containsChar(char toCheck) {
		if (left==null) {
			return character==toCheck;
		}
		return left.containsChar(toCheck)||right.containsChar(toCheck);
	}
	
	public void printTree(HuffmanNode head) {
		if (left==null) {
			System.out.println(character+": "+head.getCodeForChar(character));
			return;
		}
		left.printTree(head);
		right.printTree(head);
	}
	
	public String toString() {
		return "<"+character+" : "+getValue()+">";
	}
	
}
