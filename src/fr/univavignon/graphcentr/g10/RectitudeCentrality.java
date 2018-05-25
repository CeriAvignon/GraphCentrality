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
	
	private SpatialWeightedGraph graph = null ;
	
	
	/**
	 * @author Christophe
	 */
	public RectitudeCentrality(SpatialWeightedGraph graph) {
		this.graph = graph ;
	}
	
	/**
	 * @author Christophe
	 * @param graph 			un graphe spatial
	 * @return adjacencyMatrix	la matrice d'adjacence (distance)
	 */
	public double[][] WarshallFloyd() {
		// recupère le nombre de noeud du graphe
		int nbNodes = graph.getNodeCount();
		// recupère la matrice d'adjacence du graphe
		double[][] adjacencyMatrix = graph.toAdjacencyMatrix();
		// initialisation de chaque case avec la valeur max possible
		for(int k = 0 ; k < nbNodes ; k++) {
			for(int i = 0 ; i < nbNodes ; i++) {
				for (int j = 0 ; j < nbNodes ; j++) {
					if (adjacencyMatrix[i][j] > adjacencyMatrix[i][k] + adjacencyMatrix[k][j]) {
						adjacencyMatrix[i][j] = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];
					}
				}
			}
		}
		return adjacencyMatrix ;
	}
	
	/**
	 * @author Christophe
	 * @param graph			un graphe spatial
	 * @return moyenne		un tableau de moyenne
	 */
	private double[] rectitudeMoyenne() {
		int nbNodes = graph.getNodeCount();
		double[][] distanceShortestPathMatrix = WarshallFloyd();
		double[] moyenne = new double[nbNodes];
		for (int k = 0 ; k < nbNodes ; k++) {
			for (int j = 0 ; j < nbNodes ; j++) {
				if (k != j) {
					moyenne[k] += rectitude(k, j, distanceShortestPathMatrix[k][j]);
				}
			}
			moyenne[k] /= nbNodes -1;			
		}
		return moyenne ;
	}
	
	private double rectitude(int k, int j, double distanceShortestPath) {
		double resRectitude = 0 ;
		double distanceEuclidienne = graph.getEuclideanDistance(graph.getNodeAt(k), graph.getNodeAt(j));
		if (distanceShortestPath == Double.MAX_VALUE) {
			resRectitude = 0;
		}
		else {
			if (distanceShortestPath == distanceEuclidienne){
				resRectitude = 1 ;
			} else {
				resRectitude = distanceEuclidienne / distanceShortestPath ;
			}
		}
		return resRectitude ;
	}
	
	
	
	@Override
	public CentralityResult evaluate(SpatialGraph inGraph) {
		
		// TODO Auto-generated method stub
		return null;
	}
}