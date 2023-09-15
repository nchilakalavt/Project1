
import  student.TestCase;

public class MemManagerTest extends TestCase{
    private MemManager mem = new MemManager(512);
    private String[] keywords = {"Good", "Bad", "Ugly"};
    private Seminar mysem3 = new Seminar(1639, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    
    public void testInsert() throws Exception {
        mem.dump();
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        mem.dump();
    }
    
    public void testRemove() throws Exception {
        Handle hand = new Handle(0, mysem3.serialize().length);
        mem.remove(hand);
        mem.dump();
    }
}
