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
        ArrayList<Integer> serverTransactionTime = new ArrayList<Integer>(numberOfQueue); // init to zero
        
        System.out.println("The probability that a customer arrives in one tick (%):");
        int probabilityCustomerArrivesInOneTick = Integer.parseInt(consoleInput());
        
        System.out.println("The maximum duration of a transaction in ticks:");
        int maxDurationOfATransaction = Integer.parseInt(consoleInput());

//        System.out.println("Enter a random number seed:");
//        int seed = Integer.parseInt(consoleInput());
        
        // A probabilistic simulation calls a pseudo-random number generator to determine if events occur at each tick of the simulation's clock.
        // tick and new customer enqueue (randomness) 
        // simulation: a new customer arrives (or not); join a shortest queue; teller transaction; 
        int tick = 0;
        DistributedRandomNumberGenerator drng = new DistributedRandomNumberGenerator();
	    drng.addNumber(0, 0.2d);
	    drng.addNumber(1, probabilityCustomerArrivesInOneTick/100.0d);
	    int testCount = 120;
	    HashMap<Integer, Double> test = new HashMap<>();
	    QueueUtils sApp = new QueueUtils();
	    
	    for(tick=1; tick<=120; tick++)
        {
	        int random = drng.getDistributedRandomNumber();

        	//System.out.println("tick: " + tick + " randomNumber: " + random);
 	        test.put(random, (test.get(random) == null) ? (1d / testCount) : test.get(random) + 1d / testCount);
 	        if(random == 1)
 	        {
 	        	int shortestQueueIndex = sApp.getIndexOfShortestLines(queues);
 	        	queues[shortestQueueIndex].enQueue(tick);
 	        }
        }
	    System.out.println(test.toString());

        // teller transaction time: 0 means ready to serve, it should be a number between 1 and 12 after that, and
        // reduce by 1 for each tick
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
