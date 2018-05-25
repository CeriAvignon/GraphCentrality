package fr.univavignon.graphcentr.tests.g13;

import java.util.ArrayList;
import java.util.List;

import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;
import fr.univavignon.graphcentr.g13.Ecart_type;

public class TestSecondOrdre {

	public static void main(String[] args) {
		
		SimpleGraph test = new SimpleGraph();
		
 		Ecart_type et = new Ecart_type();
 		
 		Node v1 = test.createNode();
 		Node v2 = test.createNode();
 		Node v3 = test.createNode();
 		Node v4 = test.createNode();
 		Node v5 = test.createNode();
 		Node v6 = test.createNode();
 		
 		test.createLink(v2, v3);
 		test.createLink(v1, v2);
 		test.createLink(v1, v5);
 		test.createLink(v5, v4);
 		test.createLink(v4, v3);
 		test.createLink(v6, v1);
 		test.createLink(v6, v2);
 		test.createLink(v6, v3);
 		test.createLink(v6, v4);
 		test.createLink(v6, v5);

		et.setNombreDePasParNoeud(1000);
		double tmpAuLancement = System.currentTimeMillis();
		CentralityResult resPas = et.evaluate(test);
		System.out.println("Algo fct Pas (" + et.getNombreDePasParNoeud() + ") :");
		System.out.println(resPas.toString());
		System.out.println("Nombre de pas effectuer : " + et.getNombreDePasEffectuer());
		double tmpALaFin = System.currentTimeMillis();
		double tempsExec = tmpALaFin - tmpAuLancement;
		System.out.println("Temps d'execution : " + tempsExec);
		
	}
}