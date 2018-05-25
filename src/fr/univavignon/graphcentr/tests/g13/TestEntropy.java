package fr.univavignon.graphcentr.tests.g13;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;
import fr.univavignon.graphcentr.g13.EntropyCentrality;

/**
 * @author uapv1500969
 *
 */
public class TestEntropy {

	/**
	 * @param args
	 */
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

		EntropyCentrality e = new EntropyCentrality();
		
	    long debut = System.currentTimeMillis();

		System.out.println(e.evaluate(G));
		
		long fin = System.currentTimeMillis();
		
		System.out.println("Méthode exécutée en " + Long.toString(fin - debut) + " ms");


		
	}

}
