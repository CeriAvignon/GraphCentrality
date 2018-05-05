package fr.univavignon.graphcentr.g07.core.readers;

/**
 * 
 * @author JackassDestroyer
 * User-defined XML attribute. Contains a name / ID and a default value
 */
class XMLUserDefinedAttribute 
{
	/** Attribute's ID */
	public String AttributeIdentifier;
	/** Attribute's name */
	public String AttributeName;
	/** Default value */
	public String DefaultValue;
	/** File's type */
	public GraphFileType Type;
	
	
	/** XML Node identifier in XML files */
	private static final String NODE_IDENTIFIER = "id";
	
	/** Attribute name identifier in GEXF files */
	private static final String GEXF_ATT_NAME_IDENTIFIER = "title";
	/** Attribute default value identifier in GEXF files */
	private static final String GEXF_ATT_DEFAULT_VALUE_IDENTIFIER = "default";
	
	/** Attribute name identifier in GraphML files */
	private static final String GRAPHML_ATT_NAME_IDENTIFIER = "attr.name";
	
	/**
	 * Default constructor
	 * @param inType Type of file
	 * @param inNode
	 */
	public XMLUserDefinedAttribute(GraphFileType inType, org.w3c.dom.Node inNode)
	{
		Type = inType;
		AttributeIdentifier = "";
		AttributeName = "";
		DefaultValue = "";
		
		extractInformation(inNode);
	}
	
	/**
	 * Extracts informations from given XML node
	 * @param inNode XML node
	 */
	public void extractInformation(org.w3c.dom.Node inNode)
	{
		if(Type == GraphFileType.GEXF)
		{
			org.w3c.dom.Node Identifier = inNode.getAttributes().getNamedItem(NODE_IDENTIFIER);
			org.w3c.dom.Node Name = inNode.getAttributes().getNamedItem(GEXF_ATT_NAME_IDENTIFIER);
			org.w3c.dom.Node Default = null;
			for(int i = 0; i < inNode.getChildNodes().getLength(); i++)
			{
				org.w3c.dom.Node CurrentNode = inNode.getChildNodes().item(i);
				if(CurrentNode.getNodeName().equalsIgnoreCase(GEXF_ATT_DEFAULT_VALUE_IDENTIFIER))
				{
					Default = CurrentNode;
					break;
				}
			}
			
			if(Default != null)
				DefaultValue = Default.getTextContent();
			
			if(Name != null)
				AttributeName = Name.getNodeValue();
			
			if(Identifier != null)
				AttributeIdentifier = Identifier.getNodeValue();
		}
		if(Type == GraphFileType.GraphML)
		{
			org.w3c.dom.Node Identifier = inNode.getAttributes().getNamedItem(NODE_IDENTIFIER);
			org.w3c.dom.Node Name = inNode.getAttributes().getNamedItem(GRAPHML_ATT_NAME_IDENTIFIER);
						
			if(Identifier != null)
				AttributeIdentifier = Identifier.getNodeValue();
			
			if(Name != null)
				AttributeName = Name.getNodeValue();
			
			DefaultValue = inNode.getTextContent();
		}
		
	}
}

