package fr.univavignon.graphcentr.tests.g07;

/**
 * 
 * @author EthosReaper
 *
 * @brief Thrown exception when a core test have failed
 */
class CoreException extends Exception 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1941461807195054520L;
	
	/** Exception message */
	private String message;
	
	/**
	 * Default constructor, initialise output message
	 * @param inMessage Message to output
	 * @param inClassName Concerned class
	 */
	CoreException(String inMessage, String inClassName)
	{
		message = "["+inClassName+"] "+inMessage;
	}
	
	public String getMessage()
    {
        return message;
    }
}
