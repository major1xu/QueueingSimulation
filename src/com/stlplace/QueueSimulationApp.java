package com.stlplace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class QueueSimulationApp {

	public static void main(String[] args) {     
		
        // deal with input
        System.out.println("Enter these parameters of the simulation:");
        System.out.println("The number of queue/server pairs:");
        int numberOfQueue = Integer.parseInt(consoleInput());
        Queue[] queues = new Queue[numberOfQueue];
        for(int hh=0; hh<numberOfQueue; hh++)
        {
        	queues[hh] = new Queue();
        }
                 
        System.out.println("The probability that a customer arrives in one tick (%):"); // 80
        int probabilityCustomerArrivesInOneTick = Integer.parseInt(consoleInput());
        
        System.out.println("The maximum duration of a transaction in ticks:");
        int maxDurationOfATransaction = Integer.parseInt(consoleInput());

        // the following seed number is no longer needed as I have the class for distribute random number
//        System.out.println("Enter a random number seed:");
//        int seed = Integer.parseInt(consoleInput());
        
        // A probabilistic simulation calls a pseudo-random number generator to determine if events occur at each tick of the simulation's clock.
        // tick and new customer enqueue (randomness) 
        // simulation: a new customer arrives (or not); join a shortest queue; teller transaction; 
        int tick = 0;
        DistributedRandomNumberGenerator drng = new DistributedRandomNumberGenerator();
	    drng.addNumber(0, 0.2d); // 20%
	    drng.addNumber(1, probabilityCustomerArrivesInOneTick/100.0d); // this is 80%
	    int testCount = 120;
	    HashMap<Integer, Double> test = new HashMap<>();
	    QueueUtils sApp = new QueueUtils();
	    
        // teller transaction time: 0 means ready to serve, it's a number between 1 and 12 when a person enQueue (evenly distributed), 
        // and reduce by 1 for each tick
	    // how to track server/teller, their transaction time?
	    int[] server = new int[4];
	    server[0]=0;
	    server[1]=0;
	    server[2]=0;
	    server[3]=0;
	    
	    int max_wait_time = 0;
	    int total_wait_time = 0;
	    int total_dequeued = 0;
	    for(tick=1; tick<=120; tick++)
        {
	        int random = drng.getDistributedRandomNumber();

        	//System.out.println("tick: " + tick + " randomNumber: " + random);
 	        test.put(random, (test.get(random) == null) ? (1d / testCount) : test.get(random) + 1d / testCount);
 	        if(random == 1)
 	        {
 	        	int shortestQueueIndex = sApp.getIndexOfShortestLines(queues);
 	        	queues[shortestQueueIndex].enQueue(tick);
 	        	
 	        	DistributedRandomNumberGenerator drng2 = new DistributedRandomNumberGenerator();
 	        	for(int ii=1; ii<=maxDurationOfATransaction; ii++)
 	        	{
 	        		drng2.addNumber(ii, 1.0d/maxDurationOfATransaction); 
 	        	}
 	        	int random2 = drng2.getDistributedRandomNumber();
 	        	server[shortestQueueIndex]= random2; // random number between 1 and maximum
 	        	
 	        	// other queues may count down by one (teller)
 	        // check if a teller is available?
 	        	// reduce teller waiting time by 1?
 	        	// if the teller time is zero and there is people in the queue, deQueue the first in line
 	        	for(int jj=0; jj<numberOfQueue && jj!=shortestQueueIndex; jj++)
 	        	{
 	        		if(server[jj]==0)
 	        		{
 	        			// The longest time a customer waited was 41 ticks
 	        			int wait_time = 0;
 	        			if (queues[jj].peek() != -1)
 	        			{
 	        				wait_time = tick - queues[jj].peek();
 	        				if(wait_time > max_wait_time)
 	        				{
 	        					max_wait_time = wait_time;
 	        				}
 	 	        			total_wait_time = total_wait_time + wait_time;
 	 	        			total_dequeued++;
 	 	        			queues[jj].deQueue();
 	        			}
 	        		}
 	        		else
 	        		{
 	        		    server[jj] = server[jj]-1;	
 	        		}
 	        	}
 	        }
 	        else
 	        {
 	        	// check if a teller is available?
 	        	// reduce teller waiting time by 1?
 	        	// if the teller time is zero and there is people in the queue, deQueue the first in line
 	        	for(int jj=0; jj<numberOfQueue; jj++)
 	        	{
 	        		if(server[jj]==0)
 	        		{
 	        			// The longest time a customer waited was 41 ticks
 	        			int wait_time = 0;
 	        			if (queues[jj].peek() != -1)
 	        			{
 	        				wait_time = tick - queues[jj].peek();
 	        				if(wait_time > max_wait_time)
 	        				{
 	        					max_wait_time = wait_time;
 	        				}
 	 	        			total_wait_time = total_wait_time + wait_time;
 	 	        			total_dequeued++;
 	 	        			queues[jj].deQueue();
 	        			}
 	        		}
 	        		else
 	        		{
 	        		    server[jj] = server[jj]-1;	
 	        		}
 	        	}
 	        } // end of else
 	        
 	        // print out the stats for that tick
 	        /* 118      1  79 82 91 96 100 102 109 114 116 
           				6  80 87 88 97 101 103 110 115 
           				3  90 92 94 98 104 107 111 112 
          			   12  93 95 99 105 108 113 118
 	         */
 	        
 	        for(int kk=0; kk<numberOfQueue; kk++)
 	        {
 	        	if(kk==0)
 	        	{
 	        		if(tick>=100)
 	        		{
 	        		    System.out.print(tick + "    "); // fill 1 or 2 spaces for the tick, total 3 spaces
 	        		}
 	        		else if(tick>=10)
 	        		{
 	        			System.out.print(" " + tick + "    ");
 	        		}
 	        		else
 	        		{
 	        			System.out.print("  " + tick + "    ");
 	        		}		
 	        	}
 	        	else
 	        	{
 	        		System.out.print("   " + "    ");
 	        	}
 	        	System.out.print(server[kk]+" ");
 	        	queues[kk].printQueue();
 	        }
 	        
        } // end of loop (tick)
	    // Distribution of 0 and 1 (no customer; a customer arrives at each tick)
	    //System.out.println(test.toString());
        
	    // stats
	    System.out.println(total_dequeued + " customers waited an average of " + (double)(total_wait_time/total_dequeued) + " ticks.");
	    
	    //The longest time a customer waited was 41 ticks
	    System.out.println("The longest time a customer waited was " + max_wait_time + " ticks");
	      
	    int countRemainingInQueues = 0;
	    for(int kk=0; kk<numberOfQueue; kk++)
	    {
	    	countRemainingInQueues = countRemainingInQueues + queues[kk].lengthOfQueue();
	    }
	    System.out.println(countRemainingInQueues + " customers remain in the lines");
	    
    }

	// http://www.mkyong.com/java/how-to-read-input-from-console-java/
	private static String consoleInput() {

		try{
		    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    String s = bufferRead.readLine();
		      
		    System.out.println(s);
		    
			return s;
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}
