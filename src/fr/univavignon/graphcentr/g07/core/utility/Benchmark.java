package fr.univavignon.graphcentr.g07.core.utility;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * 
 * @author Kelian Holstein
 *
 * Class used to do benchmark
 */
public class Benchmark 
{
	/** Benchmark snapshots */
	static private ArrayList<BenchmarkSnapshot> snapshots = new ArrayList<BenchmarkSnapshot>();
	
	/** Full execution time of program (between start and stop) */
	static private BenchmarkSnapshot fullExecution;
	
	/** Current snapshot */
	static private BenchmarkSnapshot currentSnapshot;
	
	/** */
	static private final String PRINT_TABLE_FIRST_COLUMN = "Name";
	/** */
	static private final String PRINT_TABLE_SECOND_COLUMN = "Ms";
	/** */
	static private final String PRINT_TABLE_THIRD_COLUMN = "Iteration count";
	
	/**
	 * Starts benchmark 
	 */
	public static void start()
	{
		TimeSnapshotSaver.start();
		snapshots.clear();
		fullExecution = new BenchmarkSnapshot();
		currentSnapshot = null;
	}
	
	/**
	 * Stops benchmark
	 */
	public static void stop()
	{		
		TimeSnapshotSaver.addSnapshot();
		if(currentSnapshot != null)
		{
			currentSnapshot.time = TimeSnapshotSaver.getLastSnapshotTime();
		}
		TimeSnapshotSaver.stop();
		fullExecution.time = TimeSnapshotSaver.getEndTime();
	}
	
	/**
	 * Add a snapshot. A snapshot is a pair of Name and Time. The time is the elapsed
	 * time between last snapshot (or beginning) and the creation of this snapshot
	 * @param inSnapshotName Snapshot name
	 */
	public static void addSnapshot(String inSnapshotName)
	{
		TimeSnapshotSaver.addSnapshot();
		if(currentSnapshot != null)
		{
			currentSnapshot.time = TimeSnapshotSaver.getLastSnapshotTime();
		}
			
		currentSnapshot = new BenchmarkSnapshot();
		currentSnapshot.name = inSnapshotName;
		snapshots.add(currentSnapshot);
	}
	
	/**
	 * Add an iteration to current snapshot
	 */
	public static void addIteration()
	{
		if(snapshots.size() > 0)
			snapshots.get(snapshots.size() - 1).iterationCount += 1;
		fullExecution.iterationCount += 1;
	}
	
	/**
	 * Prints snapshots and their informations
	 */
	public static void printSnapshots()
	{
		System.out.println("--------------- Benchmark ---------------");
		if(snapshots.size() > 0)
		{
			System.out.println("Snapshots :");
		
			int longestNameSize = PRINT_TABLE_FIRST_COLUMN.length();
			for(BenchmarkSnapshot currentSnapshot : snapshots)
			{
				if(currentSnapshot.name.length() > longestNameSize)
					longestNameSize = currentSnapshot.name.length();
			}
			
			System.out.print(PRINT_TABLE_FIRST_COLUMN);
			for(int i = 0; i < longestNameSize - PRINT_TABLE_FIRST_COLUMN.length(); i++)
				System.out.print(" ");
			System.out.print(" |");
			
			System.out.print(PRINT_TABLE_SECOND_COLUMN + "\t\t |");
			System.out.println(PRINT_TABLE_THIRD_COLUMN);
			
			for(BenchmarkSnapshot currentSnapshot : snapshots)
			{
				System.out.print(currentSnapshot.name);
				for(int i = 0; i < longestNameSize - currentSnapshot.name.length(); i++)
					System.out.print(" ");
				
				System.out.println(" |" + TimeSnapshotSaver.toMilliseconds(currentSnapshot.time) + "ms\t |" + currentSnapshot.iterationCount);
			}
			System.out.println("");
		}
		
		System.out.println("Full execution time : "+TimeSnapshotSaver.toMilliseconds(fullExecution.time)+"ms");
		System.out.println("Total iteration count : "+fullExecution.iterationCount);
		
		System.out.println("------------ End of Benchmark -----------");
	}
	
	/**
	 * Save benchmark results to the given file
	 * @param inFileName File name
	 */
	public static void saveToFile(String inFileName)
	{
		try (PrintWriter file = new PrintWriter(inFileName)) 
		{
			file.println("--------------- Benchmark ---------------");
			if(snapshots.size() > 0)
			{
				file.println("Name Time(ms) Iteration_count");
				
				for(BenchmarkSnapshot currentSnapshot : snapshots)
				{
					file.println(currentSnapshot.name+" "+TimeSnapshotSaver.toMilliseconds(currentSnapshot.time)+" "+currentSnapshot.iterationCount);
				}
				
				file.println("");
			}
			file.println("Full execution time : "+TimeSnapshotSaver.toMilliseconds(fullExecution.time)+"ms");
			file.println("Total iteration count : "+fullExecution.iterationCount);
			
			file.println("------------ End of Benchmark -----------");
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
}
