import student.TestCase;

public class LinkedListTest extends TestCase {
    LinkedList r;

    public void setUp() {
        r = new LinkedList();
        r.add(3);

    }


    public void testLinkedList() {
        exc
        assertTrue(r.contains(3));
        r.add(5);
        assertFalse(r.contains(4));
        assertTrue(r.contains(5));

    }


    public void testPrint() {
        
    }
    public void testRemoveFirst() {
        assertEquals(r.removeFirst(), 3);
        
    }
}
