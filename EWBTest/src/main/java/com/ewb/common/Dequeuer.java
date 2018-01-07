package com.ewb.common;

public interface Dequeuer<T> {

	public T dequeue() throws InterruptedException;
}
