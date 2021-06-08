public class IntStack {

    private Node stk;
    private int size;

    public IntStack() 
    {
        stk = null;
        size = 0;
    }

    public void push(int x) 
    {
        stk = new Node(x, stk);
        size++;
    }

    public int pop() 
    {
        if (stk == null)
        {
            return -1;
        }
        else
        {
            int poppedInt = stk.digit;
            stk = stk.next;
            size--;
            return poppedInt;
        }
    }
    
    public void print()
    {
        Node stk1 = stk;
        while (stk1 != null)
        {
            System.out.print(stk1.digit + " ");
            stk1 = stk1.next;
        }
    }
    
    public int peek()
    {
        return stk.digit;
    }
    
    public boolean isEmpty()
    {
        return (stk == null);
    }
}
