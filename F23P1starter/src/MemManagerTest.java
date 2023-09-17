
import  student.TestCase;

public class MemManagerTest extends TestCase{
    private MemManager mem = new MemManager(512);
    private String[] keywords = {"Good", "Bad", "Ugly"};
    private Seminar mysem3 = new Seminar(1639, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    private Seminar mysem = new Seminar(190, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    
    public void testInsert() throws Exception {
        mem.dump();
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        mem.dump();
        System.out.println("lengt: " + mysem.serialize().length);
        mem.insert(mysem.serialize(), mysem.serialize().length);
    }
    
//    public void testRemove() throws Exception {
//        Handle hand = new Handle(0, mysem3.serialize().length);
//        MemManager memEmpty = new MemManager(512);
//        memEmpty.remove(hand);
//        
//        mem.insert(mysem3.serialize(), mysem3.serialize().length);
//        mem.remove(hand);
//        mem.dump();
//        
//        int length = mysem.serialize().length;
//        Handle hand2 = new Handle(length, 2*length);
//        System.out.println(hand2.getEndPos());
//        mem.insert(mysem.serialize(), length);
//        mem.remove(hand2);
//        
//    }
//    
//    public void testGetLength() throws Exception {
//        Handle hand = new Handle(0, mysem3.serialize().length);
//        System.out.println(mysem3.serialize().length);
//        assertEquals(mem.length(hand), hand.getLength());
//    }
}
