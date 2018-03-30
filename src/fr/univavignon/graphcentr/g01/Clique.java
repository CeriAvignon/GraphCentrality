package fr.univavignon.graphcentr.g01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;


public class Clique  implements SimpleCentrality{
	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		CentralityResult centraliteNoeud = new CentralityResult();
		
		return centraliteNoeud;
		
	}
	
	private List<List<Integer>> RechercheCliqueBouda( SimpleGraph inGraph ) {
		List<List<Integer>> listeClique = new ArrayList<List<Integer>>();
		int k = 2;
		List<List<Integer>> kClique = getAllLink(inGraph);
		
		
		while (!kClique.isEmpty()){
			if (k != 2) {
				listeClique.addAll(kClique);
			}
			Set<List<Integer>> cliques1 = new HashSet<List<Integer>>();
			
			for(Pair<List<Integer>, List<Integer>> cliq : combinaisions(kClique)) {
				List<Integer> diff = diffSyme(cliq.first, cliq.second);
				if ( diff.size() == 2 && inGraph.isAdjacentTo(diff.get(0), diff.get(1))) {
					cliques1.add(union(cliq.first, cliq.second));
				}
			}
			kClique.clear();
			kClique.addAll(cliques1);
			k++;
		}
		return listeClique;
		
	}
	
	
	private List<Pair<List<Integer>,List<Integer>>> combinaisions(List<List<Integer>> kClique){
		List<Pair<List<Integer>,List<Integer>>> sortie = new ArrayList<Pair<List<Integer>,List<Integer>>>();
		for(int i=0;i<kClique.size()-1;i++) {
			for(int j=i+1;j<kClique.size();j++) {
				sortie.add(new Pair<List<Integer>,List<Integer>>(kClique.get(i),kClique.get(j)));
			}
		}
		
		return sortie;
	}
	
	private List<Integer> diffSyme(List<Integer> u, List<Integer> v){
		List<Integer> same = new ArrayList<Integer>(u);
		same.retainAll(v);
		List<Integer> diff = new ArrayList<Integer>(u);
		diff.addAll(v);
		diff.removeAll(same);
		return diff;
	}
	
	private List<Integer> union(List<Integer> u, List<Integer> v){
		Set<Integer> s = new HashSet<Integer>();
		s.addAll(u);
		s.addAll(v);
		return new ArrayList<Integer>(s);
	}
	
	private List<List<Integer>> getAllLink(SimpleGraph inGraph){
		List<List<Integer>> kClique = new ArrayList<List<Integer>>();
		Set<List<Integer>> kCliqueTmp = new HashSet<List<Integer>>();
		for(int i =0; i < inGraph.getNodeCount();i++) {
			List<Link> listeLinkNoeud = inGraph.getNodeLinks(inGraph.getNodeAt(i));
			for (int j=0 ; j< listeLinkNoeud.size();j++) {
				List<Integer> tempo = new ArrayList<Integer>();
				tempo.add(listeLinkNoeud.get(j).getDestinationIdentifier());
				tempo.add(listeLinkNoeud.get(j).getSourceIdentifier());
				Collections.sort(tempo);
				kCliqueTmp.add(tempo);
			}
		}
		kClique.addAll(kCliqueTmp);
		return kClique;
	}
	
}


