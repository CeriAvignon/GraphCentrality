package fr.univavignon.graphcentr.g08;
import fr.univavignon.graphcentr.g07.core.*;
import fr.univavignon.graphcentr.g07.core.centrality.*;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;


/**
 * @author g08
 *
 */
class CorenessCentrality implements SimpleCentrality 
{
	@Override
	public CentralityResult evaluate(SimpleGraph G)
	{
		CentralityResult Result = new CentralityResult();
		
		int n = G.getNodeCount();
		
		double[] K = new double[n];
		
		double[][] D = new double[2][n];
		
		Node parcour;
		for(int i=0; i<n; i++)
		{
			parcour = G.getNodeAt(i);
			D[0][i] = i;
			D[1][i] = G.getNodeDegree(parcour);
		}
		
		double_sort(D);
		
		for(int v=0; v<n; v++)
		{
			K[v] = D[1][v];
			Result.add(v, D[1][v]);
			for(int u=0; u<n; u++)
			{
				if(G.isAdjacentTo(v, u))
				{
					if(D[1][u]>D[1][v])
					{
						D[1][u] = D[1][u] - 1;
						double_sort(D);
					}
				}
			}
		}
		return Result;
	}
	
	/**
	 * @param D
	 */
	void double_sort(double[][] D)
	{
		double temp;
		for(int j=1; j<D.length; j++)
		{
			int i = j;
			while((i-1)>=0 && (D[1][i]<D[1][i-1]))
			{
				temp = D[1][i-1];
				D[1][i-1] = D[1][i];
				D[1][i] = temp;
				
				temp = D[0][i-1];
				D[0][i-1] = D[0][i];
				D[0][i] = temp;
				
				i--;
			}
		}
	}
}

