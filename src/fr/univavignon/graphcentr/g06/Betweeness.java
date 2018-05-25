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
		
		int Cb[] = new int[inGraph.getNodeCount()];	// resultat betweenness centrality
		Stack<Node> P = new Stack<Node>(); 			// Pile permettant de conserver les arrêtes dans un ordre non croissant des distance depuis s
		Queue<Node> F = new LinkedList<Node>();		// File 
		
		int dist[] = new int[inGraph.getNodeCount()];
		List<List<Node>> pred = new LinkedList<List<Node>>(); // Liste des prédecesseurs
		
		//List<Integer> sigma = new LinkedList<Integer>();// Nombre de chemins le plus court
		int sigma[] = new int[inGraph.getNodeCount()];
		int delta[] = new int[inGraph.getNodeCount()];// dépendance de s par rapport à v
		
		Node v;
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
			F.add(s);
			
			while(!F.isEmpty()) // tant que la file n'est pas vide 
			{
				v =  F.remove();
				P.add(v);
				
				for(Node w : inGraph.getNodes())	// pour chaque noeud w
				{
					if (inGraph.isAdjacentTo(v,w))	//si il est voisin de v
					{
						if(dist[inGraph.getNodeIndex(w)] = -1)	//si w est vue pour la première fois
						{
							dist[inGraph.getNodeIndex(w)] = dist[inGraph.getNodeIndex(v)] + 1;
							F.add(w);
						}
						
						if(dist[inGraph.getNodeIndex(w)] = dist[inGraph.getNodeIndex(v)] +1 )
						{
							sigma[inGraph.getNodeIndex(w)] = sigma[inGraph.getNodeIndex(w)]+sigma[inGraph.getNodeIndex(v)];
							pred.get(inGraph.getNodeIndex(w)).add(v);
						}
					}
				}
			}
			for (Node y : inGraph.getNodes())
				delta[inGraph.getNodeIndex(y)] = 0;
			Node w;
			while(!P.empty())
			{
				w = P.pop();
				for(Node p : pred.get(inGraph.getNodeIndex(w)))			
					delta[inGraph.getNodeIndex(p)] += (sigma[inGraph.getNodeIndex(p)]/ sigma[inGraph.getNodeIndex(w)])*(1 + delta[inGraph.getNodeIndex(w)]);
				if(w != s)
					Cb[inGraph.getNodeIndex(w)] += delta[inGraph.getNodeIndex(w)];
			}
			
			
		}
		
		return null;
	}
	
}
	
