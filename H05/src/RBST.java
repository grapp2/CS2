import java.util.Random;

public class RBST {

    private Node root;		// Head node of the tree.
    private Random rand;	// A random object - required to randomly insert nodes into the tree.
    
    // Constructors
    public RBST() 
    {
        root = null;
        rand = new Random();
    }
    public RBST(Node _root) 
    {
        root = _root;
        rand = new Random();
    }

    /**
		Wrapper print method to print the contents of the tree. Calls the private print method.
     */
    public void print() {
        print(root);
        System.out.println();
    }
    /**
		Print method to print the contents of the tree.
     */
    private void print(Node T) {
        if (T == null)
        {
            return;
        }
        print(T.getLeft());
        System.out.print(T.getTeam() + " ");
        print(T.getRight());
    }

    /**
		Wrapper for insertNormal method.
     */
    public void insertNormal(int team, int rank) {
        root = insertNormal(root, team, rank);
    }
    /**
		Insert the data team at position rank into node T. This is the normal insert routine without any balancing.
     */
    private Node insertNormal(Node T, int team, int rank) {

        if (T == null)
        {
            return new Node(team);
        }
        assert (rank >= 1 && rank <= T.getSize() + 1) : "rank should be between 1 and size of the tree <" + (T.getSize()+1) + ">";

        if (rank <= getRank(T))
        {
            T.incSize();
            T.setLeft(insertNormal(T.getLeft(), team, rank));
        }
        else
        {
            T.incSize();
            T.setRight(insertNormal(T.getRight(), team, rank - getRank(T)));
        }
        return T;
    }

    private int getRank(Node T)
    {
        if (T.getLeft() == null)
        {
            return 1;
        }
        else
        {
            return T.getLeft().getSize() + 1;
        }
    }

    /**
		Split the tree at psition rank. It returns RET, a RBST array of length two. RET[0] is the left side of the split,
		and RET[1] is the right side of the split. This is a wrapper method that calls the private split method.
     */
    public RBST[] split(int rank) {
        Node T = root;
        Node [] ret = split(T, rank);
        RBST [] RET = {null, null};
        RET[0] = new RBST(ret[0]);
        RET[1] = new RBST(ret[1]);
        return RET;
    }
    /**
		The private split method that splits tree T at position rank. 
		It returns an array ret, of two nodes -- ret[0] is the root of the left tree, and
		ret[1] is the root of the right tree of the split.
     */
    private Node[] split(Node T, int rank) {
        Node [] ret = {null, null};	// ret[0] is the root node to the left side of the split, ret[1] is the right side.	

        if (T == null)
        {
            ret[0] = null;
            ret[1] = null;
        }
        else if (rank == getRank(T)) 
        {
            ret[1] = T.getRight();
            T.setRight(null);
            ret[0] = T;
        }
        else if (rank < getRank(T))
        {
            ret = split(T.getLeft(), rank);
            T.setLeft(ret[1]);
            ret[1] = T;
        }
        else 
        {
            ret = split(T.getRight(), rank - getRank(T));
            T.setRight(ret[0]);
            ret[0] = T;
        }
        // update sizes of ret[0] and ret[1].
        if (ret[0] != null)
        {
            ret[0].updateSize();
        }
        if (ret[1] != null)
        {
            ret[1].updateSize();
        }
        
        return ret;
    }

    /**
		Insert the data team at position rank in the tree. This is a wrapper method that calls the private insert method.
     */
    public void insert(int team, int rank) {
        rand = new Random();
        root = insert(root, team, rank);
    }
    /**
		The private insert method, that inserts the data team at position rank in the tree rooted at node T. 
		team is inserted at the root with probability 1/(T.getSize()+1). This is done by splitting the tree T
		at position rank-1, creating a new node for team, and attaching the left and right sides of the split as
		the two subtrees of the new node. Otherwise, with probability 1 - 1/(T.getSize()+1), insert recursively
		at either the left tree (rank <= rank of root) or at the right tree (rank > rank of root).
     */
    private Node insert(Node T, int team, int rank) {
        if (T == null)
        {
            return new Node(team);
        }
        assert (rank >= 1 && rank <= T.getSize() + 1) : "rank should be between 1 and size of the tree <" + (T.getSize()+1) + ">";

        int randNum = rand.nextInt(T.getSize() + 1);
        if (randNum == 0)
        {
            Node[] split = split(T, rank - 1);
            T = new Node(team);
            T.setLeft(split[0]);
            T.setRight(split[1]);
            // Update T's size and return T.
            T.updateSize();
            return T;
        }
        else
        {
            if (rank <= getRank(T))
            {
                T.incSize();
                T.setLeft(insert(T.getLeft(), team, rank));
            }
            else
            {
                T.incSize();
                T.setRight(insert(T.getRight(), team, rank - getRank(T)));
            }
            return T;
        }
    }

    /**
		Return the node at position rank in the tree. This is a wrapper method that calls the private select method.
     */	
    public Node select(int rank) {
        return select(root, rank);
    }
    /**
		The select method that returns the node in the tree at position rank. 
     */
    private Node select(Node T, int rank) {
        if (T == null) return null;

        assert (rank >= 1 && rank <= T.getSize()) : "rank should be between 1 and size of the tree <" + T.getSize() + "> ";
        
        if (rank == getRank(T)) 
        {
            return T;
        }
        if (rank < getRank(T))
        {
            return select(T.getLeft(), rank);
        }
        else
        {
            return select(T.getRight(), rank - getRank(T));
        }
    }

    /**
		Returns the size of the tree.
     */
    public int getSize() {
        if (root == null) return 0;
        return root.getSize();
    }
}
