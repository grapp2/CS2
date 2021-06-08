/*
 * This class maintains a set of integers. 
 */
public class IntSet {
    private Node head;  // A Node to represent the set. This is always maintained in sorted order.
    private int size;       // The number of elements currently stored in the set.

    public IntSet() 
    {
        size = 0;
        head = null;
    }

    /* Find if a key is present in the set. Returns -1 if the key is not present, otherwise returns the position in the set.*/
    public int find(int key) 
    {
        Node copy = head;
        int position = 0;
        while (copy != null)
        {
            if (copy.digit == key)
            {
                return position;
            }
            position++;
            copy = copy.next;
        }
        return -1;
    }
    
    public int findOccurances(int key)
    {
        Node copy = head;
        int totalOccurances = 0;
        while (copy != null)
        {
            if(copy.digit == key)
            {
                totalOccurances++;
            }
            copy = copy.next;
        }
        return totalOccurances;
    }

    /* Insert a key into the set. */
    public int insert(int key) 
    {
        // Make sure that the key is not present.
        assert (find(key) == -1);
        Node newHead = head;
        Node head2reverse = head;
        Node reverse = null;
        // Perform insert.
        if (head == null)
        {
            head = new Node(key, null);
        }
        else if (isMax(head, key))
        {
            Node singleHead = new Node(key, null);
            while (head2reverse != null)
            {
                reverse = new Node(head2reverse.digit, reverse);
                head2reverse = head2reverse.next;
            }
            while (reverse != null)
            {
                singleHead = new Node(reverse.digit, singleHead);
                reverse = reverse.next;
            }
            head = singleHead;
        }
        else
        {

            while (newHead.digit < key)
            {
                newHead = newHead.next;
            }

            newHead = new Node(key, newHead);

            while (head2reverse.digit < key)
            {
                reverse = new Node (head2reverse.digit, reverse);
                head2reverse = head2reverse.next;
            }

            while (reverse != null)
            {
                newHead = new Node(reverse.digit, newHead);
                reverse = reverse.next;
            }
            head = newHead;
        }
        size++;
        return findOccurances(key);
    }

    public boolean isMax(Node n, int key)
    {
        Node copy = n;
        if (n != null)
        {
            while (copy.digit < key)
            {
                if (copy.next == null)
                {
                    return true;
                }
                copy = copy.next;
            }
        }
        return false;
    }

    /* Remove a key from the set. */
    public void remove(int key) {
        // Make sure that the key is present.
        int pos = find(key);
        assert (pos != -1);

        Node cur = head;
        Node reverse = null;
        Node newHead = null;


        for (int i = 0; i < pos; i++)
        {
            reverse = new Node(cur.digit, reverse);
            cur = cur.next;
        }
        cur = cur.next;
        for (int i = pos; i < size - 1; i++)
        {
            reverse = new Node(cur.digit, reverse);
            cur = cur.next;
        }

        while (reverse != null)
        {
            newHead = new Node(reverse.digit, newHead);
            reverse = reverse.next;
        }
        head = newHead;


        size--;
    }

    /* Print the contents of the set in sorted order. */
    public void print() {
        Node copy = head;
        while (copy != null)
        {
            System.out.print(copy.digit + " ");
            copy = copy.next;
        }
        if (head == null)
        {
            System.out.print("Nothing in the IntSet");
        }
        System.out.println();
    }
}
