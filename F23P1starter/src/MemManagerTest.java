
import  student.TestCase;

public class MemManagerTest extends TestCase{
    private MemManager mem = new MemManager(512);
    private String[] keywords = {"Good", "Bad", "Ugly"};
    private Seminar mysem3 = new Seminar(1639, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    private Seminar mysem = new Seminar(190, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    
    public void testInsert() throws Exception {
        //mem.dump();
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        //mem.dump();
        System.out.println("length: " + mysem.serialize().length);
        mem.insert(mysem.serialize(), mysem.serialize().length);
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        Seminar mysem4 = new Seminar(1739, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem4.serialize(), mysem4.serialize().length);
        Seminar mysem5 = new Seminar(1839, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem5.serialize(), mysem5.serialize().length);
    }
    
    public void testRemove() throws Exception {
        Handle hand = new Handle(0, mysem3.serialize().length);
//        MemManager memEmpty = new MemManager(512);
//        memEmpty.remove(hand);
        
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        mem.insert(mysem.serialize(), mysem.serialize().length);
        mem.remove(hand);
        //mem.dump();
        
        int length = mysem.serialize().length;
        Handle hand2 = new Handle(length, 2*length);
        System.out.println(hand2.getEndPos());
        mem.remove(hand2);
        MemManager mem2 = new MemManager(512);
        mem2.remove(hand);
    }
    
    public void testGetLength() throws Exception {
        Handle hand = new Handle(0, mysem3.serialize().length);
        System.out.println(mysem3.serialize().length);
        assertEquals(mem.length(hand), hand.getLength());
    }
    
    public void testGet() throws Exception {
        Handle hand = new Handle(0, mysem.serialize().length);
      //  assertEquals(mem.getByte(hand), mysem.serialize().length);
    }
    
    public void testDump() throws Exception {
        Handle hand = new Handle(0, mysem3.serialize().length);
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        //mem.dump();
        System.out.println("length: " + mysem.serialize().length);
        mem.insert(mysem.serialize(), mysem.serialize().length);
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        Seminar mysem4 = new Seminar(1739, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem4.serialize(), mysem4.serialize().length);
        Seminar mysem5 = new Seminar(1839, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem5.serialize(), mysem5.serialize().length);
        Seminar mysem6 = new Seminar(1939, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem6.serialize(), mysem6.serialize().length);
        Seminar mysem7 = new Seminar(2039, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem7.serialize(), mysem7.serialize().length);
        Seminar mysem8 = new Seminar(2139, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem8.serialize(), mysem8.serialize().length);
        mem.dump();
    }
    
    public void testMinPowerOfTwo() {
        assertEquals(mem.minPowerTwo(32), 6);
        assertEquals(mem.minPowerTwo(100), 7);
    }
    
    public void testMergeIndex() {
        assertEquals(mem.mergeIndex(25, 5), 20);
        assertEquals(mem.mergeIndex(100, 25), 125);
    }
}