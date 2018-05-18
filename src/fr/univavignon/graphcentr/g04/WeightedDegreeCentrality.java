/**
 * 
 */
package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.WeightedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;

/**
 * @author Abidi Hamza
 *
 */
public class WeightedDegreeCentrality implements WeightedCentrality{

	@Override
	public CentralityResult evaluate(WeightedGraph inGraph) {
		CentralityResult result = new CentralityResult();

        int n = inGraph.getNodeCount();
        
        for (int i = 0; i < n; i++)
        {
        	Node node = inGraph.getNodeAt(i);
        	result.add(inGraph.getNodeDegree(node)  / (double)(n - 1));
        }
        
        return result;
	}

}
