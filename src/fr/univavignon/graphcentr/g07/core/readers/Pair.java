package fr.univavignon.graphcentr.g07.core.readers;

/**
 * 
 * @author JackassDestroyer
 * A pair of values used to avoid multiple class creation
 * @param <FirstValueType>
 * @param <SecondValueType>
 */
class Pair <FirstValueType, SecondValueType>
{
	/**
	 * First value
	 */
	public FirstValueType FirstValue;
	/**
	 * Second value
	 */
	public SecondValueType SecondValue;
	
	/**
	 * Default constructor
	 */
	public Pair()
	{
	}
	
	/**
	 * Initialise both values
	 * @param inFirstValue
	 * @param inSecondValue
	 */
	public Pair(FirstValueType inFirstValue, SecondValueType inSecondValue)
	{
		FirstValue = inFirstValue;
		SecondValue = inSecondValue;
	}
}
