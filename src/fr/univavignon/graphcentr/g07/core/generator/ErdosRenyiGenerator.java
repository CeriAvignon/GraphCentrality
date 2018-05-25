package fr.univavignon.graphcentr.g07.core.generator;

import java.util.Random;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * 
 * @author Clement Mathieu
 * 
 * @brief Generate random graph with Erdos-Renyi method.
 */
public class ErdosRenyiGenerator{
	
	/**
	 * Generate random graph with Erdos-Renyi method.
	 * @param nodeCount Number of nodes in the generated graph.
	 * @param probability The links' probability to be created.
	 * @return Created graph.
	 */
	public SimpleGrah generate(int nodeCount, float probability)
	{
		SimpleGraph simpleGraph = new SimpleGraph();
		
		float rand;
		Random r = new Random();
		
		for(int i = 0;i < nodeCount;i++)
		{
			simpleGraph.createNode();
		}
		
		for(int i = 0;i < nodeCount;i++)
		{
			for(int j = i+1;j < nodeCount;j++)
			{
				rand = r.nextFloat();
				if(probability >= rand)
				{
					simpleGraph.createLink(i,j);
				}
			}
		}
		
		return simpleGraph;
		
	}
	
}
