package fr.univavignon.graphcentr.tests.g01;

import fr.univavignon.graphcentr.g01.Laverage;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * @author uapv1602077
 *
 */
public class LaverageTest {
	
	/**
	 * 
	 */
	public static void execute()
	{
		SimpleGraph graph = new SimpleGraph();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		graph.createNode();
		
		graph.createLink(0, 1);
		graph.createLink(1, 2);
		graph.createLink(1, 3);
		graph.createLink(2, 3);
		graph.createLink(3, 6);
		graph.createLink(3, 4);
		graph.createLink(4, 5);
		graph.createLink(5, 6);
		
		Laverage centrality = new Laverage();
		
		centrality.evaluate(graph);
	}

}