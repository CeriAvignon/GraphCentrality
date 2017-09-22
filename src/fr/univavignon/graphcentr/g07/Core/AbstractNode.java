package Core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Abstract class of graph nodes. Acts as a container of links.
 *  Note : Given links are not checked, you have to handle null-cases
 * @param <LinkType> Which type of link it contains
 */
public abstract class AbstractNode<LinkType>
{
	/** Links to other nodes */
	protected Vector<LinkType> Links;
	
	/**
	 * Default constructor
	 */
	public AbstractNode()
	{
		Links = new Vector<LinkType>();
	}
	
	/**
	 * Add given link to links
	 * @param InLink link to add
	 */
	public void AddLink(LinkType InLink)
	{
		Links.add(InLink);
	}
	
	/**
	 * Add given link to links
	 * @param InLink Link to remove
	 */
	public void RemoveLink(LinkType InLink)
	{
		Links.remove(InLink);
	}
	
	/**
	 * Returns links
	 * @return
	 */
	public Vector<LinkType> GetLinks() 
	{
		return Links;
	}
	
	/**
	 * Returns links count / node degree
	 * @return
	 */
	public int GetLinkCount()
	{
		return Links.size();
	}
}
