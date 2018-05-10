/**
 * 
 */
package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.Node;

/**
 * @author Abidi Hamza
 *
 */
public class SimpleDegreeCentrality implements SimpleCentrality{

	@Override
    public CentralityResult evaluate(SimpleGraph inGraph)
    {
        CentralityResult result = new CentralityResult();

        int n = inGraph.getNodeCount();
        
        for (int i = 0; i < n; i++)
        {
        	Node node = inGraph.getNodeAt(i);
        	
        	result.add(inGraph.getNodeDegree(node) / (double)(n - 1));
        }
        
        return result;
    }
}
