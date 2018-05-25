/**
 * 
 */
package fr.univavignon.graphcentr.tests.g04;

import fr.univavignon.graphcentr.g04.DirectedDegreeCentrality;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

/**
 * @author hamza
 *
 */
public class DirectedDegreeCentralityTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DirectedGraph graph = new DirectedGraph();
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
		
		DirectedDegreeCentrality myCentrality = new DirectedDegreeCentrality();
		myCentrality.setOrientation(DirectedDegreeCentrality.IN);

		CentralityResult result = myCentrality.evaluate(graph);
		
		System.out.println(result);
	}

}


