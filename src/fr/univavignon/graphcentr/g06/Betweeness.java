package fr.univavignon.graphcentr.g06;

import java.util.LinkedList;
import java.util.List; // Liste
import java.util.Stack; // Pile
import java.util.Queue; // File

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;


/**
 * @author Chris_Chevalier
 *
 */
public class Betweeness implements DirectedCentrality {
	
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph) {

		Stack<Node> P = new Stack<Node>();
		Queue<Node> F = new LinkedList<Node>();
		
		int dist[] = new int[inGraph.getNodeCount()];
		List<Node> pred = new LinkedList<Node>(); // Liste des prédecesseurs
		
		List<Integer> sigma = new LinkedList<Integer>();// Nombre de chemins le plus court
		List<Integer> delta = new LinkedList<Integer>();
		
		// Boucle principale :
		for (Node s : inGraph.getNodes()) {
			for (Node w : inGraph.getNodes()) {
				pred.clear();
			}
			
			/*for (int t : inGraph) {
				dist[t] = -1;
			}*/
		}
		
		return null;
	}
	
}