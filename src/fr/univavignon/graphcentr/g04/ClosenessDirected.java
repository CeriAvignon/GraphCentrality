package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

import fr.univavignon.graphcentr.g11.FloydWarshall;

/**
 * @author Agbektas Ahmet
 */

public class ClosenessDirected implements DirectedCentrality
{
	private String orientation;
	
	public void setOrientation(String o)
	{
		orientation = o;
	}
	
	public String getOrientation()
	{
		return orientation;
	}
	
	@Override
    public CentralityResult evaluate(DirectedGraph inGraph)
    {
        CentralityResult result = new CentralityResult();

        double somme = 0;
        int n = inGraph.getNodeCount();

        double distance[][] = FloydWarshall.findShortestDistances(inGraph);
        
        for (int i = 0; i < n; i++)
        {
            somme = 0;
            
            for (int j = 0; j < n; j++)
            {
                if (i != j)
                {
                    if (getOrientation() == "in")
                    {
                        if (distance[j][i] != Double.MAX_VALUE)
                        {
                            somme += distance[j][i];
                        }
                    }
                    else
                    {
                        if (distance[i][j] != Double.MAX_VALUE)
                        {
                            somme += distance[i][j];
                        }
                    }
                }
            }
            
            if (somme == 0)
            {
                result.add(null);
            }
            else
            {
                result.add((n-1)/somme);
            }
        }

        return result;
    }
}