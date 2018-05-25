/**
 * 
 */
package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;

/**
 * @author hamza
 *
 */
public class SimpleDegreeCentralityTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WeightedGraph graph = new WeightedGraph();
		// Create 3 nodes
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		// Link node 0 with 1, etc...
		graph.createLink(0, 1 , 5);
		graph.createLink(1, 2 , 4);
		graph.createLink(1, 3 , 3);
		graph.createLink(2, 3 , 5);
		graph.createLink(2, 1 , 2);
		graph.createLink(3, 4 , 4);
		graph.createLink(2, 5 , 1);
		
		WeightedDegreeCentrality myCentrality = new WeightedDegreeCentrality();
		//myCentrality.setOrientation(DirectedDegreeCentrality.IN);
		CentralityResult result = myCentrality.evaluate(graph);
		
		/*SimpleGraph graph = new SimpleGraph();
		GraphMLReader hamza = new GraphMLReader();
		
		SimpleDegreeCentrality s = new SimpleDegreeCentrality();
		
		hamza.updateFromFile("Benchmark/watts_strogatz/n=100_k=10.graphml", graph);
		long startTime = System.nanoTime();
		CentralityResult result = s.evaluate(graph);
		long endTime = System.nanoTime();
		double duration = ((endTime - startTime)/1000000.0);
		System.out.println(duration);*/
		
		System.out.println(result);
	}

}
