/**
 * Linked List clas to use in MemManager
 * 
 * @author Nirish Chilakala (nchilakala), Pratham Chopra (pratc)
 * @version 1
 */
public class LinkedList {
    /**
     * Node Class
     */
    public class Node {
        private Node previousNode;
        private Node nextNode;
        private int data;

        /**
         * Node Constructor
         * 
         * @param info:
         *            data to be held inside node
         */
        public Node(int info) {
            this.data = info;
            this.nextNode = null;
            this.previousNode = null;
        }


        /**
         * Returns next node
         * 
         * @return Node next
         */
        public Node getNextNode() {
            return nextNode;
        }


        /**
         * get Node prev
         * 
         * @return Node prev
         */
        public Node getPreviousNode() {
            return previousNode;
        }


        /**
         * int data for node
         * 
         * @return int data
         */
        public int getData() {
            return data;
        }

    }

    private Node head;
    private Node tail;

    /**
     * Linked List Constructor
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }


    /**
     * adds node onto the end of the list
     * 
     * @param data:
     *            data to be help in node that will be appended
     */
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.nextNode = newNode;
            newNode.previousNode = tail;
            tail = newNode;
        }
    }


    /**
     * removeFirstNode method
     * 
     * @return int -the value removed
     */
    public int removeFirstNode() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Lists");
        }

        int removal = head.data;
        head = head.nextNode;
        if (head == null) {
            tail = null;

        }
        else {
            head.previousNode = null;
        }
        return removal;
    }


    /**
     * isEmpty method
     * 
     * @return bool -whether the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }


    /**
     * Checks if any of the nodes in the list contain a specific data object
     * 
     * @param data
     *            - data in node we are looking for
     * @return bool - whether theres a node containing the data
     */
    public boolean contains(int data) {

        Node current = head;
        while (current != null) {
            if (data == current.data) {
                return true;
            }
            current = current.nextNode;
        }

        return false;
    }


    /**
     * printList method
     */
    public void printer() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.nextNode;
        }
        System.out.println();
    }


    /**
     * Removes node from list
     * 
     * @param data
     *            - data in node we want to remove
     */
    public void remove(int data) {

        Node curr = head;
        while (curr != null) {
            if (curr.data == data) {
                if (curr.previousNode == null) {
                    head = curr.nextNode;
                    if (head != null) {
                        head.previousNode = null;
                    }
                }
                else {
                    curr = curr.nextNode;
                    if (curr.nextNode != null) {
                        curr = curr.previousNode;
                    }
                    else {
                        tail = curr.previousNode;
                    }
                }
                return;
            }
            curr = curr.nextNode;
        }
    }


    /**
     * Returns whatever node is at the top of the list
     * 
     * @return head node
     */
    public Node getHead() {
        return head;
    }
}
