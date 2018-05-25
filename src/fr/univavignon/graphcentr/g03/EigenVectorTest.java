package fr.univavignon.graphcentr.g03;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.utility.Benchmark;


public class EigenVectorTest
{
	public static void execute()
	{
		/*//	--------Exmple du rapport---------
		SimpleGraph g=new SimpleGraph();
		
		g.createNode();
		g.createNode();
		g.createNode();
		g.createNode();
		
		g.createLink(0, 2);
		g.createLink(2, 1);
		g.createLink(2, 3);
		g.createLink(1, 3);*/
		
			//-------------Benchmarks----------------
		int n=1000;
		int m=5;
		int k=100;
		double p=0.0;
		SimpleGraph g=new SimpleGraph();
		GraphMLReader r= new GraphMLReader();
		//r.updateFromFile("./res/Benchmark/erdos_renyi/n="+n+"_p="+p+".graphml",g);
		r.updateFromFile("./res/Benchmark/barabasi_albert/n="+n+"_m="+m+".graphml",g);
		//r.updateFromFile("./res/Benchmark/watts_strogatz/n="+n+"_k="+k+".graphml",g);
		
		System.out.println("Go!");
		EigenVector eigen=new EigenVector();
		Benchmark.start();
		CentralityResult res=eigen.evaluate(g);
		Benchmark.stop();
		System.out.println(res);
		Benchmark.printSnapshots();
	}
}