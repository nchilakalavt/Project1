/**
 * LinkedList class
 * 
 * @version 1.0
 * @author xavier0ne keshr
 */
public class LinkedList {
    /**
     * Node for linkedlist
     * 
     * @author nchilakala
     * @version 1
     */
    public class Node {
     
        private Node next;
        
        private Node prev;
       
        private int data;

        /**
         * NodeConstructor
         * 
         * @param data
         *            -the data
         */
        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        /**
         * get Node next
         * @return Node next
         */
        public Node getNext() {
            return next;
        }

        /**
         * get Node prev
         * @return Node prev
         */
        public Node getPrev() {
            return prev;
        }

         /**
         * int data for node
         * @return int data
         */
        public int getData() {
            return data;
        }




    }

    private Node head;
    private Node tail;

    /**
     * Constructor
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }


    /**
     * add method
     * 
     * @param data
     *            -the data
     */
    public void add(int data) {

        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }


    /**
     * removeFirst method
     * 
     * @return int -the value
     */
    public int removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Empty Lists");
        }

        int val = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        else {
            tail = null;
        }
        return val;
    }


    /**
     * isEmpty method
     * 
     * @return boolean -true if empty
     */
    public boolean isEmpty() {
        return head == null;
    }


    /**
     * contains method
     * 
     * @param value
     *            -the value
     * 
     * @return boolean -true if contains
     */
    public boolean contains(int value) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == value) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }


    /**
     * printList method
     */
    public void printList() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }


    /**
     * remove method
     * 
     * @param value
     *            -the value
     */
    public void remove(int value) {
        Node curr = head;
        while (curr != null) {
            if (curr.data == value) {
                if (curr.prev == null) {
                    head = curr.next;
                    if (head != null) {
                        head.prev = null;
                    }
                }
                else {
                    curr.prev.next = curr.next;
                    if (curr.next != null) {
                        curr.next.prev = curr.prev;
                    }
                    else {
                        tail = curr.prev;
                    }
                }
                return;
            }
            curr = curr.next;
        }
    }


    /**
     * getHead method
     * 
     * @return Node -the head
     */
    public Node getHead() {
        return head;
    }

}
