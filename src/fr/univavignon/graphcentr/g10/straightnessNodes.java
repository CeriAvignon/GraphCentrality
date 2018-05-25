package fr.univavignon.graphcentr.g10;

import fr.univavignon.graphcentr.g07.core.AbstractSimpleGraph;
import fr.univavignon.graphcentr.g07.core.SpatialNode;
import fr.univavignon.graphcentr.g07.core.WeightedLink;

import fr.univavignon.graphcentr.g07.core.helper.WeightedGraphHelper;
import fr.univavignon.graphcentr.g07.core.helper.SpatialGraphHelper;

import fr.univavignon.graphcentr.g11.FloydWarshall;
/**
 * @author Habib Mohamed
 * 
 */
public class straightnessNodes {

/**
 * @param k
 * @param j
 * @param distanceShortestPath
 * @return
 */
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
/*
public static double[] fin(AbstractGraph inGraph)
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
			moyenne[k]=moyenne[k]+ Straightness(inGraph.getNodeAt(k),inGraph.getNodeAt(j),distanceShortestPathMatrix[k][j]);
		}
	}
}
moyenne k ← moyenne k /(n − 1);

return moyenne ;

}
*/

}
