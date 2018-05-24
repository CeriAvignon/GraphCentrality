package fr.univavignon.graphcentr.g01;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
	
	/**
	 * First iterator for the function "combinaisions"
	 */
	private Iterator<TreeSet<Integer>> itCombi1;
	
	/**
	 * Second iterator for the function "combinaisions"
	 */
	private Iterator<TreeSet<Integer>> itCombi2;
	
	/**
	 * Value of itCombi1
	 */
	private TreeSet<Integer> nCombi1;
	
	@SuppressWarnings("unused")
	@Override
	public CentralityResult evaluate(SimpleGraph inGraph) {
		CentralityResult centraliteNoeud = new CentralityResult();
		for(Node u : inGraph.getNodes()) {
			centraliteNoeud.add(0.0);
		}
		TreeSet<TreeSet<Integer>> listeClique = rechercheCliqueRecursive(inGraph);
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
	@SuppressWarnings("unused")
	private TreeSet<TreeSet<Integer>> rechercheCliqueBouda( SimpleGraph inGraph ) {
		TreeSet<TreeSet<Integer>> listeClique = new TreeSet<TreeSet<Integer>>(new CompareTreeSet());
		int k = 2;
		TreeSet<TreeSet<Integer>> kClique  = getAllLink(inGraph);
		
		
		while (!kClique.isEmpty()){
			
			if (k != 2) {
				listeClique.addAll(kClique);
			}
			TreeSet<TreeSet<Integer>> cliques1 = new TreeSet<TreeSet<Integer>>(new CompareTreeSet());
			
			
			itCombi1 = kClique.iterator();
			itCombi2 = kClique.iterator();
			nCombi1 = itCombi1.next();
			itCombi2.next();
			
			Pair<TreeSet<Integer>, TreeSet<Integer>> cliq = combinaisions(kClique);
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
	 * Use Bouda's algorithm for find clique. 
	 * Unappreciated
	 * @param inGraph
	 * @return List of clique
	 */
	@SuppressWarnings("unused")
	private TreeSet<TreeSet<Integer>> rechercheCliqueBoudaWithTab( SimpleGraph inGraph ) {
		TreeSet<TreeSet<Integer>> listeClique = new TreeSet<TreeSet<Integer>>(new CompareTreeSet());
		int k = 2;
		TreeSet<TreeSet<Integer>> kClique  = getAllLink(inGraph);
		
		
		while (!kClique.isEmpty()){
			
			if (k != 2) {
				listeClique.addAll(kClique);
			}
			TreeSet<TreeSet<Integer>> cliques1 = new TreeSet<TreeSet<Integer>>(new CompareTreeSet());
			
			for(Pair<TreeSet<Integer>, TreeSet<Integer>> cliq : combinaisionsWithTab(kClique)) {
				
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
	private Pair<TreeSet<Integer>,TreeSet<Integer>> combinaisions(TreeSet<TreeSet<Integer>> kClique){
		
		if(!itCombi2.hasNext() && !itCombi1.hasNext()) return null;
		
		Pair<TreeSet<Integer>,TreeSet<Integer>> sortie=new Pair<TreeSet<Integer>,TreeSet<Integer>>(nCombi1,itCombi2.next());
		
		if(!itCombi2.hasNext()) {
			nCombi1 = itCombi1.next();
			itCombi2 = kClique.iterator();
			while(itCombi2.next() != nCombi1);
		}
		
		return sortie;
	}
	
	
	
	/**
	 * Generate all combination of 2 in KClique ( stack all combination in a list ) .
	 * Unappreciated
	 * @param kClique
	 * @return combination
	 */
	@SuppressWarnings("unused")
	private List<Pair<TreeSet<Integer>,TreeSet<Integer>>> combinaisionsWithTab(TreeSet<TreeSet<Integer>> kClique){
		List<Pair<TreeSet<Integer>,TreeSet<Integer>>> sortie = new ArrayList<Pair<TreeSet<Integer>,TreeSet<Integer>>>();
		
		Iterator<TreeSet<Integer>> it1 = kClique.iterator();
		
		
		
		while(it1.hasNext()) {
			
			TreeSet<Integer> n1 = it1.next();
			Iterator<TreeSet<Integer>> it2 = kClique.iterator();
			while(it2.next() != n1); // No method for copy Iterator need to go at the good indexs
			while(it2.hasNext()) {
				sortie.add(new Pair<TreeSet<Integer>,TreeSet<Integer>>(n1,it2.next()));
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
	private TreeSet<TreeSet<Integer>> getAllLink(SimpleGraph inGraph){
		TreeSet<TreeSet<Integer>> kClique = new TreeSet<TreeSet<Integer>>(new CompareTreeSet());
		for(int i =0; i < inGraph.getNodeCount();i++) {
			List<Link> listeLinkNoeud = inGraph.getNodeLinks(inGraph.getNodeAt(i));
			for (int j=0 ; j< listeLinkNoeud.size();j++) {
				TreeSet<Integer> tempo = new TreeSet<Integer>();
				tempo.add(listeLinkNoeud.get(j).getDestinationIdentifier());
				tempo.add(listeLinkNoeud.get(j).getSourceIdentifier());
				kClique.add(tempo);
			}
		}
		return kClique;
	}
	
	
	/**
	 * Use Recursive's algorithm for find clique
	 * @param inGraph
	 * @return List of cliques
	 */
	@SuppressWarnings("unused")
	public TreeSet<TreeSet<Integer>> rechercheCliqueRecursive(SimpleGraph inGraph){
		TreeSet<TreeSet<Integer>> listeClique = new TreeSet<TreeSet<Integer>>(new CompareTreeSet());
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
	private void rechercheCliqueRec(SimpleGraph inGraph, TreeSet<TreeSet<Integer>> listeClique, TreeSet<Integer> uVoisin, TreeSet<Integer> cliqueTest) {
		
		if( cliqueTest.size() >= 3 ) {
			TreeSet<Integer> toAdd = new TreeSet<Integer>(cliqueTest);
			listeClique.add(toAdd);
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
	 * List of neighbors of u
	 * @param inGraph
	 * @param u
	 * @return List of neighbors
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
