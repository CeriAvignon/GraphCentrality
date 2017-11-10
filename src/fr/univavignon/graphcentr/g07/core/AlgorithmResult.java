package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Algorithm's output which associate a node to a value
 */
class AlgorithmResult 
{
	/** */
	protected Vector<NodeResult> result;
	/** Message to display before output */
	protected String message;
	
	/**
	 * Default constructor
	 */
	public AlgorithmResult()
	{
		result = new Vector<NodeResult>();
		message = "";
	}
	
	/**
	 * Set message
	 * @param inMessage
	 */
	public AlgorithmResult(String inMessage)
	{
		result = new Vector<NodeResult>();
		message = inMessage;
	}
	
	/**
	 * Push back a node with it associate value
	 * @param inNode
	 * @param inValue
	 */
	public void add(AbstractNode<?> inNode, double inValue)
	{
		NodeResult NewNode = new NodeResult(inNode, inValue);
		result.addElement(NewNode);
	}
	
	/**
	 * Set result
	 * @param inResult
	 */
	public void setResult(Vector<NodeResult> inResult)
	{
		result = inResult;
	}
	
	/**
	 * Returns result
	 * @return Results
	 */
	public Vector<NodeResult> getResult()
	{
		return result;
	}
	
	/**
	 * Returns discrete result (only values)
	 * @return Results as discrete values
	 */
	public Vector<Double> getDiscreteResult()
	{
		Vector<Double> discreteResult = new Vector<Double>();
		for(int i = 0; i < result.size(); i++)
			discreteResult.add(result.get(i).getValue());
		
		return discreteResult;
	}
	
	/**
	 * Print results
	 */
	public void print()
	{
		System.out.println("================= RESULT =================");
		if(!message.equals(""))
		{
			System.out.println(message);
			System.out.println("------------------------------------------");
		}
		for(int i = 0; i < result.size(); i++)
		{
			System.out.println(result.get(i));
		}
		System.out.println("==========================================");
	}
}
