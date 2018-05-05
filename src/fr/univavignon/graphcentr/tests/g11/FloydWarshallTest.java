package fr.univavignon.graphcentr.tests.g11;


import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;
import fr.univavignon.graphcentr.g11.FloydWarshall;

/**
 * 
 * @author Djebablia Mustapha
 * 
 * @brief The test of Floyd-Warshall algorithm applied on directed weighted graph
 * 
 */

class FloydWarshallTest {

	public static void execute()
	{
		System.out.println("=============== FloydWarshall test ===============");
		
		DirectedWeightedGraph graph = new DirectedWeightedGraph();
		
		Node node0 = graph.createNode();
		Node node1 = graph.createNode();
		Node node2 = graph.createNode();
		Node node3 = graph.createNode();
		
		
		graph.createLink(node1, node0, 4);
		graph.createLink(node0, node2, -2);
		graph.createLink(node1, node2, 3);
		graph.createLink(node3, node1, -1);
		graph.createLink(node2, node3, 2);
		
		double[][] result = FloydWarshall.findShortestDistances(graph);
		
		for(int i = 0; i < 4; i++ ) {
			for(int j = 0; j < 4; j++) {
				System.out.print(result[i][j]+ " " );
			}
			System.out.println();
		}
		System.out.println("========== FloydWarshall test completed ==========");
	}
}
