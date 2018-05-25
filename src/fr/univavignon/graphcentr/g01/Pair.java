package fr.univavignon.graphcentr.g01;

/**
 * Pair of two item
 * @author Rivière Colin
 *
 * @param <Type1> type of First element
 * @param <Type2> type of Second element
 */
public class Pair<Type1 , Type2> {

	/**
	 * First element
	 */
	public Type1 first;
	
	
	/**
	 * Second element
	 */
	public Type2 second;
	
	
	/**
	 * Constructor with 2 param
	 * @param firstElement
	 * @param SecondElement
	 */
	public Pair(Type1 firstElement, Type2 SecondElement) {
		first = firstElement;
		second = SecondElement;
	}
	
	/**
	 * Constructor with no param
	 */
	public Pair() {
		
	}
	
	public String toString() {
		return first+", "+second;
	}
}
