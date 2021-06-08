
public class PriorityQueue {
    private Interval [] heap; // An array that encodes a max-heap data structure.
    private int size;	// The size of allocated buffer for the heap.
    private int numElements;	// The number of elements currently stored. 

    /**
		Constructor: s is the initial size of the heap.
     */
    public PriorityQueue(int s) {
        size = s;
        heap = new Interval[size + 1];	// 1 extra element allows us to use 1-based indexing. The max heap stores intervals keyed on their lengths.
        numElements = 1;
    }

    /**
		Inserts a new Interval k into the heap. Automatically expands the heap if the buffer allocated is full.
	TODO: Please complete this method.
     */
    public void insert(Interval k) {
        if (numElements == size) {
            // Expand the buffer allocated for the heap to another buffer that is twice as big
            Interval[] copy = new Interval[size * 2 + 1];
            for (int i = 1; i < size; i++)
            {
                copy[i] = heap[i];
            }
            heap = copy;
            size *= 2;
        }
        // Insert without buffer expansion here.
        heap[numElements] = k;
        siftUp(numElements);
        numElements++;

    }

    public int getParent(int index)
    {
        return index / 2;
    }

    public int getLeft(int index)
    {
        return index * 2;
    }

    public int getRight(int index)
    {
        return index * 2 + 1;
    }
    
    public int direction(Interval left, Interval right)
    {
        if (right.compareTo(left) < 0)
        {
            return 0;
        }
        else if (right.compareTo(left) > 0)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
//&& (heap[index].compareTo(heap[right]) <= 0 || heap[index].compareTo(heap[left]) <= 0)
    public void siftDown(int index) 
    {
        int lOrR = -1;
        while (getLeft(index) < numElements && getRight(index) < numElements)
        {
            if (heap[getRight(index)] != null && heap[getLeft(index)] != null)
            {
                lOrR = direction(heap[getLeft(index)],heap[getRight(index)]);
            }
            if(heap[getRight(index)] != null && lOrR == 1 && heap[index].compareTo(heap[getRight(index)]) < 0)
            {
                Interval temp = heap[index];
                heap[index] = heap[getRight(index)];
                heap[getRight(index)] = temp;
                index = getRight(index);

            }
            else if (heap[getRight(index)] != null && lOrR == 0 && heap[index].compareTo(heap[getLeft(index)]) < 0)
            {
                Interval temp = heap[index];
                heap[index] = heap[getLeft(index)];
                heap[getLeft(index)] = temp;
                index = getLeft(index);
            }
            else
            {
                break;
            }
        }
    }
    
    public void siftUp(int index)
    {
        int parent = getParent(index);
        while (index != 1 && heap[index].compareTo(heap[parent]) >= 0)
        {
            Interval temp = heap[index];
            heap[index] = heap[getParent(index)];
            heap[getParent(index)] = temp;
            index = getParent(index);
        }
    }

    /**
		Returns the maximum Interval from the heap (usually the one with the largest length. See the compareTo function of Interval for more details on the comparison.
	TODO: Please complete this method.
     */
    public Interval remove_max() {
        if (numElements == 1) return null; // Retuns null if heap is empty.
        // Remove_max code here.
        
        Interval temp = heap[1];
        numElements--;
        heap[1] = heap[numElements];
        heap[numElements] = null;
        siftDown(1);
        return temp; // Replace this statement with returning the max element (root) in the heap.
    }

    /**
		This function prints the contents of the array that encodes a heap.
     */
    public void print() {
        System.out.println("Printing heap:");
        for (int i = 1; i < numElements; ++i)
            System.out.println(heap[i]);
    }
    
    public static void main(String[] args)
    {
        PriorityQueue a = new PriorityQueue(10);
        
        for (int i = 0; i < 10; ++i)
        {
            a.insert(new Interval(i,i*2));
        }
        System.out.print("After insert: ");
        a.print();
        for (int i= 0; i < 5; ++i)
        {
            a.remove_max();
            System.out.println("removing: ");
            a.print();
        }
        System.out.println("After removeMax: ");
        a.print();
    }
}
