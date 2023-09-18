

/**
 * This provides implementation for some of the LList methods.
 *
 * @author Mark Wiggans (mmw125)
 * @version 3/29/15
 * @author Eric Williamson
 * @version 10/30/15
 * @author maellis1
 * @version 11/1/15
 * @author Nirish Chilakala (nchilakala)
 * @version 9/15/23
 * @param 
 *            The type of object the class will store
 */
public class DLList {
    private int size;
    private Node head;
    private Node tail;
    /**
     * This represents a node in a doubly linked list. This node stores data, a
     * pointer to the node before it in the list, and a pointer to the node
     * after it in the list
     *            This is the type of object that this class will store
     * @author Mark Wiggans (mmw125)
     * @version 4/14/2015
     * @author Nirish Chilakala (nchilakala)
     * @version 9/17/23
     */
    public static class Node {
        private Node nextNode;
        private Node previousNode;
        private int data;


        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         */
        public Node(int d) {
            this.data = d;
            this.nextNode = null;
            this.previousNode = null;
        }

        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node n) {
            nextNode = n;
        }

        /**
         * Sets the node before this one
         *
         * @param n
         *            the node before this one
         */
        public void setPrevious(Node n) {
            previousNode = n;
        }

        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node getNext() {
            return nextNode;
        }

        /**
         * Gets the node before this one
         *
         * @return the node before this one
         */
        public Node getPrevious() {
            return previousNode;
        }

        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public int getData() {
            return data;
        }
    }


    /**
     * Create a new DLList object.
     */
    public DLList() {
        init();
    }

    /**
     * Initializes the object to have the head and tail nodes
     */
    private void init() {
        head = null;
        tail = null;
        size = 0;
    }
    public Node getHead() {
        return head;
    }
    /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int getSize() {
        return size;
    }

    /**
     * Removes all of the elements from the list
     */
    public void clear() {
        init();
    }

    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    public boolean contains(int obj) {
        return lastIndexOf(obj) != -1;
    }

    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if there no node at the given index
     */
    public int get(int index) {
        return getNodeAtIndex(index).getData();
    }

    /**
     * Adds a element to the end of the list
     *
     * @param newEntry
     *            the element to add to the end
     */
    public void add(int newEntry) {
        add(getSize(), newEntry);
    }

    /**
     * Adds the object to the position in the list
     *
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    public void add(int index, int obj) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }

        Node nodeAfter;
        if (index == size) {
            nodeAfter = tail;
        } 
        else {
            nodeAfter = getNodeAtIndex(index);
        }

        Node addition = new Node(obj);
        addition.setPrevious(nodeAfter.previous());
        addition.setNext(nodeAfter);
        nodeAfter.previous().setNext(addition);
        nodeAfter.setPrevious(addition);
        size++;

    }

    /**
     * gets the node at that index
     * 
     * @param index
     * @return node at index
     */
    public Node getNodeAtIndex(int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException("No element exists at " 
                    + index);
        }
        Node current = head.next(); // as we have a sentinel node
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }

    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    public int lastIndexOf(int obj) {
        /*
         * We should go from the end of the list as then we an stop once we find
         * the first one
         */
        Node current = tail.previous();
        for (int i = size() - 1; i >= 0; i--) {
            if (current.getData() == obj) {
                return i;
            }
            current = current.previous();
        }
        return -1;  //if we do not find it
    }

    /**
     * Removes the element at the specified index from the list
     *
     * @param index
     *            where the object is located
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     * @return true if successful
     */
    public int removeFirstNode() {
        if(!isEmpty()) {
            int retValue = head.data;
            head = head.next;
            if (head == null) {
                head.previous = null;
            }
            else {
                tail = null;
            }
        }
        throw new IllegalStateException("Empty Lists");
    }

    /**
     * Removes the first object in the list that .equals(obj)
     *
     * @param obj
     *            the object to remove
     * @return true if the object was found and removed
     */

    public boolean remove(int obj) {
        Node current = head.next();
        while (!current.equals(tail)) {
            if (current.getData() == obj) {
                current.previous().setNext(current.next());
                current.next().setPrevious(current.previous());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }

    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node currNode = head.next();
            while (currNode != tail) {
                int element = currNode.getData();
                builder.append(element);
                if (currNode.next != tail) {
                    builder.append(", ");
                }  
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }
    

    public void printList() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }
}

