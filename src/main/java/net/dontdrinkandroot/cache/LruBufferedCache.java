package net.dontdrinkandroot.cache;

/**
 * A Buffered Cache that has a LruRecyclingExpungeStrategy for both persistent storage and buffer.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
public interface LruBufferedCache<K, V> extends LruCache<K, V> {

	int getBufferMaxSize();


	void setBufferMaxSize(int maxSize);


	int getBufferRecycleSize();


	void setBufferRecycleSize(int bufferRecycleSize);

}
