package fr.univavignon.graphcentr.g01;

/**
 * @author Rivière Colin
 *
 * @param <Type1>
 * @param <Type2>
 */
public class Pair<Type1 , Type2> {

	public Type1 first;
	public Type2 second;
	
	public Pair(Type1 u, Type2 v) {
		first = u;
		second = v;
	}
}
