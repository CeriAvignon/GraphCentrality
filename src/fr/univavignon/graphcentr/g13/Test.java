package fr.univavignon.graphcentr.g13;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

public class Test {

	public static void main(String[] args) {
		SimpleGraph G = new SimpleGraph();
		Node v1 = G.createNode();
		Node v2 = G.createNode();
		Node v3 = G.createNode();
		Node v4 = G.createNode();
		G.createLink(v1, v1);
		G.createLink(v2, v2);
		G.createLink(v3, v3);
		G.createLink(v4, v4);
		G.createLink(v1, v2);
		G.createLink(v1, v3);
		G.createLink(v2, v3);
		G.createLink(v3, v4);
		
		double[][] a = G.toAdjacencyMatrix();
		
		for (int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}

		EntropyCentrality e = new EntropyCentrality();
		System.out.println(e.evaluate(G));
	}

}
