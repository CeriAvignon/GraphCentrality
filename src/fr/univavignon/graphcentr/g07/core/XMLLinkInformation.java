package fr.univavignon.graphcentr.g07.core;

/**
 * 
 * @author JackassDestroyer
 * XML-extracted information for links
 */
class XMLLinkInformation 
{
	/** Concerned link */
	public AbstractLink<?> Link;
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
			org.w3c.dom.Node Id = inNode.getAttributes().getNamedItem("id");
			org.w3c.dom.Node SourceId = inNode.getAttributes().getNamedItem("source");
			org.w3c.dom.Node DestinationId = inNode.getAttributes().getNamedItem("target");
			org.w3c.dom.Node ReadWeight = inNode.getAttributes().getNamedItem("weight");
			
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
				if(CurrentNode.getNodeName().equalsIgnoreCase("attvalues"))
				{
					Attributes = CurrentNode.getChildNodes();
					break;
				}
			}
			
			if(Weight == 0)
			{
				String WeightValue = inAttributes.getNodeAttribute(Attributes, "weight");
				if(WeightValue != "")
					Weight = Double.parseDouble(WeightValue);
			}
		}
		else if(Type == GraphFileType.GraphML)
		{
			org.w3c.dom.Node Id = inNode.getAttributes().getNamedItem("id");
			org.w3c.dom.Node SourceId = inNode.getAttributes().getNamedItem("source");
			org.w3c.dom.Node DestinationId = inNode.getAttributes().getNamedItem("target");
			
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
				String WeightValue = inAttributes.getNodeAttribute(Attributes, "weight");
				if(WeightValue != "")
					Weight = Double.parseDouble(WeightValue);
			}
		}
	}
}
