import student.TestCase;

public class SemDatabaseTest extends TestCase {
    HashTable h = new HashTable(20);
    MemManager m = new MemManager(20);
    SemDatabase s = new SemDatabase(20, 20);

    public void setUp() {
        Handle han = new Handle(2, 2);
        Record r = new Record(han, 1);
        h.hashInsert(r);
    }


    public void testDelete() {

        s.delete(1);
        // assertEquals(, false)
    }


    public void testInsert() {
        String[] keywords = { "Good", "Bad", "Ugly" };

        Seminar mysem2 = new Seminar(1739, "Seminar Title",
            "2405231000", 1, (short)15, (short)33, 125, keywords,
            "This is a great seminar");

    }
}
