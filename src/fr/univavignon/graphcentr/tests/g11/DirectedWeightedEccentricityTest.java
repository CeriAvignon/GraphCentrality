package fr.univavignon.graphcentr.tests.g11;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;
import fr.univavignon.graphcentr.g11.DirectedEccentricity;


/**
 * 
 * @author Lounissa Imane 
 * 
 * @brief The test of Eccentricity algorithm applied on directed weighted graph
 * 
 */

public class DirectedWeightedEccentricityTest 
{

	public static void execute()
	{
		System.out.println("*****************Directed Weighted Eccentricity test**********************");
		
		DirectedWeightedGraph graph = new DirectedWeightedGraph();
		
		Node node1 = graph.createNode();
		Node node2 = graph.createNode();
		Node node3 = graph.createNode();
		Node node4 = graph.createNode();
		Node node5 = graph.createNode();
		Node node6 = graph.createNode();
		
		graph.createLink(node1,node2,5);
		graph.createLink(node2,node3,2);
		graph.createLink(node3,node2,8);
		graph.createLink(node1,node4,7);
		graph.createLink(node4,node1,2);
		graph.createLink(node5,node2,5);
		graph.createLink(node3,node5,3);
		graph.createLink(node5,node6,1);
		graph.createLink(node6,node3,9);
		
		DirectedEccentricity centrality = new DirectedEccentricity();

		CentralityResult  result = centrality.evaluate(graph);
		
		/** Impossible de réaliser ce test car la fonction evaluate dans le fichier Directed eccentricity.java doit avoir comme paramètre un graphe de type DirectedWeighted Graph mais pas Directed Graph    */
		/* car notre mesure est applicable sur deux types de graphes différents : pondéré  non orienté et pondéré orienté*/
		/* nous somme dans l'attente de la correction de cette erreur */
		/*fr.univavignon.graphcentr.g11.DirectedEccentricity.java     (Ligne 17 )*/
		
		
		System.out.println(result);
	
		
		System.out.println("****************Directed Weighted Eccentricity test completed********************");
	}
}