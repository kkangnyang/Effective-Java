package effective.java.item6.flyweightpattern;

import java.util.HashMap;

public class TreeFactory {
	
	public static final HashMap<String, Tree> treeMap = new HashMap<String, Tree>();
	
	public static Tree getTree(String treeColor) {
		Tree tree = treeMap.get(treeColor);
		
		if (tree == null) {
			tree = new Tree(treeColor);
			treeMap.put(treeColor, tree);
			System.out.println("货 按眉 积己!");
		}
		
		return tree;
	}
	
}


