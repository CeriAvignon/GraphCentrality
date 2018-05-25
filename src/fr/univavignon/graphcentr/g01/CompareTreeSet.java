package fr.univavignon.graphcentr.g01;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Comparator in order to comparate two TreeSet<Integer>
 * @author Colin Riviere
 *
 */
public class CompareTreeSet implements Comparator<TreeSet<Integer>> {
	
	@Override
	public int compare(TreeSet<Integer> a,TreeSet<Integer> b) {
		Iterator<Integer> itA = a.iterator();
		Iterator<Integer> itB = b.iterator();
		while(itA.hasNext() && itB.hasNext()) {
			
			int c = itA.next();
			int d = itB.next();
			
			
			if ( c > d) {
				return 1;
			}else if ( c < d) {
				return -1;
			}
		}
		
		
		if (!itA.hasNext() && !itB.hasNext()) return 0;
		else if (!itA.hasNext()) return -1;
		else return 1;
	}
}
