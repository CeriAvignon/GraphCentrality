package fr.univavignon.graphcentr.g06;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

/**
 * @author Chris_Chevalier
 *
 */
public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DirectedGraph inGraph = new DirectedGraph();
		
		Node v1 = new Node();
		Node v2 = new Node();
		Node v3 = new Node();
		Node v4 = new Node();
		Node v5 = new Node();
		Node v6 = new Node();
		Node v7 = new Node();
		
		inGraph.addNode(v1);
		inGraph.addNode(v2);
		inGraph.addNode(v3);
		inGraph.addNode(v4);
		inGraph.addNode(v5);
		inGraph.addNode(v6);
		inGraph.addNode(v7);
		
		inGraph.createLink(v1, v2);
		inGraph.createLink(v1, v3);
		inGraph.createLink(v3, v4);
		inGraph.createLink(v4, v5);
		inGraph.createLink(v4, v6);
		inGraph.createLink(v4, v7);
		
		Betweeness B = new Betweeness();
		B.evaluate(inGraph);
	}
}
