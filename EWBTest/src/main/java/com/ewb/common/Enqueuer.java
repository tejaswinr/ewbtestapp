package com.ewb.common;

public interface Enqueuer<T> {

	public boolean enqueue(T object) throws InterruptedException;

}
