package fr.univavignon.graphcentr.g07.core;

/**
 * @author Holstein Kelian
 * 
 * @brief Link between two nodes
 */
public class Link
{	
	/** Source node id */
	private int sourceIdentifier = -1;
	/** Destination node id */
	private int destinationIdentifier = -1;
	
	/**
	 * Set source node id
	 * @param inID
	 */
	public void setSourceIdentifier(int inID)
	{
		sourceIdentifier = inID;
	}
	/**
	 * Set destination node id
	 * @param inID
	 */
	public void setDestinationIdentifier(int inID)
	{
		destinationIdentifier = inID;
	}
	
	/**
	 * Returns source node id
	 * @return Source node id
	 */
	public int getSourceIdentifier()
	{
		return sourceIdentifier;
	}
	/**
	 * Returns destination node id
	 * @return Destination node id
	 */
	public int getDestinationIdentifier()
	{
		return destinationIdentifier;
	}
	
	@Override
	public String toString()
	{
		return "" + sourceIdentifier + " -> " + destinationIdentifier;
	}
}
