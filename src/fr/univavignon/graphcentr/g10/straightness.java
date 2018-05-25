package fr.univavignon.graphcentr.g10;

import fr.univavignon.graphcentr.g07.core.AbstractGraph;
import fr.univavignon.graphcentr.g07.core.SpatialNode;
import fr.univavignon.graphcentr.g07.core.centrality.SpatialWeightedCentrality;
import fr.univavignon.graphcentr.g11.FloydWarshall;
/**
 * @author Habib Mohamed
 * 
 */
public class straightness implements SpatialWeightedCentrality{

/**
 * @param k
 * @param j
 * @param distanceShortestPath
 * @return
 */


public static double[] Straightness(SpatialWeightedGraph inGraph)
{


int n = inGraph.getNodeCount();
double[][] adjacencyMatrix = inGraph.toAdjacencyMatrix();
double[][] distanceShortestPathMatrix = findShortestDistances(inGraph);

double moyenne[] = new double[n];

for(int k=0;k<n;k++)
{
	for(int j=0;j<n;j++)
	{
		if(k!=j)
		{
			moyenne[k]=moyenne[k]+ StraightnessNodes(inGraph.getNodeAt(k),inGraph.getNodeAt(j),distanceShortestPathMatrix[k][j]);
		}
	}
moyenne [k] = moyenne [k] /(n-1);
}
return moyenne ;

}

public static double StraightnessNodes(SpatialNode k,SpatialNode j,double distanceShortestPath)
{
	//Initialisation
	
	double resStraightness=0;
	double de=distanceEuclidienne(k,j);

	if(distanceShortestPath==Double.MAX_VALUE)
	{
		resStraightness=0;
	}
	else
	{
		if(distanceShortestPath==de)
		{
			resStraightness=1;
		}
		else
		{
			resStraightness=de/distanceShortestPath;
		}
	}
	return resStraightness;
}
	public static double distanceEuclidienne(SpatialNode u,SpatialNode v)
	{
	return Math.sqrt(Math.pow((u.getX()-v.getX()),2)+Math.pow((u.getY()-v.getY()),2));
	}


}