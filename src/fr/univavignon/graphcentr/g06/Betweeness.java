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
 * @author Chris_Chevalier, Tata Noaman
 *
 */
public class Betweeness implements DirectedCentrality {
	
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph) {

		Stack<Node> P = new Stack<Node>(); 			// Pile permettant de conserver les arrêtes dans un ordre non croissant des distance depuis s
		Queue<Node> F = new LinkedList<Node>();		// File 
		
		int dist[] = new int[inGraph.getNodeCount()];
		List<Node> pred = new LinkedList<Node>(); // Liste des prédecesseurs
		
		//List<Integer> sigma = new LinkedList<Integer>();// Nombre de chemins le plus court
		int sigma[] = new int[inGraph.getNodeCount()];
		List<Integer> delta = new LinkedList<Integer>();// dépendance de s par rapport à v
		
		// Boucle principale :
		for (Node s : inGraph.getNodes()) {
			for (Node w : inGraph.getNodes()) {
				pred.clear();
			}
			
			for (int t=0 ; t < inGraph.getNodeCount(); t++) {
				dist[t] = -1;
				sigma[t] = 0;
			}
			dist[inGraph.getNodeIndex(s)] = 0; // inGraph.getNode(s) plus ou moin la coord du noeud S
			sigma[inGraph.getNodeIndex(s)] = 1;
		}
		
		return null;
	}
	
}
	
