package fr.univavignon.graphcentr.g07.core;

import java.util.Vector;

/**
 * 
 * @author JackassDestroyer
 * Abstract class of graph nodes. Acts as a container of links.
 *  Note : Given links are not checked, you have to handle null-cases
 * @param <LinkType> Which type of link it contains
 */
abstract class AbstractNode<LinkType extends AbstractLink<?>>
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
	@SuppressWarnings("unchecked")
	public <T> void addLink(T InLink)
	{
		Links.add((LinkType)InLink);
	}
	
	/**
	 * Add given link to links
	 * @param InLink Link to remove
	 */
	@SuppressWarnings("unlikely-arg-type")
	public <T> void removeLink(T InLink)
	{
		Links.remove(InLink);
	}
	
	/**
	 * Returns links
	 * @return Links
	 */
	public Vector<LinkType> getLinks() 
	{
		return Links;
	}
	
	/**
	 * Test if node is linked with given node.
	 * Note : Parse the closest nodes
	 * @param inOther
	 * @return If node is linked with given node
	 */
	public boolean isLinkedWith(AbstractNode<LinkType> inOther)
	{
		for(LinkType CurrentLink : Links)
		{
			if(CurrentLink.DestinationNode == inOther)
				return true;
		}
		return false;
	}
	
	/**
	 * Returns links count / node degree
	 * @return Link count
	 */
	public int getLinkCount()
	{
		return Links.size();
	}
}
