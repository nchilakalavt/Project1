
import  student.TestCase;

public class MemManagerTest extends TestCase{
    private MemManager mem = new MemManager(512);
    public void testInsert() {
        mem.dump();
    }
}
