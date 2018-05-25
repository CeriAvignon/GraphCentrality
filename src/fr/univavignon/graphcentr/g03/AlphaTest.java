package fr.univavignon.graphcentr.g03;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;


public class AlphaTest
{
	public static void execute()
	{
		
		DirectedGraph g=new DirectedGraph();
		
		g.createNode();
		g.createNode();
		g.createNode();
		g.createNode();
		
		g.createLink(0, 1);
		g.createLink(0, 2);
		g.createLink(3, 0);
		g.createLink(1, 3);
		g.createLink(3, 2);
		
		double[] e=new double[4];
		e[0]=9;
		e[1]=8;
		e[2]=1;
		e[3]=5;
		
		/*int n=4;
		double[] e=new double[n];
		for(int i=0; i<n; i++)
		{
			e[i]=1;
		}*/
		/*int m=10;
		int k=100;
		double p=0.0;
		SimpleGraph g=new SimpleGraph();
		GraphMLReader r= new GraphMLReader();
		//r.updateFromFile("./res/Benchmark/erdos_renyi/n="+n+"_p="+p+".graphml",g);
		//r.updateFromFile("./res/Benchmark/barabasi_albert/n="+n+"_m="+m+".graphml",g);
		r.updateFromFile("./res/Benchmark/watts_strogatz/n="+n+"_k="+k+".graphml",g);*/
		
		System.out.println("Go!");
		DirectedAlpha sA=new DirectedAlpha(0.5, e, 5);
		Benchmark.start();
		CentralityResult res=sA.evaluate(g);
		Benchmark.stop();
		System.out.println(res);
		Benchmark.printSnapshots();
	}
}