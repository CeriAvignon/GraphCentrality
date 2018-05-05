package fr.univavignon.graphcentr.g11;

import fr.univavignon.graphcentr.g11.FloydWarshall;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;


/*method to calculat the eccentricity of a DirectedWeighted graph 
 * @param1: a DirectedWeighted graph 
 * @param2: matrix  of the shortest distances betwen all pair of nodes given by the findShortestDistances method (FloydWarshall algorithm)
 * @return : the eccentricity of each node of the graph 
 * */
/* la classe */
	public abstract class DirectedWeightedEccentricity
	{
		/* */
		public static double [] directedweightedeccentricity(DirectedWeightedGraph inGraph)
		{
		
		 
		
		/* a final eccentricity table */
			double Cee_[];
			/* matrix of the shortest distances filled by the function of FloyedWarshall*/
			double dist[][]= findShortestDistances(inGraph);
			
			/* a loop to initialise  the eccentricity of each node to zero */
			for(int k=0;k< Cee_.length;k++)
			{
				Cee_[k]=0;
			}
			int i;
			int j;
			/* loops to compare eccentricity to the shortest distances value*/
			/* if the current  eccentricity is less than the shortest distances value */
			/* the eccentricity value will replace by the shortest distances value */
			/* and finally the eccentricity value will replace by its inverse */
			for(i=0;i<Cee_.length;i++)
			{
				for(j=0;j<Cee_.length;j++)
				{
					if(Cee_[i] < dist[i][j])
					{
						Cee_[i] =dist[i][j];
					}
				}
				Cee_[i]=1/Cee_[i];
			}
			return Cee_;
	}
		
	}


