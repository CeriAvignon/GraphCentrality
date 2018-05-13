package fr.univavignon.graphcentr.g01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.centrality.CentralityResult;
import fr.univavignon.graphcentr.g07.core.centrality.SimpleCentrality;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;


/**
 * Centrality of Cliques with TreeSet
 * @author Colin Riviere, Loris Benoit
 *
 */
public class CliqueTreeSet implements SimpleCentrality{
	
	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		CentralityResult centraliteNoeud = new CentralityResult();
		for(Node u : inGraph.getNodes()) {
			centraliteNoeud.add(0.0);
		}
		List<TreeSet<Integer>> listeClique = rechercheCliqueRecursive(inGraph);
		for( TreeSet<Integer> clique : listeClique ) {
			for( int node : clique ) {
				centraliteNoeud.set(node, (centraliteNoeud.get(node))+1);
			}
		}
		
		return centraliteNoeud;
	}
	
	
	/**
	 * Use Bouda's algorithm for find clique
	 * @param inGraph
	 * @return List of clique
	 */
	private List<TreeSet<Integer>> RechercheCliqueBouda( SimpleGraph inGraph ) {
		List<TreeSet<Integer>> listeClique = new ArrayList<TreeSet<Integer>>();
		int k = 2;
		List<TreeSet<Integer>> kClique  = getAllLink(inGraph);
	
		
		while (!kClique.isEmpty()){
			if (k != 2) {
				listeClique.addAll(kClique);
			}
			Set<TreeSet<Integer>> cliques1 = new HashSet<TreeSet<Integer>>();
			
			for(Pair<TreeSet<Integer>, TreeSet<Integer>> cliq : combinaisions(kClique)) {
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
	 * Generate all combination of 2 in KClique
	 * @param kClique
	 * @return combination
	 */
	private List<Pair<TreeSet<Integer>,TreeSet<Integer>>> combinaisions(List<TreeSet<Integer>> kClique){
		List<Pair<TreeSet<Integer>,TreeSet<Integer>>> sortie = new ArrayList<Pair<TreeSet<Integer>,TreeSet<Integer>>>();
		for(int i=0;i<kClique.size()-1;i++) {
			for(int j=i+1;j<kClique.size();j++) {
				sortie.add(new Pair<TreeSet<Integer>,TreeSet<Integer>>(kClique.get(i),kClique.get(j)));
			}
		}
		
		return sortie;
	}
	
	/**
	 * Symmetrical difference of u and v
	 * @param u
	 * @param v
	 * @return Symmetrical difference
	 */
	private List<Integer> diffSyme(TreeSet<Integer> u, TreeSet<Integer> v){
		TreeSet<Integer> same = new TreeSet<Integer>(u);
		same.retainAll(v);
		List<Integer> diff = new ArrayList<Integer>(u);
		diff.addAll(v);
		diff.removeAll(same);
		return diff;
	}
	
	/**
	 * Union of u and v
	 * @param u
	 * @param v
	 * @return Union
	 */
	private TreeSet<Integer> union(TreeSet<Integer> u, TreeSet<Integer> v){
		TreeSet<Integer> s = new TreeSet<Integer>();
		s.addAll(u);
		s.addAll(v);
		return s;
	}
	
	/**
	 * Return all Link of the graph as a list of list
	 * @param inGraph
	 * @return List of Clique
	 */
	private List<TreeSet<Integer>> getAllLink(SimpleGraph inGraph){
		List<TreeSet<Integer>> kClique = new ArrayList<TreeSet<Integer>>();
		Set<TreeSet<Integer>> kCliqueTmp = new HashSet<TreeSet<Integer>>();
		for(int i =0; i < inGraph.getNodeCount();i++) {
			List<Link> listeLinkNoeud = inGraph.getNodeLinks(inGraph.getNodeAt(i));
			for (int j=0 ; j< listeLinkNoeud.size();j++) {
				TreeSet<Integer> tempo = new TreeSet<Integer>();
				tempo.add(listeLinkNoeud.get(j).getDestinationIdentifier());
				tempo.add(listeLinkNoeud.get(j).getSourceIdentifier());
				kCliqueTmp.add(tempo);
			}
		}
		kClique.addAll(kCliqueTmp);
		return kClique;
	}
	
	
	/**
	 * Use Recursive's algorithm for find clique
	 * @param inGraph
	 * @return List of cliques
	 */
	public List<TreeSet<Integer>> rechercheCliqueRecursive(SimpleGraph inGraph){
		List<TreeSet<Integer>> listeClique = new ArrayList<TreeSet<Integer>>();
		TreeSet<Integer> NoeudUse = new TreeSet<Integer>();
		for(Node uN : inGraph.getNodes()) {
			int u = uN.getIdentifier();
			TreeSet<Integer> uVoisin = new TreeSet<Integer>();
			uVoisin = getVoisins(inGraph,u);
			uVoisin.removeAll(NoeudUse);
			if( uVoisin.size() >= 2 ) {
				TreeSet<Integer> cliqueTest = new TreeSet<Integer>();
				cliqueTest.add(u);
				rechercheCliqueRec(inGraph, listeClique, uVoisin, cliqueTest);
			}
			NoeudUse.add(u);
		}
		return listeClique;
	}
	
	/** 
	 * Function recursive of of recursive's algorithm
	 * @param inGraph
	 * @param listeClique
	 * @param uVoisin
	 * @param cliqueTest
	 */
	private void rechercheCliqueRec(SimpleGraph inGraph, List<TreeSet<Integer>> listeClique, TreeSet<Integer> uVoisin, TreeSet<Integer> cliqueTest) {
		
		if( cliqueTest.size() >= 3 ) {
			TreeSet<Integer> toAdd = new TreeSet<Integer>(cliqueTest);
			if( !listeClique.contains(toAdd) ) {
				listeClique.add(toAdd);
			}
		}
	
		for(int w : uVoisin ) {
			TreeSet<Integer> wVoisin = new TreeSet<Integer>();
			wVoisin = getVoisins(inGraph,w);
			TreeSet<Integer> wTemp = new TreeSet<Integer>();
		
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
	 * List of neighbour of u
	 * @param inGraph
	 * @param u
	 * @return List of neighbour
	 */
	public TreeSet<Integer> getVoisins(SimpleGraph inGraph, int u){
		TreeSet<Integer> Voisins = new TreeSet<Integer>();
		for (Link l : inGraph.getNodeLinks(inGraph.getNodeAt(u))) {
			if ( l.getDestinationIdentifier() == u) Voisins.add(l.getSourceIdentifier());
			else Voisins.add(l.getDestinationIdentifier()); 
		}
		
		return Voisins;
	}

}
