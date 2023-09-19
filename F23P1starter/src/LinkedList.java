 /**
     * Node for linkedlist
     * 
     * @author nchilakala
     * @version 1
     */
public class LinkedList {
   
    public class Node {
        private Node previousNode;
        private Node nextNode;
        private int data;

        
        public Node(int info) {
            this.data = info;
            this.nextNode = null;
            this.previousNode = null;
        }

       
        public Node getNextNode() {
            return nextNode;
        }
        

        /**
         * get Node prev
         * @return Node prev
         */
        public Node getPreviousNode() {
            return previousNode;
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

    
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }


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
     * removeFirst method
     * 
     * @return int -the value
     */
    public int removeFirst() {
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


   
    public boolean isEmpty() {
        return head == null;
    }


    public boolean contains(int hasIt) {
       
        Node current = head;
        while (current != null) {
            if (hasIt == current.data) {
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


    public void remove(int r) {
       
            Node curr = head;
            while (curr != null) {
                if (curr.data == r) {
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



    
    public Node getHead() {
        return head;
    }
}