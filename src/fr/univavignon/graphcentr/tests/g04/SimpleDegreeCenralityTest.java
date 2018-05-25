package fr.univavignon.graphcentr.tests.g04;

import fr.univavignon.graphcentr.g04.SimpleDegreeCentrality;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * @author uapv1600436
 *
 */
public class SimpleDegreeCenralityTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleGraph graph = new SimpleGraph();
		// Create 6 nodes
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		
		graph.createLink(0, 1);
		graph.createLink(1, 2);
		graph.createLink(1, 3);
		graph.createLink(2, 1);
		graph.createLink(2, 3);
		graph.createLink(3, 4);
		graph.createLink(2, 5);
		
		SimpleDegreeCentrality myCentrality = new SimpleDegreeCentrality();

		CentralityResult result = myCentrality.evaluate(graph);
		
		System.out.println(result);
	}
}
