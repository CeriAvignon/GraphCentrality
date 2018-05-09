package fr.univavignon.graphcentr.g07.core.readers;

import fr.univavignon.graphcentr.g07.core.Link;

/**
 * 
 * @author Holstein Kelian
 * 
 * @brief XML-extracted information for links
 */
class XMLLinkInformation
{
	/** Concerned link */
	public Link Link;
	/** Weight */
	public double Weight;
	/** ID */
	public String Identifier;
	
	/** Source node's ID */
	public String SourceIdentifier;
	/** Destination node's ID */
	public String DestinationIdentifier;
	/** File's type */
	public GraphFileType Type;
	
	/** XML Node identifier in XML files */
	private static final String NODE_IDENTIFIER = "id";
	/** Source node identifier in XML files */
	private static final String SOURCE_IDENTIFIER = "source";
	/** Target node identifier in XML files */
	private static final String TARGET_IDENTIFIER = "target";
	/** Weight user-defined attribute identifier in XML files */
	private static final String WEIGHT_IDENTIFIER = "weight";
	
	/** Attributes identifier in GEXF files */
	private static final String ATTRIBUTES_IDENTIFIER = "attvalues";

	
	/**
	 * Default constructor
	 * @param inType Type of file
	 * @param inNode XMLNode
	 * @param inAttributes Document user-defined attributes
	 */
	public XMLLinkInformation(GraphFileType inType, org.w3c.dom.Node inNode, XMLGraphAttributes inAttributes)
	{
		Type = inType;
		Link = null;
		Weight = 0.0;
		Identifier = "";
		SourceIdentifier = "";
		DestinationIdentifier = "";
		
		extractInformation(inNode, inAttributes);
	}
	
	/**
	 * Extracts informations from XMLNode by parsing it
	 * @param inNode XMLNode
	 * @param inAttributes Document user-defined attributes
	 */
	public void extractInformation(org.w3c.dom.Node inNode, XMLGraphAttributes inAttributes)
	{
		if(Type == GraphFileType.GEXF)
		{
			org.w3c.dom.Node Id = inNode.getAttributes().getNamedItem(NODE_IDENTIFIER);
			org.w3c.dom.Node SourceId = inNode.getAttributes().getNamedItem(SOURCE_IDENTIFIER);
			org.w3c.dom.Node DestinationId = inNode.getAttributes().getNamedItem(TARGET_IDENTIFIER);
			org.w3c.dom.Node ReadWeight = inNode.getAttributes().getNamedItem(WEIGHT_IDENTIFIER);
			
			if(SourceId != null && DestinationId != null)
			{
				SourceIdentifier = SourceId.getNodeValue();
				DestinationIdentifier = DestinationId.getNodeValue();
			}
			if(Id != null)
				Identifier = Id.getNodeValue();
			
			if(ReadWeight != null)
				Weight = Double.parseDouble(ReadWeight.getNodeValue());
			
			org.w3c.dom.NodeList Attributes = null;
			for(int i = 0; i < inNode.getChildNodes().getLength(); i++)
			{
				org.w3c.dom.Node CurrentNode = inNode.getChildNodes().item(i);
				if(CurrentNode.getNodeName().equalsIgnoreCase(ATTRIBUTES_IDENTIFIER))
				{
					Attributes = CurrentNode.getChildNodes();
					break;
				}
			}
			
			if(Weight == 0)
			{
				String WeightValue = inAttributes.getLinkAttribute(Attributes, WEIGHT_IDENTIFIER);
				if(WeightValue != "")
					Weight = Double.parseDouble(WeightValue);
			}
		}
		else if(Type == GraphFileType.GraphML)
		{
			org.w3c.dom.Node Id = inNode.getAttributes().getNamedItem(NODE_IDENTIFIER);
			org.w3c.dom.Node SourceId = inNode.getAttributes().getNamedItem(SOURCE_IDENTIFIER);
			org.w3c.dom.Node DestinationId = inNode.getAttributes().getNamedItem(TARGET_IDENTIFIER);
			
			if(SourceId != null && DestinationId != null)
			{
				SourceIdentifier = SourceId.getNodeValue();
				DestinationIdentifier = DestinationId.getNodeValue();
			}
			if(Id != null)
				Identifier = Id.getNodeValue();
			
			org.w3c.dom.NodeList Attributes = inNode.getChildNodes();
			
			if(Weight == 0)
			{
				String WeightValue = inAttributes.getLinkAttribute(Attributes, WEIGHT_IDENTIFIER);
				if(WeightValue != "")
					Weight = Double.parseDouble(WeightValue);
			}
		}
	}
}
