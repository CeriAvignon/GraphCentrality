package fr.univavignon.graphcentr.tests.g03;

import fr.univavignon.graphcentr.g03.EigenVectorColtTest;
import fr.univavignon.graphcentr.tests.g07.CoreTest;

/**
 * 
 * @author Holstein Kelian
 *
 * @brief Class used to execute all library's test.
 */
public class TestColt 
{
	/**
	 * Execute all library's tests
	 */
	public static void execute()
	{
		// Core tests (G07)
		CoreTest.execute();
		
		// Tests on "centralitites"
		EigenVectorColtTest.execute();
	}
}