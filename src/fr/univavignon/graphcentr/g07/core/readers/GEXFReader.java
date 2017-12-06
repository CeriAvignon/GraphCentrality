package fr.univavignon.graphcentr.g07.core.readers;



import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.univavignon.graphcentr.g07.core.AbstractDirectedGraph;
import fr.univavignon.graphcentr.g07.core.AbstractGraph;
import fr.univavignon.graphcentr.g07.core.AbstractSimpleGraph;
import fr.univavignon.graphcentr.g07.core.Node;
import fr.univavignon.graphcentr.g07.core.SpatialNode;
import fr.univavignon.graphcentr.g07.core.Link;
import fr.univavignon.graphcentr.g07.core.WeightedLink;

/**
 * 
 * @author JackassDestroyer
 * Read a GEXF file and update a graph according to its type
 */
public class GEXFReader extends AbstractGraphFileReader
{
	/** */
	protected DocumentBuilderFactory factory;
	/** */
	protected DocumentBuilder builder;

	/** User-defined attribute for nodes */
	protected Vector<XMLNodeInformation> nodeInfos;
	/** User-defined attribute for links */
	protected Vector<XMLLinkInformation> linkInfos;
	/** User-defined attributes */
	protected XMLGraphAttributes Attributes;
	
	/** True if graph is weighted */
	private boolean bWeightedGraph = false;
	/** True if graph is spatial */
	private boolean bSpatialGraph = false;
	/** True if graph is directed */
	protected boolean bDirectedGraph = false;
	
	/** Attributes identifier in GEXF file */
	private static final String ATT_IDENTIFIER = "attributes";
	/** Node identifier in GEXF file */
	private static final String NODE_IDENTIFIER = "node";
	/** Nodes identifier in GEXF file */
	private static final String NODES_IDENTIFIER = "nodes";
	/** Edge identifier in GEXF file */
	private static final String EDGE_IDENTIFIER = "edge";
	/** Edges identifier in GEXF file */
	private static final String EDGES_IDENTIFIER = "edges";
	/** Graph identifier in GEXF file */
	private static final String GRAPH_IDENTIFIER = "graph";
	
	/**
	 * Default constructor
	 */
	public GEXFReader()
	{
		super();
		
		nodeInfos = new Vector<>();
		linkInfos = new Vector<>();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try 
		{
		    builder = factory.newDocumentBuilder();
		}
		catch (final ParserConfigurationException e) 
		{
		    e.printStackTrace();
		}
	}
	
