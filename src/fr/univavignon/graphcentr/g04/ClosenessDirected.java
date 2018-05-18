package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

import fr.univavignon.graphcentr.g11.FloydWarshall;

/**
 * @author Agbektas Ahmet
 * 
 */

public class ClosenessDirected implements DirectedCentrality
{
	/**
	 * By default it's IN
	 */
	private String orientation = "IN";
	
	/**
	 * @param _orientation : IN or OUT
	 */
	public void setOrientation(String _orientation)
	{
		this.orientation = _orientation;
	}
	
	/**
	 * @return Return the variable 'orientation' which is either 'IN' or 'OUT'
	 */
	public String getOrientation()
	{
		return orientation;
	}
	
	/**
	 *  Algorithm for closeness if the graph is oriented
	 */
	
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
                    if (getOrientation() == "IN")
                    {
                        if (distance[j][i] != Double.MAX_VALUE)
                        {
                            somme += distance[j][i];
                        }
                    }
                    else if (getOrientation() == "OUT")
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