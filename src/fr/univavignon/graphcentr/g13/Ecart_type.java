package fr.univavignon.graphcentr.g13;

import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

public class Ecart_type implements SimpleCentrality
{
	public double ET_calc(int[] E)
	{
		int N = E.length;
		double res = 0;
		for(int i=0; i<N; i++)
		{
			res = res + E[i]*E[i];
		}
		res = res/N;
		double sum = 0;
		for (int k=1; k<N; k++)
		{
			sum = sum + E[k];
		}
		sum = (sum/N) * (sum/N);
		res = res - sum;
		return Math.sqrt(res); 
	}
	
	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		// TODO Auto-generated method stub
		return null;
	}

}
