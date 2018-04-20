package fr.univavignon.graphcentr.g07.core.utility;

import java.util.ArrayList;

/**
 * 
 * @author Kelian Holstein
 *
 */
class TimeSnapshotSaver 
{
	/** Start time */
	static private long startTime;
	/** End time */
	static private long endTime;
	
	/** Last snapshot time */
	static private long lastSnapshotTime;
	
	/** Registered snapshots */
	static private ArrayList<Long> snapshots = new ArrayList<Long>();
	
	/**
	 * Starts clock and clear snapshots
	 */
	public static void start()
	{
		startTime = System.nanoTime();
		lastSnapshotTime = startTime;
		snapshots.clear();
	}
	
	/**
	 * Adds a snapshot. Registered time is the difference between the last snapshot
	 * and registered one.
	 */
	public static void addSnapshot()
	{
		long currentTime = System.nanoTime();
		snapshots.add(currentTime - lastSnapshotTime);
		lastSnapshotTime = currentTime;
	}
	
	/**
	 * Returns last snapshot's time
	 * @return Last snapshot's time
	 */
	public static long getLastSnapshotTime()
	{
		return snapshots.get(snapshots.size() - 1);
	}
	
	/**
	 * Returns end time
	 * @return End time
	 */
	public static long getEndTime()
	{
		return endTime;
	}
	
	/**
	 * Stops clock (same as addSnapshot())
	 */
	public static void stop()
	{
		endTime = System.nanoTime() - startTime;
	}
	
	/**
	 * Returns all snapshots
	 * @return Snapshots array
	 */
	public static ArrayList<Long> getSnapshots()
	{
		return snapshots;
	}
	
	/**
	 * Converts nanoseconds to seconds
	 * @param inNano Nano time
	 * @return Seconds
	 */
	public static double toSeconds(long inNano)
	{
		return (double)inNano / 1000000000.0;
	}
	
	/**
	 * Converts nanoseconds to milliseconds
	 * @param inNano Nano time
	 * @return Milliseconds
	 */
	public static double toMilliseconds(long inNano)
	{
		return (double)inNano / 1000000.0;
	}
}
