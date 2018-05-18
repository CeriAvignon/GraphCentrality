/**
 * 
 */
package fr.univavignon.graphcentr.g04;


import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

/**
 * @author Abidi Hamza
 *
 */
public class DirectedDegreeCentrality implements DirectedCentrality {
	
			
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
			public CentralityResult evaluate(DirectedGraph inGraph) {
				CentralityResult result = new CentralityResult();

		        int n = inGraph.getNodeCount();
		        int nodes[] = null;
		        
		        if(orientation == IN)
		        	nodes = inGraph.getNodesIncomingDegree();
		        else if (orientation == OUT)
		        	nodes = inGraph.getNodesOutgoingDegree();
		        
		        for (int i = 0; i < n; i++)
		        {
		        	result.add(nodes[i] / (double)(n - 1));
		        }
		        
		        return result;
			}



			

			
			
}
