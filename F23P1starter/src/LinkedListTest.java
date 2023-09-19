import student.TestCase;
/**
 * class to test linked list
 * @author oratc nchilakala
 */
public class LinkedListTest extends TestCase {
    LinkedList r;
/**
 * setUp method to set up test variables
 */
    public void setUp() {
        r = new LinkedList();
        r.add(3);


    }

/**
 * Testmethod for linked list
 */
    public void testContains() {
        assertTrue(r.contains(3));
        r.add(5);
        assertFalse(r.contains(4));
        assertTrue(r.contains(5));
        assertEquals(r.removeFirst(), 3);
    }

/**
 * test method for print
 */
    public void testPrint() {
        
    }
    
/**
      test method for remove first
     */
    public void testRemoveFirst() {
        LinkedList emptyL = new LinkedList();
        Exception e = null;
       /** try {
            emptyL.removeFirst();
          
        }
        catch
        {
            assertEquals(e, "Empty Lists");
        }
        */
        
    }
}
