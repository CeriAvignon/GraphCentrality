package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.DirectedCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

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

		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (i != j)
				{
					if (orientation == "in")
					{
						/* La fonction dist va être implementée par le groupe 11 */
						somme += dist(result.get(j), result.get(i));
					}
					else
					{
						/* La fonction dist va être implementée par le groupe 11 */
						somme += dist(result.get(i), result.get(j));
					}
				}
			}
			result.add((n-1)/somme);
		}

		return result;
	}
}