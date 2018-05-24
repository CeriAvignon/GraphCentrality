package fr.univavignon.graphcentr.tests;


import fr.univavignon.graphcentr.tests.g01.CliqueTest;
import fr.univavignon.graphcentr.tests.g01.CliqueTreeSetTest;
import fr.univavignon.graphcentr.tests.g01.LeverageDirectedTest;
import fr.univavignon.graphcentr.tests.g01.LeverageTest;
import fr.univavignon.graphcentr.tests.g07.CoreTest;

/**
 * 
 * @author Holstein Kelian
 *
 * @brief Class used to execute all library's test.
 */
public class Test 
{
	/**
	 * Execute all library's tests
	 */
	public static void execute()
	{
		// Core tests (G07)
		CoreTest.execute();
		
		// Tests on "centralitites"
		
		//test g01
		CliqueTest.execute();
		CliqueTreeSetTest.execute();
		LeverageTest.execute();
		LeverageDirectedTest.execute();
	}
}
