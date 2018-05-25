package fr.univavignon.graphcentr.tests.g04;

import fr.univavignon.graphcentr.g04.ClosenessSimple;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * @author Agbektas Ahmet
 * 
 */
public class ClosenessSimpleTest
{
	/**
	 * Je teste le graphe simple que j'avais mis comme exemple dans le rapport 
	 * @param args 
	 * 
	 */
	public static void main(String[] args)
	{
		 SimpleGraph graph = new SimpleGraph();

	     graph.createNode();
	     graph.createNode();
	     graph.createNode();
	     graph.createNode();
	     graph.createNode();
	     graph.createNode();
	     graph.createNode();

	     graph.createLink(0, 1);
	     graph.createLink(1, 2);
	     graph.createLink(1, 3);
	     graph.createLink(1, 4);
	     graph.createLink(4, 5);
	     graph.createLink(5, 6);
	     
	     ClosenessSimple closenessSimple = new ClosenessSimple();
	     
	     CentralityResult result = closenessSimple.evaluate(graph);
	     
	     System.out.println(result);
	}
}