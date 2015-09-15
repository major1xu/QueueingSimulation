package com.stlplace;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

	@Test
	public void testQueue() {
		
		Queue aQueue = new Queue();
		aQueue.enQueue(new Integer(1));
		aQueue.enQueue(new Integer(2));
		aQueue.printQueue();
		aQueue.deQueue();
		aQueue.printQueue();
	}
}
