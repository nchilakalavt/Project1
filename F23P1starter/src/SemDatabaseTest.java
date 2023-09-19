import student.TestCase;

public class SemDatabaseTest extends TestCase {
    private HashTable h = new HashTable(20);
    private MemManager m = new MemManager(20);
    private SemDatabase s = new SemDatabase(4, 512);
    private String[] keywords = { "Good", "Bad", "Ugly" };
    private Seminar mysem2 = new Seminar(1, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");

    public void setUp() {
        Handle han = new Handle(2, 2);
        Record r = new Record(han, 1);
        h.hashInsert(r);

    }


    public void testDelete() throws Exception {
        Handle han = new Handle(2, 2);
        Record r = new Record(han, 1);
        s.insert(mysem2);
        s.delete(mysem2.getID());
        // assertEquals(, false)
    }


    public void testInsert() throws Exception {
        String[] keywords = { "Good", "Bad", "Ugly" };
        s.insert(mysem2);
        Seminar mysem = new Seminar(1749, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem);
        Seminar mysem1 = new Seminar(1, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem1);
    }


    public void testSearch() throws Exception {
        String[] keywords = { "Good", "Bad", "Ugly" };
        s.insert(mysem2);
        Seminar mysem = new Seminar(1749, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem);
        Seminar mysem1 = new Seminar(1759, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem1);
        s.search(1759);
        s.search(1739);
        s.search(4);
    }


    public void testPrintHash() throws Exception {
        String[] keywords = { "Good", "Bad", "Ugly" };
        s.insert(mysem2);
        Seminar mysem = new Seminar(2, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem);
        Seminar mysem1 = new Seminar(3, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem1);
        Seminar mysem10 = new Seminar(10, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem10);
        s.printHash();
        s.delete(2);
        s.printHash();
    }


    public void testPrintBlock() throws Exception {
        s.printFreeBlock();
        String[] keywords = { "Good", "Bad", "Ugly" };
        s.insert(mysem2);
        Seminar mysem = new Seminar(2, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem);
        s.printFreeBlock();
        Seminar mysem1 = new Seminar(3, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem1);
        Seminar mysem10 = new Seminar(10, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        s.insert(mysem10);

        s.printFreeBlock();

    }
}
