package fr.univavignon.graphcentr.tests.g04;

import fr.univavignon.graphcentr.g04.ClosenessDirected;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;

/**
 * @author Agbektas Ahmet
 * 
 */
public class ClosenessDirectedTest
{
	/**
	 * Je teste le graphe orienté que j'avais mis comme exemple dans le rapport 
	 * @param args 
	 * 
	 */
	public static void main(String[] args)
	{
		DirectedGraph graph = new DirectedGraph();

        graph.createNode();
        graph.createNode();
        graph.createNode();
        graph.createNode();
        graph.createNode();

        graph.createLink(0, 2);
        graph.createLink(2, 0);
       
        graph.createLink(1, 2);
       
        graph.createLink(2, 3);
        graph.createLink(3, 2);
       
        graph.createLink(2, 4);
       
        graph.createLink(3, 4);
       
        ClosenessDirected closenessDirected = new ClosenessDirected();
        
        /* OUT or IN */
        closenessDirected.setOrientation("OUT");
        //closenessDirected.setOrientation("IN");
        
        CentralityResult result2 = closenessDirected.evaluate(graph);
        
        System.out.println(result2);
	}
}