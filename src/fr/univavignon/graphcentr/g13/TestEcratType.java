package fr.univavignon.graphcentr.g13;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

public class TestEcratType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleGraph test = new SimpleGraph();
		Node v3 = test.createNode();
		Node v2 = test.createNode();
		Node v4 = test.createNode();
		Node v6 = test.createNode();
		Node v1 = test.createNode();
		Node v5 = test.createNode();
		
		test.createLink(v3, v2);
		test.createLink(v2, v1);
		test.createLink(v1, v5);
		test.createLink(v5, v4);
		test.createLink(v4, v3);
		test.createLink(v3, v6);
		test.createLink(v2, v6);
		test.createLink(v1, v6);
		test.createLink(v5, v6);
		test.createLink(v4, v6);
		
		Ecart_type et = new Ecart_type();
		CentralityResult res = et.AlgoPrincipalePas(test, false, 1000);
		System.out.println(res.toString());
	}

}