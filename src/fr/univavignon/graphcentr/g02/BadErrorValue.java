package fr.univavignon.graphcentr.g02;

/**
 * Custom bad value exception class
 */
public class BadErrorValue extends Exception
{
	@SuppressWarnings("javadoc")
	private static final long serialVersionUID = 1L;
	
	/**
	 * Exception constructor
	 * @param mes Exception message to be displayed
	 */
	public BadErrorValue(String mes)
	{
		super(mes);
	}
}