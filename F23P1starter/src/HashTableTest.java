import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import  student.TestCase;

public class HashTableTest extends TestCase{
    String[] keywords = {"Good", "Bad", "Ugly"};
    Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 75,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    Seminar mysem2 = new Seminar(1739, "Seminar Title", "2405231000", 75,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    Record r = new Record(mysem); // id = 1729
    Record r2 = new Record(mysem2); // id = 1739
    HashTable h = new HashTable(2);
    HashTable h2 = new HashTable(1);
    
    public void testInsert() {
        Seminar tester = new Seminar(1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
       Record testr = new Record(tester);
       HashTable testhash = new HashTable(10);
       testhash.hashInsert(r);
       testhash.hashInsert(r2);
       h2.hashInsert(r);
       h2.hashInsert(r2);
       
    }
    public void testSearch() {
        assertEquals(h.search(1739), null);
        h.hashInsert(r2);
        h2.hashInsert(r);
        assertEquals(h.search(1739),r2);
        assertEquals(h2.search(1729), r);
        h.delete(r2);
        assertEquals(h.search(189), null);
    }
    public void testGetSize() {
        assertEquals(h2.getSize(),1);
        h2.hashInsert(r);
        assertEquals(h2.getSize(),2);
        h2.hashInsert(r2);
        assertEquals(h2.getSize(),4);
    }

    public void testDelete() {
        HashTable h3 = new HashTable(4);
        h3.hashInsert(r);
        assertEquals(h3.delete(r), "Record with ID 1729 has been deleted");
        assertEquals(h3.search(1729), null);
        assertEquals(h3.delete(r2), "Record with ID 1739 not found.");
    }



}