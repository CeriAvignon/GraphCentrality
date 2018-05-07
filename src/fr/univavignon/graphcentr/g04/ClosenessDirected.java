package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.Node;
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
	private String orientation;
	
	public void setOrientation(String o)
	{
		orientation = o;
	}
	
	@Override
	public CentralityResult evaluate(DirectedGraph inGraph)
	{
		CentralityResult result = new CentralityResult();

		double somme = 0;
		int n = inGraph.getNodeCount();

		double distance[][]= FloydWarshall.findShortestDistances(inGraph);	
		
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (i != j)
				{
					if (orientation == "in")
					{
						somme += distance[j][i];
					}
					else
					{
						somme += distance[i][j];
					}
				}
			}
			result.add((n-1)/somme);
		}

		return result;
	}
}