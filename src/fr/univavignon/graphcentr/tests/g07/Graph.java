package fr.univavignon.graphcentr.tests.g07;

import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.AbstractDirectedGraph;
import fr.univavignon.graphcentr.g07.core.AbstractGraph;
import fr.univavignon.graphcentr.g07.core.AbstractSimpleGraph;

import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SpatialGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SpatialWeightedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedSpatialGraph;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedSpatialWeightedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.DirectedWeightedGraph;

/**
 * 
 * @author Holstein Kelian
 *
 * @brief Class used to perform tests on graphs
 */
class Graph 
{

	/**
	 * Test on abstract graph class
	 * @param inGraph Graph to test
	 * @throws CoreException 
	 */
	static private <NodeType extends Node, LinkType extends Link>
	void abstractGraphTest(AbstractGraph<NodeType, LinkType> inGraph) throws CoreException
	{		
		// Get graph class name
		String className = inGraph.getClass().getName();
		
		// Test node creation
		NodeType createdNode = inGraph.createNode();
		
		if(inGraph.getNodeCount() != 1)
			throw new CoreException("createNode() / Node creation failed", className);
		if(createdNode == null)
			throw new CoreException("createNode() / Returned node is null", className);
		
		// Test node deletion by object
		inGraph.removeNode(createdNode);
		
		if(inGraph.getNodeCount() != 0)
			throw new CoreException("removeNode(NodeType) / Node deletion failed", className);
		
		// Test node deletion by ID
		createdNode = inGraph.createNode();
		inGraph.removeNode(0);
		
		if(inGraph.getNodeCount() != 0)
			throw new CoreException("removeNode(int) / Node deletion failed", className);
		
		// Test nodes ID with delete and add
		inGraph.createNode(); // 0
		inGraph.createNode(); // 1
		createdNode = inGraph.createNode(); // 2
		inGraph.removeNode(1);
		
		if(inGraph.getNodeIndex(createdNode) != 1)
			throw new CoreException("removeNode(NodeType or int) / Node's ID not properly updated", className);
		
		// Clear graph
		inGraph.removeNode(0);
		inGraph.removeNode(0);
		
		// Test link creation by object
		createdNode = inGraph.createNode();
		NodeType createNode2 = inGraph.createNode();
		LinkType link = inGraph.createLink(createdNode, createNode2);
		
		if(inGraph.getLinkCount() != 1)
			throw new CoreException("createLink(NodeType, NodeType) / Link creation failed", className);
		if(link == null)
			throw new CoreException("createLink(NodeType, NodeType) / Returned link is null", className);
		
		// Try to recreate same link
		inGraph.createLink(createdNode, createNode2);
		
		if(inGraph.getLinkCount() != 1)
			throw new CoreException("createLink(NodeType, NodeType) / Same link added twice", className);
		
		// Test link deletion by object 
		inGraph.removeLink(createdNode, createNode2);
		
		if(inGraph.getLinkCount() != 0)
			throw new CoreException("removeLink(NodeType, NodeType) / Link deletion failed", className);
		
		// Test link creation by ID
		link = inGraph.createLink(0, 1);
		
		if(inGraph.getLinkCount() != 1)
			throw new CoreException("createLink(int, int) / Link creation failed", className);
		if(link == null)
			throw new CoreException("createLink(int, int) / Returned link is null", className);
		
		// Test link deletion by ID
		inGraph.removeLink(0, 1);
		
		if(inGraph.getLinkCount() != 0)
			throw new CoreException("removeLink(int, int) / Link deletion failed", className);
		
		// Test link deletion by link object
		link = inGraph.createLink(0, 1);
		inGraph.removeLink(link);
		
		if(inGraph.getLinkCount() != 0)
			throw new CoreException("removeLink(LinkType) / Link deletion failed", className);
		
		inGraph.clear();
	}

	/**
	 * Test on abstract simple graph class
	 * @param inGraph Graph to test
	 * @throws CoreException
	 */
	static private <NodeType extends Node, LinkType extends Link>
	void abstractSimpleGraphTest(AbstractSimpleGraph<NodeType, LinkType> inGraph) throws CoreException
	{
		// First test abstract graph class methods
		abstractGraphTest(inGraph);
		
		// Get graph class name
		String className = inGraph.getClass().getName();
		
		// Test nodes degree
		inGraph.createNode();
		inGraph.createNode();
		inGraph.createNode();
		inGraph.createNode();
		
		inGraph.createLink(0, 1);
		inGraph.createLink(0, 2);
		inGraph.createLink(0, 3);
		
		inGraph.createLink(1, 2);
		
		inGraph.createLink(3, 1);
		
		// Test node degree
		if(inGraph.getNodeDegree(0) != 3 || inGraph.getNodeDegree(1) != 3
			|| inGraph.getNodeDegree(2) != 2 || inGraph.getNodeDegree(3) != 2)
			throw new CoreException("getNodeDegree(int) / Invalid degree", className);
		
		inGraph.clear();
	}
	
	/**
	 * Test on abstract directed graph class
	 * @param inGraph Graph to test
	 * @throws CoreException
	 */
	static private <NodeType extends Node, LinkType extends Link>
	void abstractDirectedGraphTest(AbstractDirectedGraph<NodeType, LinkType> inGraph) throws CoreException
	{
		// First test abstract graph class methods
		abstractGraphTest(inGraph);
		
		// Get graph class name
		String className = inGraph.getClass().getName();
		
		// Test nodes degree
		inGraph.createNode();
		inGraph.createNode();
		inGraph.createNode();
		inGraph.createNode();
		
		inGraph.createLink(0, 1);
		inGraph.createLink(0, 2);
		inGraph.createLink(0, 3);
		
		inGraph.createLink(1, 2);
		
		inGraph.createLink(3, 1);
		
		// Test outgoing degree
		if(inGraph.getOutgoingDegree(0) != 3 || inGraph.getOutgoingDegree(1) != 1
			|| inGraph.getOutgoingDegree(2) != 0 || inGraph.getOutgoingDegree(3) != 1)
			throw new CoreException("getOutgoingDegree(int) / Invalid degree", className);
		
		// Test incoming degree		
		if(inGraph.getIncomingDegree(0) != 0 || inGraph.getIncomingDegree(1) != 2
				|| inGraph.getIncomingDegree(2) != 2 || inGraph.getIncomingDegree(3) != 1)
				throw new CoreException("getIncomingDegree(int) / Invalid degree", className);
		
		inGraph.clear();
	}
	
	/**
	 * Execute all graph tests
	 */
	static public void execute()
	{
		// Assume tests are completed. If a CoreException is thrown this will be become false
		boolean testsCompleted = true;
		
		System.out.println("=============== Graph tests ===============");
		try
		{
			// Test each type of graph
			abstractSimpleGraphTest(new SimpleGraph());
			abstractSimpleGraphTest(new WeightedGraph());
			abstractSimpleGraphTest(new SpatialGraph());
			abstractSimpleGraphTest(new SpatialWeightedGraph());
			
			abstractDirectedGraphTest(new DirectedGraph());
			abstractDirectedGraphTest(new DirectedSpatialGraph());
			abstractDirectedGraphTest(new DirectedWeightedGraph());
			abstractDirectedGraphTest(new DirectedSpatialWeightedGraph());
		}
		catch(CoreException thrownException)
		{
			System.out.println(thrownException.getMessage());
			testsCompleted = false;
		}
		if(testsCompleted)
			System.out.println("========== Graph tests completed ==========");
		
		System.out.println("===========================================");
	}
}
