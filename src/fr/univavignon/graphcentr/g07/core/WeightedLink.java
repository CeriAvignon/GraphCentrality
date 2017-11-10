package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Link with a weight
 */
public class WeightedLink extends Link implements AbstractWeightedInformation
{
	/**
	 * Link's weight
	 */
	protected double Weight;
	
	/**
	 * Default constructor
	 */
	public WeightedLink()
	{
		super();
	}

	/**
	 * Default constructor, link both nodes
	 * @param InSourceNode
	 * @param InDestinationNode
	 */
	public WeightedLink(Node InSourceNode, Node InDestinationNode) 
	{
		super(InSourceNode, InDestinationNode);
		Weight = 0;
	}

	/**
	 * Set link weight
	 * @param inWeight
	 */
	@Override
	public void setWeight(double inWeight) 
	{
		Weight = inWeight;
		
	}

	/**
	 * Returns link's weight
	 * @return Weight
	 */
	@Override
	public double getWeight() 
	{
		return Weight;
	}
	
}
