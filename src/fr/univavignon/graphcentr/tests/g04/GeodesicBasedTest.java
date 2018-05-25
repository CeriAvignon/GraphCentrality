package fr.univavignon.graphcentr.tests.g04;


import fr.univavignon.graphcentr.g04.GeodesicBased;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;


/**
 * @author Corentin Bettiol
 *
 */
public class GeodesicBased {

	
	/**
	 * Execute Centrality Based Test
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
		graph.createNode();
		graph.createNode();
		graph.createNode();
		
		graph.createLink(0, 1);
		graph.createLink(0, 9);
		graph.createLink(1, 2);
		graph.createLink(1, 3);
		graph.createLink(1, 4);
		graph.createLink(1, 5);
		graph.createLink(1, 6);
		graph.createLink(2, 4);
		graph.createLink(3, 4);
		graph.createLink(4, 8);
		graph.createLink(4, 5);
		graph.createLink(5, 7);
		graph.createLink(8, 9);
		
		
		GeodesicBased centrality = new GeodesicBased();
		CentralityResult result = centrality.evaluate(graph);
		System.out.println(result);
	}
}
