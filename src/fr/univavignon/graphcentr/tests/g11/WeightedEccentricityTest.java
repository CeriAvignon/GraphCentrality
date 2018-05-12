package fr.univavignon.graphcentr.tests.g11;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;
import fr.univavignon.graphcentr.g11.WeightedEccentricity;


/**
 * 
 * @author Djebablia Mustapha
 * 
 * @brief The test of Eccentricity algorithm applied on undirected weighted graph
 * 
 */

public class WeightedEccentricityTest {

	public static void main(String[] args)
	{
		System.out.println("=============== Weighted Eccentricity test ===============");
		
		WeightedGraph graph = new WeightedGraph();
		
		Node node0 = graph.createNode();
		Node node1 = graph.createNode();
		Node node2 = graph.createNode();
		Node node3 = graph.createNode();
		Node node4 = graph.createNode();
		
		graph.createLink(node0, node1, 4);
		graph.createLink(node0, node2, 2);
		graph.createLink(node1, node3, 3);
		graph.createLink(node1, node2, 1);
		graph.createLink(node2, node4, 3);
		graph.createLink(node3, node4, 4);
		
		WeightedEccentricity centrality = new WeightedEccentricity();

		CentralityResult result = centrality.evaluate(graph);
		
		System.out.println(result);
	
		
		System.out.println("========== Weighted Eccentricity test completed ==========");
	}
}