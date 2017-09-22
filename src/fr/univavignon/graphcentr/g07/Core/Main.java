package Tests;

import Core.LinkUtility;
import Core.SimpleAlgorithm;
import Core.SimpleNode;

import java.util.Vector;

import Core.Graph;

public class Main 
{
	public static void main(String [ ] args)
	{
		System.out.println("HI THERE");
		
		Graph<NamedNode, WeightedLink> Graph1 = new Graph<>();
		
		Graph1.AddNode(new NamedNode("gqq"));
		Graph1.AddNode(new NamedNode("a"));
		Graph1.AddNode(new NamedNode("bc"));
		Graph1.AddNode(new NamedNode("bf"));
		Graph1.AddNode(new NamedNode("dfg"));
		
		LinkUtility.Link(Graph1.At(0), Graph1.At(1));
		LinkUtility.Link(Graph1.At(1), Graph1.At(2));
		LinkUtility.Link(Graph1.At(1), Graph1.At(3));
		
		SimpleAlgorithm Algo = new SimpleAlgorithm();
		Vector<Integer> Results = Algo.Evaluate(Graph1);
		
		System.out.println(Graph1.At(Results.get(0)).Name);
	}
}
