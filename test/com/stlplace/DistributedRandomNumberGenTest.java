package com.stlplace;

import java.util.HashMap;

import org.junit.Test;

public class DistributedRandomNumberGenTest {
	
	@Test 
	public void testQueue() {
		DistributedRandomNumberGenerator drng = new DistributedRandomNumberGenerator();
	    drng.addNumber(0, 0.2d);
	    drng.addNumber(1, 0.8d);
	
	    int testCount = 120;
	
	    HashMap<Integer, Double> test = new HashMap<>();
	
	    for (int i = 0; i < testCount; i++) {
	        int random = drng.getDistributedRandomNumber();
	        test.put(random, (test.get(random) == null) ? (1d / testCount) : test.get(random) + 1d / testCount);
	    }
	
	    System.out.println(test.toString());
	}

}
