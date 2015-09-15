package com.stlplace;
import java.util.ArrayList;


public class Queue {
    ArrayList<Integer> customerArrvialTime = new ArrayList<Integer>(0);
    
    public void enQueue(Integer arrivalTime)
    {
    	customerArrvialTime.add(arrivalTime);
    }
    
    public void deQueue()
    {
    	/*
    	if(customerArrvialTime.size()>1)
    	{
    		int count = 0;
    		for(count = 1; count < customerArrvialTime.size(); count++ )
    		{
    			customerArrvialTime.set( count,customerArrvialTime.get(count+1) );
    		}
    	}
    	*/
    	customerArrvialTime.remove(0);
    }
    
    public void printQueue()
    {
    	for(Integer arrivalTime : this.customerArrvialTime )
		{
    		System.out.print(arrivalTime + " ");
		}
    	System.out.print("\n");
    }
    
    public int lengthOfQueue()
    {
    	return customerArrvialTime.size();
    }
}
