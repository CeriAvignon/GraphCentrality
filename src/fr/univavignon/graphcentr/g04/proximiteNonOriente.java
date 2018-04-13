package fr.univavignon.graphcentr.g04;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * @author Agbektas Ahmet
 *
 */

public class proximiteNonOriente implements SimpleCentrality
{
	@Override
	public CentralityResult evaluate(SimpleGraph inGraph)
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
					somme += dist(result.get(i), result.get(j));
				}
			}
			result.add((n-1)/somme);
		}

		return result;
	}
}