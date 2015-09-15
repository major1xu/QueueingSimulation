package com.stlplace;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueUtilsTest {
	
	@Test
	public void test_GetIndexOfShortestQueue() {
		// 79 82 91 96 100 102 109 114 116
		Queue aQueue = new Queue();
		aQueue.enQueue(new Integer(78));
		aQueue.enQueue(new Integer(82));
		aQueue.enQueue(new Integer(91));
		aQueue.enQueue(new Integer(96));
		aQueue.enQueue(new Integer(100));
		aQueue.enQueue(new Integer(102));
		aQueue.enQueue(new Integer(109));
		aQueue.enQueue(new Integer(114));
		aQueue.enQueue(new Integer(116));
		
		// 80 87 88 97 101 103 110 115
		Queue bQueue = new Queue();
		bQueue.enQueue(new Integer(80));
		bQueue.enQueue(new Integer(87));
		bQueue.enQueue(new Integer(88));
		bQueue.enQueue(new Integer(97));
		bQueue.enQueue(new Integer(101));
		bQueue.enQueue(new Integer(103));
		bQueue.enQueue(new Integer(110));
		bQueue.enQueue(new Integer(115));

		// 90 92 94 98 104 107 111 112
		Queue cQueue = new Queue();
		cQueue.enQueue(new Integer(90));
		cQueue.enQueue(new Integer(92));
		cQueue.enQueue(new Integer(94));
		cQueue.enQueue(new Integer(98));
		cQueue.enQueue(new Integer(104));
		cQueue.enQueue(new Integer(107));
		cQueue.enQueue(new Integer(111));
		cQueue.enQueue(new Integer(112));
		
		// 93 95 99 105 108 113 118
		Queue dQueue = new Queue();
		dQueue.enQueue(new Integer(93));
		dQueue.enQueue(new Integer(95));
		dQueue.enQueue(new Integer(99));
		dQueue.enQueue(new Integer(105));
		dQueue.enQueue(new Integer(108));
		dQueue.enQueue(new Integer(113));
		dQueue.enQueue(new Integer(118));
		
		Queue[] array = new Queue[4];
		array[0] = aQueue;
		array[1] = bQueue;
		array[2] = cQueue;
		array[3] = dQueue;
		
		QueueUtils sApp = new QueueUtils();
		int index = sApp.getIndexOfShortestLines(array);
		System.out.println(index);
		
		assertEquals(index, 3);
		
		int sum = sApp.getTotalLengthOfAllQueues(array);
		System.out.println(32);
	}

}
