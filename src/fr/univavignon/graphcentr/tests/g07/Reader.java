package fr.univavignon.graphcentr.tests.g07;

import fr.univavignon.graphcentr.g07.core.graphs.DirectedGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;
import fr.univavignon.graphcentr.g07.core.graphs.SpatialGraph;
import fr.univavignon.graphcentr.g07.core.graphs.WeightedGraph;
import fr.univavignon.graphcentr.g07.core.readers.EdgeListReader;
import fr.univavignon.graphcentr.g07.core.readers.GEXFReader;
import fr.univavignon.graphcentr.g07.core.readers.GraphMLReader;

/**
 * 
 * @author Holstein Kelian
 *
 * @brief Class used to perform tests on readers
 */
class Reader 
{
	
	/**
	 * Tests on edgeListReader
	 * @throws CoreException
	 */
	static private void edgeListReaderTest() throws CoreException
	{
		// Reader name
		String edgeListReaderName = EdgeListReader.class.getName();
		
		EdgeListReader reader = new EdgeListReader();
		
		String content = "0 1 2 3\n"
					   + "1 2\n"
					   + "4";
		
		SimpleGraph graph = new SimpleGraph();
		reader.updateFromString(content, ' ', graph);
		
		// Test if graph first node have 3 links
		if(graph.getNodeDegree(0) != 3)
			throw new CoreException("updateFromString/updateFromFile / Failed to read content", edgeListReaderName);
		
		// Then test if node 4 was created
		if(graph.getNodeCount() != 5)
			throw new CoreException("updateFromString/updateFromFile / Isolated node not added", edgeListReaderName);
	}
	
