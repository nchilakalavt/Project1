import student.TestCase;
public class CommandProcessorTest extends TestCase{
    public void testCommandProcessor() {
        SemManager.main(new String[] {"512", "4", "P1Sample_input.txt"});
        
    }
}
