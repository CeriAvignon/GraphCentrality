package fr.univavignon.graphcentr.g11;

import fr.univavignon.graphcentr.g07.core.AbstractGraph;

/**
 * 
 * @author Djebablia Mustapha
 * 
 * @brief The Floyd-Warshall algorithm that determines the shortest distances between all pairs of nodes in a graph G
 * 
 */

public abstract class FloydWarshall {
	
	/**
	 * Static method to find shortest Distances between all pairs of nodes, negative edges allowed
	 * @param inGraph Directed or undirected graph
	 * @return matrix of the shortest distances
	 */
	
	public static double[][] findShortestDistances(AbstractGraph inGraph){
		
		/** n represents the number of node in the graph */
		int n = inGraph.getNodeCount();
		
		/** dist[][] the matrix that contains the shortest distances between all the nodes of the graph*/
		double[][] dist = new double[n][n];
		
		/** We convert the graph as an adjacency matrix*/
		double[][] adjacencyMatrix = inGraph.toAdjacencyMatrix();
		
		
		for(int i = 0; i < n; i++) {
			/** We initialize the distance of each node to itself to zero*/
			dist[i][i] = 0;
			/** We fill the matrix dist[][] by the weights between each two nodes
			presented in the weight matrix adjacencyMatrix[][]*/
			for(int j = 0; j < n; j++) {
				if( i != j) {
					/** If there is an arc between two different nodes i and j */
					if(adjacencyMatrix[i][j] != 0) {
						/** Then we put the weight of this arc in dist[i][j] */
						dist[i][j] = adjacencyMatrix[i][j];
					}else {
						/** Otherwise we put infinite */
						dist[i][j] = Double.MAX_VALUE;
					}
				}
			}
		}
		
		/** These nested loops fill the matrix dist[][] with the shortest distances between all node pairs in our graph*/
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if( dist[i][j] > dist[i][k] + dist[k][j] ) {
						dist[i][j] = dist[i][k] + dist[k][j];
					}
				}
			}
		}
		
		return dist;
		
	} 

}
