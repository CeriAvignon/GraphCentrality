package fr.univavignon.graphcentr.g03;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;


public class EigenVectorColtTest
{
	public static void main(String[] args)
	{
		
		SimpleGraph g=new SimpleGraph();
		
		g.createNode();
		g.createNode();
		g.createNode();
		g.createNode();
		
		g.createLink(0, 2);
		g.createLink(2, 1);
		g.createLink(2, 3);
		g.createLink(1, 3);
		
		
		EigenVectorColt eigen=new EigenVectorColt();
		CentralityResult res=eigen.evaluate(g);
		
		System.out.println(res);
	}
}