package fr.univavignon.graphcentr.g11;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.WeightedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;

/**
 * 
 * @author Djebablia Mustapha
 * 
 * @brief The Eccentricity class applied on Weighted Graph
 * 
 */

public class WeightedEccentricity implements WeightedCentrality{

	@Override
	public CentralityResult evaluate(WeightedGraph inGraph) {
		// TODO Auto-generated method stub
		CentralityResult result = new CentralityResult();
		
		/** n represents the number of node in the graph */
		int n = inGraph.getNodeCount();
		
		/** Cex[] the array that contains the eccentricities of each node of the graph*/
		double Cex[] = new double[n];
		
		/** dist the matrix that contains the shortest distances between all the nodes of the graph*/
		double dist[][]= FloydWarshall.findShortestDistances(inGraph);
		
		/**We initialize the array Cex[] which will contain the eccentricities of each node by 0*/
		for(int i = 0; i < n; i++) {
			Cex[i]= 0;
		}
		
		/** These nested loops are used to diagonally traverse the dist[][] 
		 * matrix to avoid doing the same thing twice
		 */
		for(int i = 0; i < n-1; i++) {
			for(int j = i+1; j < n; j++) {
				if(Cex[i] < dist[i][j]) {
					Cex[i] = dist[i][j];
				}
				if(Cex[j] < dist[i][j]) {
					Cex[j] = dist[i][j];
				}
			}
			/**Finally we add the inverse of Cex[i] to result*/
			result.add(1/Cex[i]);
		}
		
		return result;
	}

}
