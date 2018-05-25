/**
 * 
 */
package fr.univavignon.graphcentr.g04;


import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.WeightedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;

/**
 * @author Abidi Hamza
 *
 */
public class SimpleWeightedDegreeCentrality implements WeightedCentrality{


	@Override
	public CentralityResult evaluate(WeightedGraph inGraph) {
		CentralityResult result = new CentralityResult();

        int n = inGraph.getNodeCount();
        double [][] adjacencyMatrix = inGraph.toAdjacencyMatrix();
        
        for (int i = 0; i < n; i++)
        {
        	double s = 0;
        	
        	for (int j =0 ; j < n ; j++) {
        		if(adjacencyMatrix[i][j] != 0) {
        			s = s + adjacencyMatrix[i][j];
        		}
        			
        	}
        	result.add( s  / (double)(n - 1));
        }
        
        return result;
	}

}
