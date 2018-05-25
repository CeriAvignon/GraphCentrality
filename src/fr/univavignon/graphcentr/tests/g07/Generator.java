package fr.univavignon.graphcentr.tests.g07;

import fr.univavignon.graphcentr.g07.core.generator.*;
import fr.univavignon.graphcentr.g07.core.graphs.SimpleGraph;

/**
 * 
 * @author Holstein Kelian
 *
 * Class used for generator test
 */
class Generator 
{
	/**
	 * Execute tests on generators
	 */
	static void execute()
	{
		@SuppressWarnings("unused")
		SimpleGraph newGraph = BarabasiAlbertGenerator.generate(500, 5);
		newGraph = ErdosRenyiGenerator.generate(500, 0.2);
	}
}
