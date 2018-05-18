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

/**
 * Centrality of Cliques
 * @author Colin Riviere , Loris Benoit
 *
 */
public class Clique  implements SimpleCentrality{
	/**
	 * First iterator for the function "combinaisions"
	 */
	private int iCombi;
	
	/**
	 * Second iterator for the function "combinaisions"
	 */
	private int jCombi;
	
	@SuppressWarnings("unused")
	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		CentralityResult centraliteNoeud = new CentralityResult();
		for(Node u : inGraph.getNodes()) {
			centraliteNoeud.add(0.0);
		}
		List<List<Integer>> listeClique = rechercheCliqueRecursive(inGraph);
		for( List<Integer> clique : listeClique ) {
			for( int node : clique ) {
				centraliteNoeud.set(node, (centraliteNoeud.get(node))+1);
			}
		}
		
		return centraliteNoeud;
	}
	
	
	/**
	 * Use Bouda's algorithm in order to find clique with the algorithm which don't stack combination in an array
	 * @param inGraph
	 * @return List of clique
	 */
	@SuppressWarnings("unused")
	private List<List<Integer>> rechercheCliqueBouda( SimpleGraph inGraph ) {
		List<List<Integer>> listeClique = new ArrayList<List<Integer>>();
		int k = 2;
		List<List<Integer>> kClique = getAllLink(inGraph);
		
		
		while (!kClique.isEmpty()){
			if (k != 2) {
				listeClique.addAll(kClique);
			}
			Set<List<Integer>> cliques1 = new HashSet<List<Integer>>();
			iCombi = 0;
			jCombi = 1;
			Pair<List<Integer>,List<Integer>> cliq = combinaisions(kClique);
			while(cliq != null) {
				
				List<Integer> diff = diffSyme(cliq.first, cliq.second);
				if ( diff.size() == 2 && inGraph.isAdjacentTo(diff.get(0), diff.get(1))) {
					cliques1.add(union(cliq.first, cliq.second));
				}
				cliq = combinaisions(kClique);
				
			}
			kClique.clear();
			kClique.addAll(cliques1);
			k++;
		}
		return listeClique;
		
	}
	
	
	/**
	 * Use Bouda's algorithm in order to find clique
	 * Unappreciated
	 * @param inGraph
	 * @return List of clique
	 */
	@SuppressWarnings("unused")
	private List<List<Integer>> rechercheCliqueBoudaWithTab( SimpleGraph inGraph ) {
		List<List<Integer>> listeClique = new ArrayList<List<Integer>>();
		int k = 2;
		List<List<Integer>> kClique = getAllLink(inGraph);
		
		
		while (!kClique.isEmpty()){
			if (k != 2) {
				listeClique.addAll(kClique);
			}
			Set<List<Integer>> cliques1 = new HashSet<List<Integer>>();
			
			for(Pair<List<Integer>, List<Integer>> cliq : combinaisionsWithTab(kClique)) {
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
		
	
	
	/**
	 * Generate all combination of 2 in KClique.
	 * Send the next combination when called.
	 * Return null if there is no more combination
	 * @param kClique
	 * @return combination
	 */
	private Pair<List<Integer>,List<Integer>> combinaisions(List<List<Integer>> kClique){
		if ( iCombi == kClique.size()-1) return null;
		
		Pair<List<Integer>,List<Integer>> sortie= new Pair<List<Integer>,List<Integer>>(kClique.get(iCombi), kClique.get(jCombi));
		
		jCombi++;
		if (jCombi == kClique.size()) {
			iCombi++;
			jCombi = iCombi+1; 
		}
		
		return sortie;
	}
	
	
	
	/**
	 * Generate all combination of 2 in KClique
	 * Unappreciated
	 * @param kClique
	 * @return combination
	 */
	private List<Pair<List<Integer>,List<Integer>>> combinaisionsWithTab(List<List<Integer>> kClique){
		List<Pair<List<Integer>,List<Integer>>> sortie = new ArrayList<Pair<List<Integer>,List<Integer>>>();
		for(int i=0;i<kClique.size()-1;i++) {
			for(int j=i+1;j<kClique.size();j++) {
				sortie.add(new Pair<List<Integer>,List<Integer>>(kClique.get(i),kClique.get(j)));
			}
		}
		
		return sortie;
	}
	
	
	
	
	/**
	 * Symmetrical difference between u and v
	 * @param u
	 * @param v
	 * @return Symmetrical difference
	 */
	private List<Integer> diffSyme(List<Integer> u, List<Integer> v){
		List<Integer> same = new ArrayList<Integer>(u);
		same.retainAll(v);
		List<Integer> diff = new ArrayList<Integer>(u);
		diff.addAll(v);
		diff.removeAll(same);
		return diff;
	}
	
	
	/**
	 * Union between u and v
	 * @param u
	 * @param v
	 * @return Union
	 */
	private List<Integer> union(List<Integer> u, List<Integer> v){
		Set<Integer> s = new HashSet<Integer>();
		s.addAll(u);
		s.addAll(v);
		return new ArrayList<Integer>(s);
	}
	
	
	/**
	 * Return all Link of the graph as a 2 dimension list
	 * @param inGraph
	 * @return List of Clique
	 */
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
	
	
	/**
	 * Use Recursive's algorithm in order to find clique
	 * @param inGraph
	 * @return List of cliques
	 */
	@SuppressWarnings("unused")
	public List<List<Integer>> rechercheCliqueRecursive(SimpleGraph inGraph){
		List<List<Integer>> listeClique = new ArrayList<List<Integer>>();
		List<Integer> NoeudUse = new ArrayList<Integer>();
		for(Node uN : inGraph.getNodes()) {
			int u = uN.getIdentifier();
			List<Integer> uVoisin = new ArrayList<Integer>();
			uVoisin = getVoisins(inGraph,u);
			uVoisin.removeAll(NoeudUse);
			if( uVoisin.size() >= 2 ) {
				List<Integer> cliqueTest = new ArrayList<Integer>();
				cliqueTest.add(u);
				rechercheCliqueRec(inGraph, listeClique, uVoisin, cliqueTest);
			}
			NoeudUse.add(u);
		}
		return listeClique;
	}
	
	
	/** 
	 * Function recursive of recursive's algorithm
	 * @param inGraph
	 * @param listeClique
	 * @param uVoisin
	 * @param cliqueTest
	 */
	private void rechercheCliqueRec(SimpleGraph inGraph, List<List<Integer>> listeClique, List<Integer> uVoisin, List<Integer> cliqueTest) {
		
		if( cliqueTest.size() >= 3 ) {
			List<Integer> toAdd = new ArrayList<Integer>(cliqueTest);
			Collections.sort(toAdd);
			if( !listeClique.contains(toAdd) ) {
				listeClique.add(toAdd);
			}
		}
	
		for(int w : uVoisin ) {
			List<Integer> wVoisin = new ArrayList<Integer>();
			wVoisin = getVoisins(inGraph,w);
			List<Integer> wTemp = new ArrayList<Integer>();
		
			for(int wDel : wVoisin ) {
				if( uVoisin.contains(wDel) ) {
					wTemp.add(wDel);
				}
			}
			wVoisin = wTemp;
			cliqueTest.add(w);
			rechercheCliqueRec(inGraph, listeClique, wVoisin, cliqueTest);
			cliqueTest.remove(new Integer(w));		
		}
	}
	

	/**
	 * List of neighbours of u
	 * @param inGraph
	 * @param u
	 * @return List of neighbours
	 */
	public List<Integer> getVoisins(SimpleGraph inGraph, int u){
		List<Integer> Voisins = new ArrayList<Integer>();
		for (Link l : inGraph.getNodeLinks(inGraph.getNodeAt(u))) {
			if ( l.getDestinationIdentifier() == u) Voisins.add(l.getSourceIdentifier());
			else Voisins.add(l.getDestinationIdentifier()); 
		}
		
		return Voisins;
	}
}

