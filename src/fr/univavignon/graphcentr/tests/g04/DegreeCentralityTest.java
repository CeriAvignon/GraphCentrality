/**
 * 
 */
package fr.univavignon.graphcentr.tests.g04;

import fr.univavignon.graphcentr.g04.SimpleDegreeCentrality;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;

/**
 * @author hamza
 *
 */
public class DegreeCentralityTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*DirectedGraph graph = new DirectedGraph();
		// Create 3 nodes
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		// Link node 0 with 1, etc...
		/*graph.createLink(0, 1 , 5);
		graph.createLink(1, 2 , 4);
		graph.createLink(1, 3 , 3);
		graph.createLink(2, 3 , 5);
		graph.createLink(2, 1 , 2);
		graph.createLink(3, 4 , 4);
		graph.createLink(2, 5 , 1);
		graph.createLink(0, 1);
		graph.createLink(1, 2);
		graph.createLink(1, 3);
		graph.createLink(2, 1);
		graph.createLink(2, 3);
		graph.createLink(3, 4);
		graph.createLink(2, 5);
		
		DirectedDegreeCentrality myCentrality = new DirectedDegreeCentrality();
		myCentrality.setOrientation(DirectedDegreeCentrality.IN);
		//myCentrality.setOrientation(DirectedWeightedDegreeCentrality.IN);
		CentralityResult result = myCentrality.evaluate(graph);*/
		
		SimpleGraph graph = new SimpleGraph();
		GraphMLReader reader = new GraphMLReader();
		
		SimpleDegreeCentrality s = new SimpleDegreeCentrality();
		
		reader.updateFromFile("Benchmark/erdos_renyi/n=1000_p=0.2.graphml", graph);
		long startTime = System.nanoTime();
		CentralityResult result = s.evaluate(graph);
		long endTime = System.nanoTime();
		double duration = ((endTime - startTime)/1000000.0);
		
		System.out.println(result);
		System.out.println();
		System.out.println(duration);
		
	}

}


