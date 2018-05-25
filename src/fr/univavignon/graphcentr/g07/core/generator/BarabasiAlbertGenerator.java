package fr.univavignon.graphcentr.g07.core.generator;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import fr.univavignon.graphcentr.g07.core.Node;

/**
 * 
 * @author Mollier Pablo
 *  
 * @brief Generates a random graph using the Barabasi Albert Algorithm.
 */

public class BarabasiAlbertGenerator
{
	/**
	 * Generates a random graph using the Barabasi Albert Algorithm.
	 * @param nodeCount How many nodes the new graph will have.
	 * @param degree The degree every node will be created with.
	 * @return SimpleGraph
	 */
	public static SimpleGraph generate(int nodeCount, int degree)
	{
		//New graph initialization
		SimpleGraph newGraph = new SimpleGraph();
		//Probability list initialization.
		//For example, if a node has a 2/3 probability to be chosen, it will appear 2 times in this list (which will contain 3 elements).
		ArrayList<Node> probaList = new ArrayList<Node>();
		
		
		//We will add the nodes to the graph in this loop
		for (int i = 0; i < nodeCount; i++)
		{
			//Removes all elements from the probability list.
			probaList.clear();
			
			//Initializes the probability list
			//Every node already in the graph will appear n times in the list (with n the node's degree)
			for (int j = 0; j < newGraph.getNodeCount(); j++)
			{
				for (int l = 0; l < newGraph.getNodeDegree(j); l++)
				{
					probaList.add(newGraph.getNodeAt(j));
				}
			}
			//Creates a new node in the graph
			Node newNode = newGraph.createNode();
			
			if(newGraph.getNodeCount() == 2)
				newGraph.createLink(0, 1);
			
			//Creates links between the new node and a random node from the probability list
			//Stops when all the nodes in the graph were linked or when degree links where linked (whichever is lowest).
			for (int j = 0; j < degree; j++)
			{
				//If probaList is empty it means no links can be created.
				if (probaList.size() > 0)
				{
					//Generates a random index for the probability list
					int randomIndex = ThreadLocalRandom.current().nextInt(0, probaList.size());
					//Creates a new random node from the probability list
					Node randomNode = probaList.get(randomIndex);
					//Creates the link
					newGraph.createLink(newNode, randomNode);
					//We remove all the instances of the randomly chosen node from the list so it cannot be chosen again.
					for(int k = 0; k < probaList.size(); k++)
					{
						if(probaList.get(k) == randomNode)
						{
							probaList.remove(k);
						}
					}
				}
			}
		}
		
		return newGraph;
	}
}

