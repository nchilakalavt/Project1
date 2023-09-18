import student.TestCase;

public class BlockTest extends TestCase {
    Block b;
    Block b2;
    Block b3;

    /**
     * Test method for testing the getter and setter methods of the Block Class
     */
    public void testGettersAndSetters() {
        b2 = new Block(10, 20);
        b = new Block(12, 24);
        b3 = new Block(10, 5);
        assertEquals(b.getLength(), 12);
        assertEquals(b2.getLength(), 10);
        assertEquals(b3.getLength(), -5);
        assertEquals(b.getEnd(), 24);
        assertEquals(b2.getStart(), 10);
        b3.setEnd(20);
        assertEquals(b3.getLength(), 10);
        b.setStart(1);
        assertEquals(b.getStart(), 1);
    }

}
