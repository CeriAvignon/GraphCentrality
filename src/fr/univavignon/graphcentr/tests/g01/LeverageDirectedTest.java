package fr.univavignon.graphcentr.tests.g01;


import fr.univavignon.graphcentr.g01.LeverageDirected;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;



/**
 * @author Riviere Colin, Benoit Loris
 *
 */
public class LeverageDirectedTest {

	
	/**
	 * Execute LeverageDirected Test
	 */
	public static void execute()
	{
		DirectedGraph graph = new DirectedGraph();
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
		
		LeverageDirected centrality = new LeverageDirected();	
		CentralityResult result = centrality.evaluate(graph);
		System.out.println(result);
	}
}
