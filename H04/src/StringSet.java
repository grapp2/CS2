/**
 * This is a string set data structure, that is implemented as a Hash Table. 
 * This data structure supports operations insert, find and print - that insert a new 
 * String, finds a String key and prints the contents of the data structure resp.
 */
public class StringSet {

    StringNode [] table;	// Hash table - collisions resolved through chaining.
    int numelements;	// Number of elements actually stored in the structure.
    int size;					// Allocated memory (size of the hash table).

    /** 
     * Constructor: initializes numelements, size and initial table size.
     */
    public StringSet() 
    {
        numelements = 0;
        size = 100;
        table = new StringNode[size];
    }

    /*
     * inserts a new key into the set. Inserts it at the head of the linked list given by its hash value.
     */
    public void insert(String key) 
    {
        if (numelements == size) 
        {
            int oldsize = size;
            size *= 2;
            
            StringNode[] newTable = new StringNode[size];
            for (int i = 0; i < oldsize; i++)
            {
                StringNode cur = table[i];
                while(cur != null)
                {
                    int hash = hash(cur.getKey());
                    if (newTable[hash] != null)
                    {
                        newTable[hash] = new StringNode(key, newTable[hash]);
                    }
                    else
                    {
                        newTable[hash] = new StringNode(key, null);
                    }
                    cur = cur.getNext();
                }
            }
            table = newTable;
        }
        int hash = hash(key);
        if (table[hash] != null)
        {
            table[hash] = new StringNode(key, table[hash]);
        }
        else
        {
            table[hash] = new StringNode(key, null);
        }
        numelements++;
    }

    /*
     * finds if a String key is present in the data structure. Returns true if found, else false.
     */
    public boolean find(String key) 
    {
        int hash = hash(key);
        StringNode copy = table[hash];
        while (copy != null)
        {
            if (copy.getKey().equals(key))
            {
                return true;
            }
            copy = copy.getNext();
        }
        return false;
    }

    /*
     * Prints the contents of the hash table.
     */
    public void print() 
    {
        StringNode copy;
        for (int i = 0; i < size; i++)
        {
            if (table[i] != null)
            {
                copy = table[i];
                while (copy != null)
                {
                    System.out.println(copy.getKey());
                    copy = copy.getNext();
                }
            }
        }
    }

    /*
     * The hash function that returns the index into the hash table for a string k.
     */
    public int hash(String k) 
    {
        int hash = 0;
        int x = 3;
        int m = size;
        for (char c : k.toCharArray())
        {
            hash = (hash * x + c) % m;
        }
        return hash;
    }
    
    public static void main(String[] args)
    {
        StringSet a = new StringSet();
        System.out.println(a.hash("cpple"));
    }
}
