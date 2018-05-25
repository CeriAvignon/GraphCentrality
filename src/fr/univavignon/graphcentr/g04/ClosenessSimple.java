package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

import fr.univavignon.graphcentr.g11.FloydWarshall;

/**
 * @author Agbektas Ahmet
 * 
 */

public class ClosenessSimple implements SimpleCentrality
{
	/**
	 *  Algorithm for closeness if the graph is simple
	 */
	
    @Override
    public CentralityResult evaluate(SimpleGraph inGraph)
    {
        CentralityResult result = new CentralityResult();

        double somme = 0;
        int n = inGraph.getNodeCount();
        
        double distance[][]= FloydWarshall.findShortestDistances(inGraph);
        
        for (int i = 0; i < n; i++)
        {
            somme = 0;
            
            for (int j = 0; j < n; j++)
            {
                if (i != j)
                {
                	somme += distance[i][j];
                }
            }   
            result.add((n-1)/somme);
        }
        return result;
    }
}