import  student.TestCase;
public class HashTableTest extends TestCase{
    String[] keywords = {"Good", "Bad", "Ugly"};
    Seminar mysem = new Seminar(1729, "Seminar Title", "2405231000", 75,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    Seminar mysem2 = new Seminar(1739, "Seminar Title", "2405231000", 75,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    Record r = new Record(mysem);
    Record r2 = new Record(mysem2);
    HashTable h = new HashTable(2);
    HashTable h2 = new HashTable(1);
    
    public void testInsert() {
        Seminar tester = new Seminar(1, "Seminar Title", "2405231000", 75,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
       record testr = new Record(tester);
       HashTable testhash = new HashTable(10);
       testhash.Hashinsert(r);
       testhash.Hashinsert(r2);

    }
    public void testSearch() {
        h.Hashinsert(r2);
        h2.Hashinsert(r);
        assertEquals(h.search(1739),r2);

    }
    public void testGetSize() {
        assertEquals(h2.getSize(),1);

    }
}