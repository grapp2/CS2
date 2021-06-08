/**
 * Class Node. Represents a single node in the linked list.
 * Each node only contains an integer digit and a next reference.
 **/
public class Node {
    public int minimum;
    public int maximum;
    public Node next;

    public Node(int min,int max, Node n) {
        minimum = min;
        maximum = max;
        next = n;
    }
}
         