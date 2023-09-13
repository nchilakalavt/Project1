
import  student.TestCase;

public class HashTableTest extends TestCase{
    private String[] keywords = {"Good", "Bad", "Ugly"};
   /* private Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    private Seminar mysem2 = new Seminar(1739, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    private Seminar mysem3 = new Seminar(1639, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");*/
    private Handle han= new Handle(3, 20);
    private Handle han2= new Handle(3, 20);
    private Record r = new Record(han, 1729); // id = 1729
    private Record r2 = new Record(han2, 1739); // id = 1739
    private HashTable h = new HashTable(2);
    private HashTable h2 = new HashTable(1);
    
    public void testInsert() {
     /*   Seminar tester = new Seminar(1, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");*/
       Handle hanpan = new Handle(3,20);
       Record testr = new Record(hanpan, 1);
       HashTable testhash = new HashTable(10);
       testhash.hashInsert(r);
       testhash.hashInsert(r2);
       h2.hashInsert(r);
       h2.delete(r);
       h2.hashInsert(r2);
       
       HashTable h69 = new HashTable(10);
       h69.hashInsert(testr);
     //  Seminar sameIdTesterSem = new Seminar(1, "Seminar Title", "2405231000", 1,
      //     (short)15, (short)33, 125, keywords, "This is a great seminar");
       Handle sameAs = new Handle(3, 20);
       Record sameIdTesterRec = new Record(sameAs, 1);
       h69.hashInsert(sameIdTesterRec);
    }
    public void testSearch() {
        assertEquals(h.search(1739), null);
        h.hashInsert(r2);
        h2.hashInsert(r);
        assertEquals(h.search(1739),r2);
        assertEquals(h2.search(1729), r);
        h.delete(r2);
        assertEquals(h2.search(1739), null);
        assertEquals(h.search(189), null);
        
        HashTable h10 = new HashTable(10);
        h10.hashInsert(r);
        Record r2 = new Record(han, 1739);
        h10.hashInsert(r2);
        Record r3 = new Record(han2, 1639);
      //  assertEquals(r3.getSeminar(), mysem3);
        h10.delete(r2);
        h10.hashInsert(r3);
        assertEquals(h10.search(1639), r3);
        
        HashTable h11 = new HashTable(10);
        h11.hashInsert(r);
        Handle hon = new Handle(3, 23);
        Record r4 = new Record(han, 1739);
        h11.hashInsert(r4);
        Record r5 = new Record(hon, 1639);
        h11.hashInsert(r5);
        assertEquals(h11.search(1639), null);
        
        HashTable h69 = new HashTable(4);
        assertEquals(h69.search(420), null);
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
        
        HashTable h5 = new HashTable(4);
        assertEquals(h5.delete(r), "Hash Table is empty");
    }



}