	/**
	 * Read given file and update given graph
	 * @param inFileName File to read
	 * @param inGraph Graph to update
	 */
	public <GraphNodeType extends Node, GraphLinkType extends Link> 
	void updateFromFile(String inFileName, AbstractGraph<GraphNodeType, GraphLinkType> inGraph)
	{
		if(!openFile(inFileName))
			return;
		
		try 
		{
			bWeightedGraph = WeightedLink.class.isInstance(inGraph.getLinkClass().newInstance());
		} 
		catch (InstantiationException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IllegalAccessException e1) 
		{
			e1.printStackTrace();
		}
		
		try 
		{
			bSpatialGraph = SpatialNode.class.isInstance(inGraph.getNodeClass().newInstance());
		} 
		catch (InstantiationException e1) 
		{
			e1.printStackTrace();
		} 
		catch (IllegalAccessException e1) 
		{
			e1.printStackTrace();
		}
		
		bDirectedGraph = inGraph instanceof AbstractDirectedGraph<?, ?>;
				
		// Parse file
		Document readFile = null;
		try 
		{
			readFile = builder.parse(new File(inFileName));
		} 
		catch (SAXException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		// Extract user-defined attributes
		extractAttributes(readFile);
		// Parse nodes from file and extract XML infos
		parseNodes(readFile);
		// Parse links from file and extract XML infos
		parseLinks(readFile);
		// Create nodes from parsed XML infos
		createNodes(inGraph);
		// Create links from parsed XML infos
		createLinks(inGraph);
	}
	
	/**
	 * Extracts user-defined attributes from file
	 * @param inDocument XML Document
	 */
	protected void extractAttributes(Document inDocument)
	{
		Attributes = new XMLGraphAttributes(GraphFileType.GEXF);
		NodeList graphs = inDocument.getElementsByTagName(ATT_IDENTIFIER);
		
		for(int i = 0; i < graphs.getLength(); i++)
		{
			Attributes.extractAttributes(graphs.item(i));
		}
	}
	
	/**
	 * Search all nodes and create XMLNodeInformation
	 * @param inDocument XML Document
	 */
	protected void parseNodes(Document inDocument)
	{
		nodeInfos.clear();
		
		NodeList graphs = inDocument.getElementsByTagName(GRAPH_IDENTIFIER);
		if(graphs.getLength() <= 0)
			return;
		
		NodeList child = graphs.item(0).getChildNodes();
		org.w3c.dom.Node nodes = null;
		
		for(int i = 0; i < child.getLength(); i++)
		{
			org.w3c.dom.Node currentChild = child.item(i);
			if(currentChild.getNodeName().equalsIgnoreCase(NODES_IDENTIFIER))
				nodes = currentChild;
		}
		
		if(nodes == null)
			return;
		
		for(int i = 0; i < nodes.getChildNodes().getLength(); i++)
		{
			org.w3c.dom.Node currentNode = nodes.getChildNodes().item(i);
			if(currentNode.getNodeName().equalsIgnoreCase(NODE_IDENTIFIER))
				nodeInfos.add(new XMLNodeInformation(GraphFileType.GEXF, currentNode, Attributes));		
		}
	}
	
	/**
	 * Search all links and create XMLLinkInformation
	 * @param inFile
	 */
	protected void parseLinks(Document inFile)
	{
		NodeList graphs = inFile.getElementsByTagName(GRAPH_IDENTIFIER);
		if(graphs.getLength() <= 0)
			return;
		
		NodeList child = graphs.item(0).getChildNodes();
		org.w3c.dom.Node links = null;
		
		for(int i = 0; i < child.getLength(); i++)
		{
			org.w3c.dom.Node currentChild = child.item(i);
			if(currentChild.getNodeName().equalsIgnoreCase(EDGES_IDENTIFIER))
				links = currentChild;
		}
		
		// Add error ?
		if(links == null)
			return;
		
		for(int i = 0; i < links.getChildNodes().getLength(); i++)
		{
			org.w3c.dom.Node currentLink = links.getChildNodes().item(i);
			if(currentLink.getNodeName().equalsIgnoreCase(EDGE_IDENTIFIER))
				linkInfos.add(new XMLLinkInformation(GraphFileType.GEXF, currentLink, Attributes));			
		}
	}
	
	/**
	 * Create nodes in graph from XMLNodeInformation
	 * @param inGraph Graph to update
	 */
	protected <GraphNodeType extends Node, GraphLinkType extends Link> 
	void createNodes(AbstractGraph<GraphNodeType, GraphLinkType> inGraph)
	{
		for(XMLNodeInformation current : nodeInfos)
		{
			current.Node = inGraph.createNode();
			if(bSpatialGraph)
			{
				SpatialNode currentNode = (SpatialNode)current.Node;
				currentNode.setX(current.X);
				currentNode.setY(current.Y);
			}
		}
	}
	
	/**
	 * Create links in graph from XMLLinkInformation
	 * @param inGraph Graph to update
	 */
	protected <GraphNodeType extends Node, GraphLinkType extends Link> 
	void createLinks(AbstractGraph<GraphNodeType, GraphLinkType> inGraph)
	{
		for(XMLLinkInformation current : linkInfos)
		{
			Node sourceNode = null;
			Node destinationNode = null;
			
			for(XMLNodeInformation CurrentNode : nodeInfos)
			{
				if(CurrentNode.Identifier.equalsIgnoreCase(current.SourceIdentifier))
					sourceNode = CurrentNode.Node;
				
				if(CurrentNode.Identifier.equalsIgnoreCase(current.DestinationIdentifier))
					destinationNode = CurrentNode.Node;
			}
			
			// Add error ?
			if(sourceNode == null || destinationNode == null)
				continue;
			
			current.Link = inGraph.createLink(sourceNode.getIdentifier(), destinationNode.getIdentifier());
			
			if(bWeightedGraph)
			{
				WeightedLink currentLink = (WeightedLink)current.Link;
				currentLink.setWeight(current.Weight);
				
				if(!bDirectedGraph)
				{
					AbstractSimpleGraph<GraphNodeType, GraphLinkType> simpleGraph = (AbstractSimpleGraph<GraphNodeType, GraphLinkType>)inGraph;
					currentLink = (WeightedLink)simpleGraph.getLastBackLink();
					currentLink.setWeight(current.Weight);
				}
			}
		}
	}
}
