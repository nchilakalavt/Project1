import student.TestCase;

public class RecordTest extends TestCase {

    Handle han = new Handle(20, 20);

    /**
     * Test method for testing the record class
     */
    public void testRecord() {
        Record r = new Record(han, 10);
        assertEquals(r.getHandle(), han);
        assertEquals(r.getID(), 10);
        r.setID(12);
        assertEquals(12, r.getID());
    }


    /*
     * Test method for Handle Class, which record takes in
     */
    public void testHandle() {
        assertEquals(han.getStartPos(), 20);
        assertEquals(han.getEndPos(), 20);
        assertEquals(han.getLength(), 0);
    }
}
