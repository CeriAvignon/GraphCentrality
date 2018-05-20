package fr.univavignon.graphcentr.g13;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

public class TestEcratType {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
 		SimpleGraph test = new SimpleGraph();
		Node v1 = test.createNode();
		Node v2 = test.createNode();
		Node v3 = test.createNode();
		Node v4 = test.createNode();
		Node v5 = test.createNode();
		Node v6 = test.createNode();
		
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
		
		DirectedGraph test2 = new DirectedGraph();
		Node va = test2.createNode();
		Node vb = test2.createNode();
		Node vc = test2.createNode();
		Node vd = test2.createNode();

		test2.createLink(va, vb);
		test2.createLink(vb, vc);
		test2.createLink(vc, va);
		test2.createLink(va, vd);
		test2.createLink(vd, vc);
		
		Ecart_type et = new Ecart_type();
		Directed_Ecart_Type det = new Directed_Ecart_Type();
		CentralityResult resTemps = et.AlgoPrincipaleTemps(test);
		et.setTempsOuNombre(true);
		CentralityResult resPas = et.AlgoPrincipalePas(test);
		System.out.println("Algo nombre de pas (" + et.getNombreDePasParNoeud() + ") :");
		System.out.println(resPas.toString());
		System.out.println("Algo fct temps (" + et.getTempsEnSeconde() + "s) :");
		System.out.println(resTemps.toString());
		
		CentralityResult resTemps2 = det.AlgoPrincipaleTemps(test2);
		det.setTempsOuNombre(true);
		CentralityResult resPas2 = det.AlgoPrincipalePas(test2);
		System.out.println("Algo nombre de pas (" + det.getNombreDePasParNoeud() + ") :");
		System.out.println(resPas2.toString());
		System.out.println("Algo fct temps (" + det.getTempsEnSeconde() + "s) :");
		System.out.println(resTemps2.toString());
	}

}