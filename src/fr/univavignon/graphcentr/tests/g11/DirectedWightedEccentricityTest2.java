package fr.univavignon.graphcentr.tests.g11;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;
import fr.univavignon.graphcentr.g11.DirectedWightedEccentricity;

/**
 * @author KHELAFI Abdelhamid
 *
 */
public class DirectedWightedEccentricityTest2 {
	
	/**
	 * @param args 
	 * 
	 */
	public static void main(String[] args)
		{
			System.out.println("----- Directed Weighted Eccentricity TEST -----");
			
			DirectedWeightedGraph graph = new DirectedWeightedGraph();
			
			Node n0 = graph.createNode();
			Node n1 = graph.createNode();
			Node n2 = graph.createNode();
			Node n3 = graph.createNode();
			Node n4 = graph.createNode();
			
			graph.createLink(n0, n1, 4);
			graph.createLink(n0, n2, 2);
			graph.createLink(n1, n3, 3);
			graph.createLink(n1, n2, 1);
			graph.createLink(n2, n4, 3);
			graph.createLink(n3, n4, 4);
			
			
			
			DirectedWightedEccentricity centrality = new DirectedWightedEccentricity();
			CentralityResult  result = centrality.evaluate(graph);
			
			System.out.println(result);
			
			System.out.println("----- Directed Weighted Eccentricity TEST -----");
		}
}
