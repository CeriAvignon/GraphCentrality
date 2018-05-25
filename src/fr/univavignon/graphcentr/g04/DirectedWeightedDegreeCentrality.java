/**
 * 
 */
package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedWeightedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;

/**
 * @author hamza
 *
 */
public class DirectedWeightedDegreeCentrality implements DirectedWeightedCentrality {

	/**
	 * IN represents the incoming edges
	 */
	public static final boolean IN = false ;
	
	/**
	 * OUT represents the outgoing edges
	 */
	public static final boolean OUT = true ;
	
	/**
	 * orientation takes the OUT value as default value
	 */
	private boolean orientation = OUT;


	/**
	 * @param orientation the orientation to set
	 */
	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}


	@Override
	public CentralityResult evaluate(DirectedWeightedGraph inGraph) {
		CentralityResult result = new CentralityResult();

        int n = inGraph.getNodeCount();
        double [][] adjacencyMatrix = inGraph.toAdjacencyMatrix();
        
        for (int i = 0; i < n; i++)
        {
        	double s = 0;
        	
        	for (int j =0 ; j < n ; j++) {
        		if(orientation == IN) {
	        		if(adjacencyMatrix[j][i] != 0) {
	        			s = s + adjacencyMatrix[j][i];
	        		}
        		}
	        	else if(orientation == OUT) {
	        		if(adjacencyMatrix[i][j] != 0) {
	        			s = s + adjacencyMatrix[i][j];
	        		}
	        	}	
        	}
        	
        	result.add( s  / (double)(n - 1));
        }
        
        return result;
	}

}
