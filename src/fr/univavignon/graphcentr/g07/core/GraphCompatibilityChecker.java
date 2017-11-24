package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * Class used to test graph compatibility. Because of java type-erasure, it's impossible to override 
 * a templated method. This compatibility checker is a runtime-workaround to handle this.
 * A good example is in IncidenceMatrix, it requires a specialized method for oriented graphs. 
 */
public class GraphCompatibilityChecker 
{	

	/**
	 * Test if graph's nodes and links are compatible with another type of graph (pair of DesiredNodeClass/DesiredLinkClass)
	 * @param inGraph Graph to test
	 * @param DesiredNodeClass
	 * @param DesiredLinkClass
	 * @return If both nodes/links are compatible
	 */
	static public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>, DesiredNodeType, DesiredLinkType>
	boolean isCompatible(AbstractGraph<NodeType, LinkType> inGraph, Class<DesiredNodeType> DesiredNodeClass
			, Class<DesiredLinkType> DesiredLinkClass) 
	{					
		return GraphCompatibilityChecker.nodesAreCompatible(inGraph, DesiredNodeClass) && 
				GraphCompatibilityChecker.linksAreCompatible(inGraph, DesiredLinkClass);
	}
	
	/**
	 * Test if graph is directed
	 * @param inGraph Graph to test
	 * @return
	 */
	static public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>>
	boolean isDirected(AbstractGraph<NodeType, LinkType> inGraph) 
	{					
		return (inGraph instanceof DirectedGraph);
	}
	
	/**
	 * Test if graph's nodes are compatible (inherits from) with the DesiredNodeClass
	 * @param inGraph Graph to test
	 * @param DesiredNodeClass
	 * @return If nodes are compatible
	 */
	static public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>, DesiredNodeType>
	boolean nodesAreCompatible(AbstractGraph<NodeType, LinkType> inGraph, Class<DesiredNodeType> DesiredNodeClass) 
	{				

		// Get node class from inGraph by using forName method
		Class<NodeType> NodeClass = inGraph.getNodeClass();
		
		try 
		{
			if(!DesiredNodeClass.isInstance(NodeClass.newInstance()))
				return false;
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
			
		return true;
	}
	
	/**
	 * Test if graph's links are compatible (inherits from) with the DesiredLinkClass
	 * @param inGraph Graph to test
	 * @param DesiredLinkClass
	 * @return If links are compatible
	 */
	static public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>, DesiredLinkType>
	boolean linksAreCompatible(AbstractGraph<NodeType, LinkType> inGraph, Class<DesiredLinkType> DesiredLinkClass) 
	{				

		// Get node class from inGraph by using forName method
		Class<LinkType> LinkClass = inGraph.getLinkClass();
		
		try 
		{
			if(!DesiredLinkClass.isInstance(LinkClass.newInstance()))
				return false;
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		}
			
		return true;
	}
}
