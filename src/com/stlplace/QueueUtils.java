package com.stlplace;

public class QueueUtils {
	// Consider a function that returns the index of the "shortest" line; 
	// it will examine both the lengths of the queues and the remaining transaction times. 
	// ??? how to deal with remaining transaction times ???
		public int getIndexOfShortestLines(Queue[] queues)
		{
			int lengthOfShortestQueue = queues[0].lengthOfQueue();
			int indexOfShortestQueue = 0;
			for(int index=1; index<queues.length; index++)
			{
			    int length = queues[index].lengthOfQueue();
			    // In the case of the multiple shortest queues, it will compare all the lengths, but finds the 1st shortest queue
			    if(length < lengthOfShortestQueue)
			    {
			    	length = lengthOfShortestQueue;
			    	indexOfShortestQueue = index;
			    }
			}
			return indexOfShortestQueue;
		}
		
	// Consider also a function that returns the total length of all the queues.
		public int getTotalLengthOfAllQueues(Queue[] queues)
		{
			int sum = 0;
			for(int index=0; index<queues.length; index++)
			{
			    sum = sum + queues[index].lengthOfQueue();
			    
			}
			return sum;
		}
}
