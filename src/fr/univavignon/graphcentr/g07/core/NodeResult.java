package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Pair of node and value. Used in AlgorithmResult to associate a node and its value
 */
public class NodeResult 
{
	/**
	 * Acts as a key
	 */
	protected AbstractNode<?> node;
	/**
	 * 
	 */
	protected double value;
	
	/**
	 * Default constructor, initialise both node and value 
	 * @param inNode
	 * @param inValue
	 */
	public NodeResult(AbstractNode<?> inNode, double inValue)
	{
		node = inNode;
		value = inValue;
	}
	
	/**
	 * Returns node
	 * @return node
	 */
	public AbstractNode<?> getNode()
	{
		return node;
	}
	
	/**
	 * Returns value
	 * @return value
	 */
	public double getValue()
	{
		return value;
	}
	
	@Override
	public String toString()
	{
		return node+": "+value;
	}
	
}
