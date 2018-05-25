package fr.univavignon.graphcentr.g04;

import java.util.ArrayList;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;

import fr.univavignon.graphcentr.g11.FloydWarshall;

/**
 * Geodesic-based Centrality
 * @author Corentin Bettiol
 */
public class GeodesicBased  implements SimpleCentrality
{

	/**
	 * delta value
	 * 1 = centralité réciproque par défaut
	 */
	private float delta = 1;
	
	@Override
	public CentralityResult evaluate(SimpleGraph g)
	{
		CentralityResult Result = new CentralityResult();
		
		// calcul des plus courts chemins
		double dist[][]= FloydWarshall.findShortestDistances(g);
		
		double c[] = new double[g.getNodeCount()];
		
		// calcul du score pour chaque nœud
		for(int i = 0; i < g.getNodeCount(); i++)
		{
			// calcul du score de centralité du nœud courant
			for(int j = 0; j < g.getNodeCount(); j++)
				if(i != j && dist[i][j] != Double.MAX_VALUE)
					// c += dist(i,j)^-d
					c[i] += Math.pow(dist[i][j], -delta);
			// c /= n
			c[i] /= g.getNodeCount();
		}
		
		ArrayList<Double> arr = new ArrayList<Double>();
		
		// convert data into an arraylist
		for(int i = 0; i < g.getNodeCount(); i++)
			arr.add(c[i]);
		
		// add the arraylist into 
		Result.addAll(arr);
		
		return Result;
	}
	
	/**
	 * @param n the new delta value
	 */
	public void setDelta(float n)
	{
		float = n;
	}
	
	/**
	 * @return delta the current delta value
	 */
	public float getDelta()
	{
		return delta;
	}
}

