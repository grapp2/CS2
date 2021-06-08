/**
	This class implements a circular array.
	It expands if the queue becomes full.
**/
public class Queue {
	private QNode [] queue;		// Array that stores the queue elements.
	private int front;				// Index pointing to the front of the queue.
	private int end;					// Index pointing to the element that is one less than the end of the queue.
	private int numElements;	// The number of elements currently stored in the queue.
	private int size;					// The capacity of the allocated array. If the number of elements reaches this capacity, we need to expand.

	/**
		Constructor: Allocates a queue with inital size of 1000.
	**/
	public Queue() {
		numElements = 0;
		size = 1000;
		front = size - 1;
		end = size - 1;
		queue = new QNode[size];
	}
	
	/**
		This function enqueues a new element p into the queue. 
		This also expands the array if it is full.
	**/
	public void enqueue(QNode p) {
		if (numElements == size) {
			// TODO: Expand the array, by first creating another one with twice the size and copying the contents of the old array.
		    size *= 2;
		    QNode[] copy = new QNode[size];
		    for (int i = 0; i < numElements; i++)
		    {
		        copy[i] = queue[i];
		    }
		    queue = copy;
		}
		queue[numElements + 1] = p;
		numElements++;
		end++;
	}

	/**
		This function removes and returns the end front element in the queue.
	**/
	public QNode dequeue() {
		if (numElements == 0) {
			return null;
		}
		QNode q = queue[0];
		QNode[] copy = new QNode[size];
		for (int i = 1; i < numElements; i++)
		{
		    copy[i-1] = queue[i];
		}
		queue = copy;
		numElements--;
		end--;
		return q;
	}

	/**
		This function returns true if the queue is empty, otherwise returns false.
	**/
	public boolean isEmpty() {
		if (numElements == 0) 
			return true;
		return false;
	}

	/**
		This function prints the contents of the queue.
	**/
	public void print() {
        // TODO: print the contents of the queue from front to end.
	    for (int i = 0; i < numElements; i++)
	    {
	        System.out.println(queue[i].getWord());
	    }
	}

	/**
		This function copies the contents of the array passed in, into the queue.
		This is usually called when expanding the array size.
	**/
	private void copyArray(QNode [] array) {
		// TODO: Code to copy the array into queue. Make sure that the queue parameters -- front, end and numElements and size are all set correctly.
	    for (int i = 0; i < array.length; i++)
	    {
	        if (array[i] != null)
	        {
	            enqueue(array[i]);
	        }
	    }
	}
}
