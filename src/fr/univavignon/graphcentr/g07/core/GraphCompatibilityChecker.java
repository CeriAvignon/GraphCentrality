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
	boolean isCompatible(Graph<NodeType, LinkType> inGraph, Class<DesiredNodeType> DesiredNodeClass
			, Class<DesiredLinkType> DesiredLinkClass) 
	{					
		return GraphCompatibilityChecker.nodesAreCompatible(inGraph, DesiredNodeClass) && 
				GraphCompatibilityChecker.linksAreCompatible(inGraph, DesiredLinkClass);
	}
	
	/**
	 * Test if graph's nodes are compatible (inherits from) with the DesiredNodeClass
	 * @param inGraph Graph to test
	 * @param DesiredNodeClass
	 * @return If nodes are compatible
	 */
	static public <NodeType extends AbstractNode<?>, LinkType extends AbstractLink<?>, DesiredNodeType>
	boolean nodesAreCompatible(Graph<NodeType, LinkType> inGraph, Class<DesiredNodeType> DesiredNodeClass) 
	{				
		// First resolve class names to get template parameters classes
		inGraph.resolveClassNames();

		// Get node class from inGraph by using forName method
		@SuppressWarnings("rawtypes")
		Class NodeClass = null;
		try 
		{
			NodeClass = Class.forName(inGraph.getNodeTypeClassName());
		} catch (ClassNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		
		try 
		{
			if(!NodeClass.isInstance(DesiredNodeClass.newInstance()))
				return false;
			
		} catch (InstantiationException | IllegalAccessException e) 
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
	boolean linksAreCompatible(Graph<NodeType, LinkType> inGraph, Class<DesiredLinkType> DesiredLinkClass) 
	{				
		// First resolve class names to get template parameters classes
		inGraph.resolveClassNames();

		// Get node class from inGraph by using forName method
		@SuppressWarnings("rawtypes")
		Class LinkClass = null;
		try 
		{
			LinkClass = Class.forName(inGraph.getLinkTypeClassName());
		} catch (ClassNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		
		try 
		{
			if(!LinkClass.isInstance(DesiredLinkClass.newInstance()))
				return false;
			
		} catch (InstantiationException | IllegalAccessException e) 
		{
			e.printStackTrace();
		}
		
		return true;
	}
}