	/**
	 * Tests on GEXF file reader
	 * @throws CoreException
	 */
	static private void gexfFileReaderTest() throws CoreException
	{
		// Reader name
		String gexfReaderName = GEXFReader.class.getName();
		
		GEXFReader reader = new GEXFReader();
		
		String content ="<graph>\r\n" + 
				"\r\n" + 
				"	<attributes class=\"node\">\r\n" + 
				"\r\n" + 
				"		<attribute id=\"X\" title=\"X\">\r\n" + 
				"			<default>5.0</default>\r\n" + 
				"		</attribute>\r\n" + 
				"\r\n" + 
				"		<attribute id=\"Y\" title=\"Y\">\r\n" + 
				"			<default>10.0</default>\r\n" + 
				"		</attribute>\r\n" + 
				"	</attributes>\r\n" + 
				"	\r\n" + 
				"	<attributes class=\"edge\">\r\n" + 
				"		<attribute id=\"W\" title=\"Weight\">\r\n" + 
				"			<default>5.0</default>\r\n" + 
				"		</attribute>\r\n" + 
				"	</attributes>\r\n" + 
				"\r\n" + 
				"	<nodes>\r\n" + 
				"		<node id=\"0\" label=\"Hello\">\r\n" + 
				"			<attvalues>\r\n" + 
				"				<attvalue for=\"X\" value=\"50.0\" />\r\n" + 
				"				<attvalue for=\"Y\" value=\"50.0\" />\r\n" + 
				"			</attvalues>\r\n" + 
				"		</node>\r\n" + 
				"		<node id=\"1\">\r\n" + 
				"			<attvalues>\r\n" + 
				"				<attvalue for=\"X\" value=\"15.0\" />\r\n" + 
				"				<attvalue for=\"Y\" value=\"25.0\" />\r\n" + 
				"			</attvalues>\r\n" + 
				"		</node>\r\n" + 
				"		<node id=\"2\"/>\r\n" + 
				"		<node id=\"3\"/>\r\n" + 
				"		<node id=\"4\"/>\r\n" + 
				"	</nodes>\r\n" + 
				"	<edges>\r\n" + 
				"		<edge id=\"0\" source=\"0\" target=\"1\">\r\n" + 
				"			<attvalues>\r\n" + 
				"				<attvalue for=\"W\" value=\"2.0\" />\r\n" + 
				"			</attvalues>\r\n" + 
				"		</edge>\r\n" + 
				"		<edge id=\"1\" source=\"1\" target=\"2\"/>\r\n" + 
				"		<edge id=\"2\" source=\"0\" target=\"3\"/>\r\n" + 
				"		<edge id=\"3\" source=\"3\" target=\"4\"/>\r\n" + 
				"	</edges>\r\n" + 
				"</graph>";
		
		// Tests on simple graph
		SimpleGraph simpleGraph = new SimpleGraph();
		reader.updateFromString(content, simpleGraph);
		
		// Test if all nodes were created
		if(simpleGraph.getNodeCount() != 5)
			throw new CoreException("updateFromString/updateFromFile / Failed to read nodes", gexfReaderName);
		
		// Then test if graph's first node have 2 links
		if(simpleGraph.getNodeDegree(0) != 2)
			throw new CoreException("updateFromString/updateFromFile / Failed to read links", gexfReaderName);
		
		// Tests on directed graph
		DirectedGraph directedGraph = new DirectedGraph();
		reader.updateFromString(content, directedGraph);
		
		// Test if graph's have directed links
		if(directedGraph.getOutgoingDegree(1) != 1)
			throw new CoreException("updateFromString/updateFromFile / Failed to create directed links", gexfReaderName);
		
		// Tests on weighted graph
		WeightedGraph weightedGraph = new WeightedGraph();
		reader.updateFromString(content, weightedGraph);
		
		// Test if graph's have read link's weight
		if(weightedGraph.getNodeLinks(weightedGraph.getNodeAt(0)).get(0).getWeight() != 2.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read weight on links", gexfReaderName);
		
		// Test if links default weight value is good
		if(weightedGraph.getNodeLinks(weightedGraph.getNodeAt(2)).get(0).getWeight() != 5.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read default weight on links", gexfReaderName);
		
		// Tests on spatial graph
		SpatialGraph spatialGraph = new SpatialGraph();
		reader.updateFromString(content, spatialGraph);
		
		// Test if x and y value are read
		if(spatialGraph.getNodeAt(0).getX() != 50.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read X value on nodes", gexfReaderName);
		if(spatialGraph.getNodeAt(0).getY() != 50.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read Y value on nodes", gexfReaderName);
		
		// Test if x and y default values are read
		if(spatialGraph.getNodeAt(2).getX() != 5.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read X value on nodes", gexfReaderName);
		if(spatialGraph.getNodeAt(2).getY() != 10.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read Y value on nodes", gexfReaderName);

	}
	
	/**
	 * Tests on GEXF file reader
	 * @throws CoreException
	 */
	static private void graphMLFileReaderTest() throws CoreException
	{
		// Reader name
		String graphMLReaderName = GraphMLReader.class.getName();
		
		GraphMLReader reader = new GraphMLReader();
		
		String content ="<graphml>\r\n" + 
				"	<key id=\"X\" for=\"node\" attr.name=\"X\">5.0</key>\r\n" + 
				"	<key id=\"Y\" for=\"node\" attr.name=\"Y\">10.0</key>\r\n" + 
				"	<key id=\"weight\" for=\"edge\" attr.name=\"weight\">5.0</key>\r\n" + 
				"\r\n" + 
				"	<graph id=\"G\" edgedefault=\"undirected\">\r\n" + 
				"\r\n" + 
				"		<node id=\"n0\">\r\n" + 
				"			<data key=\"X\">50.0</data>\r\n" + 
				"			<data key=\"Y\">50.0</data>\r\n" + 
				"		</node>\r\n" + 
				"		<node id=\"n1\">\r\n" + 
				"			<data key=\"X\">15.0</data>\r\n" + 
				"			<data key=\"Y\">25.0</data>\r\n" + 
				"		</node>\r\n" + 
				"		<node id=\"n2\">\r\n" + 
				"		</node>\r\n" + 
				"		<node id=\"n3\">\r\n" + 
				"		</node>\r\n" + 
				"		<node id=\"n4\">\r\n" + 
				"		</node>\r\n" + 
				"\r\n" + 
				"		<edge source=\"n0\" target=\"n1\">\r\n" + 
				"			<data key=\"weight\">2.0</data>\r\n" + 
				"		</edge>\r\n" + 
				"		<edge source=\"n1\" target=\"n2\">\r\n" + 
				"		</edge>\r\n" + 
				"		<edge source=\"n0\" target=\"n3\">\r\n" + 
				"		</edge>\r\n" + 
				"		<edge source=\"n3\" target=\"n4\">\r\n" + 
				"		</edge>\r\n" + 
				"	</graph>\r\n" + 
				"</graphml>";
		
		// Tests on simple graph
		SimpleGraph simpleGraph = new SimpleGraph();
		reader.updateFromString(content, simpleGraph);
		
		// Test if all nodes were created
		if(simpleGraph.getNodeCount() != 5)
			throw new CoreException("updateFromString/updateFromFile / Failed to read nodes", graphMLReaderName);
		
		// Then test if graph's first node have 2 links
		if(simpleGraph.getNodeDegree(0) != 2)
			throw new CoreException("updateFromString/updateFromFile / Failed to read links", graphMLReaderName);
		
		// Tests on directed graph
		DirectedGraph directedGraph = new DirectedGraph();
		reader.updateFromString(content, directedGraph);
		
		// Test if graph's have directed links
		if(directedGraph.getOutgoingDegree(1) != 1)
			throw new CoreException("updateFromString/updateFromFile / Failed to create directed links", graphMLReaderName);
		
		// Tests on weighted graph
		WeightedGraph weightedGraph = new WeightedGraph();
		reader.updateFromString(content, weightedGraph);
		
		// Test if graph's have read link's weight
		if(weightedGraph.getNodeLinks(weightedGraph.getNodeAt(0)).get(0).getWeight() != 2.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read weight on links", graphMLReaderName);
		
		// Test if links default weight value is good
		if(weightedGraph.getNodeLinks(weightedGraph.getNodeAt(2)).get(0).getWeight() != 5.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read default weight on links", graphMLReaderName);
		
		// Tests on spatial graph
		SpatialGraph spatialGraph = new SpatialGraph();
		reader.updateFromString(content, spatialGraph);
		
		// Test if x and y value are read
		if(spatialGraph.getNodeAt(0).getX() != 50.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read X value on nodes", graphMLReaderName);
		if(spatialGraph.getNodeAt(0).getY() != 50.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read Y value on nodes", graphMLReaderName);
		
		// Test if x and y default values are read
		if(spatialGraph.getNodeAt(2).getX() != 5.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read X value on nodes", graphMLReaderName);
		if(spatialGraph.getNodeAt(2).getY() != 10.0)
			throw new CoreException("updateFromString/updateFromFile / Failed to read Y value on nodes", graphMLReaderName);

	}
	
	/**
	 * Execute all readers tests
	 */
	static public void execute()
	{
		// Assume tests are completed. If a CoreException is thrown this will be become false
		boolean testsCompleted = true;
		
		System.out.println("============== Reader tests ===============");
		try
		{
			// Test each type of readers
			edgeListReaderTest();
			gexfFileReaderTest();
			graphMLFileReaderTest();
		}
		catch(CoreException thrownException)
		{
			System.out.println(thrownException.getMessage());
			testsCompleted = false;
		}
		if(testsCompleted)
			System.out.println("========= Reader tests completed ==========");
		
		System.out.println("===========================================");
	}
}
