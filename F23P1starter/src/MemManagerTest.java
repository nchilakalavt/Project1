
import  student.TestCase;

public class MemManagerTest extends TestCase{
    private MemManager mem = new MemManager(512);
    private String[] keywords = {"Good", "Bad", "Ugly"};
    private Seminar mysem3 = new Seminar(3, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    private Seminar mysem = new Seminar(1, "Seminar Title", "2405231000", 1,
        (short)15, (short)33, 125, keywords, "This is a great seminar");
    
    public void testInsert() throws Exception {
        //mem.dump();
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        //mem.dump();
        System.out.println("length: " + mysem.serialize().length);
        mem.insert(mysem.serialize(), mysem.serialize().length);
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
        byte[] byteArr = new byte[hand.getLength()];
        System.arraycopy(mem.getPool(), hand.getStartPos(), byteArr, 0, hand.getLength());
        for (int i = 0; i < byteArr.length; i++) {
            assertEquals(byteArr[i], mem.get(hand)[i]);
        }
    }
    
    public void testDump() throws Exception {
        Handle hand = new Handle(0, mysem.serialize().length);
        MemManager mem2 = new MemManager(512);
        Seminar mysem2 = new Seminar(2, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        Seminar mysem10 = new Seminar(10, "Seminar Title", "2405231000", 1,
            (short)15, (short)33, 125, keywords, "This is a great seminar");
        mem.insert(mysem.serialize(), mysem.serialize().length);
        mem.insert(mysem2.serialize(), mysem2.serialize().length);
        mem.insert(mysem3.serialize(), mysem3.serialize().length);
        mem.insert(mysem10.serialize(), mysem10.serialize().length);
        mem.dump();
        mem.remove(hand);
        mem.dump();
        //mem2.dump(hand);
        
    }
}
