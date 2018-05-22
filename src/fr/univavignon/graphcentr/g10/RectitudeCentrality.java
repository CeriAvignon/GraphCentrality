package fr.univavignon.graphcentr.g10;

import fr.univavignon.graphcentr.g10.NotImplementedException;
import fr.univavignon.graphcentr.g07.core.SpatialNode;
import fr.univavignon.graphcentr.g07.core.WeightedLink;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SpatialWeightedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SpatialGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SpatialWeightedGraph;
import java.util.List;

/**
 * @author Christophe
 * @version 1.0
 * Cette classe permet définie la mesure de rectitude sur un graphe simple spatial et pondéré
 */
public class RectitudeCentrality implements SpatialWeightedCentrality {
	/**
	 * @author Christophe
	 */
	public RectitudeCentrality() {	}
	
	/**
	 * @author Christophe
	 * @param graph 			un graphe
	 * @return adjacencyMatrix	la matrice d'adjacence (distance)
	 */
	public double[][] WarshallFloyd(SpatialWeightedGraph graph) {
		// recupère le nombre de noeud du graphe
		int nbNodes = graph.getNodeCount();
		// recupère la matrice d'adjacence du graphe
		double[][] adjacencyMatrix = graph.toAdjacencyMatrix();
		// initialisation de chaque case avec la valeur max possible
		for(int k = 0 ; k < nbNodes ; k++) {
			for(int i = 0 ; i < nbNodes ; i++) {
				for (int j = 0 ; j < nbNodes ; j++) {
					adjacencyMatrix[i][j] = Math.min(adjacencyMatrix[i][j], adjacencyMatrix[i][k] + adjacencyMatrix[k][j]);
				}
			}
		}
		return adjacencyMatrix ;
	}
	
	@Override
	public CentralityResult evaluate(SpatialGraph inGraph) {
		// TODO Auto-generated method stub
		return null;
	}
}