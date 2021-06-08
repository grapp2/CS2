/**
 * A Big Integer Data type implemented as a linked list in reverse.
 **/
public class BigInt {
    private Node head;      // Head node in the linked list.

    /**
     * Takes a string representation of a huge integer, and creates a BigInt type.
     */
    public BigInt(String number) {
        head = null;

        // Loop through each character and add it to the linked list.
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);

            // Throw exception if the input data is non-numeric.
            if (c < '0' || c > '9')
                throw new IllegalArgumentException("input contains non-numeric data");

            // Insert at the head of the linked list and update head.
            head = new Node((int) c - (int) '0', head);
        }
    }

    /**
     * A method to add two BigInt types passed in as lhs and rhs.
     * The calling object (this reference) stores the result.
     */
    public void add(BigInt lhs, BigInt rhs) {

        // Dealing with null lists.
        if (lhs.head == null || rhs.head == null) {

            // If both lhs and rhs are null, then result is null.
            if (lhs.head == null && rhs.head == null) {
                head = null;
                return;
            }

            // Have a handle to the non-empty list.
            Node iter = (lhs.head != null) ? lhs.head : rhs.head;

            // Copy the head node.
            head = new Node(iter.digit, null);
            iter = iter.next;
            Node cur = head;

            // Loop through and copy the rest of the list.
            while (iter != null) {
                cur.next = new Node(iter.digit, null);
                iter = iter.next;
                cur = cur.next;
            }
            return;
        }
        // General case, when there is something in lhs and rhs.

        Node liter = lhs.head;
        Node riter = rhs.head;

        // Find the newdigit and carry.
        int sum = liter.digit + riter.digit;
        int newdigit = sum % 10;
        int carry = sum / 10;

        // Create the head node of the result.
        head = new Node(newdigit, null);
        Node cur = head;

        liter = liter.next;
        riter = riter.next;

        // Loop through and create the rest of the list.
        while (liter != null && riter != null) {
            sum = liter.digit + riter.digit + carry;
            newdigit = sum % 10;
            carry = sum / 10;

            cur.next = new Node(newdigit, null);
            cur = cur.next;
            liter = liter.next;
            riter = riter.next;
        }

        // If lhs and rhs contain different number of digits, then we need
        // to copy the remaning digits of the non-empty list.
        // First, get a handle to the non-empty list.
        if (riter != null)
            liter = riter;

        // Loop through and add the last digits of the non-empty list.
        while (liter != null) {
            sum = liter.digit + carry;
            newdigit = sum % 10;
            carry = sum / 10;

            cur.next = new Node(newdigit, null);
            cur = cur.next;
            liter = liter.next;
        }

        // Finally, need to add in the carry.
        if (carry == 1)
            cur.next = new Node(carry, null);

    }

    /**
     * public access to the print method.
     * This simply calls the private print method to
     * print the list.
     */
    public void print() {
        print(head);
        System.out.println();
    }

    /**
     * Recursive to function to print the list in reverse (which is the original
     * direction of the list. This is done by first recursively printing the
     * rest of the list, and then the current node x.
     */
    private void print(Node x) {
        if (x == null)
            return;
        print(x.next);
        System.out.print(x.digit + " ");
    }
}