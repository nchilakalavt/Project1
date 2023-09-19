import student.TestCase;

/**
 * @author Nirish Chilakala
 * @author Prat Chopra
 * @version 9/11/2023
 */
public class SemManagerTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
    public void testMInitx() {
        SemManager sem = new SemManager();
        assertNotNull(sem);
        // SemManager.main(null);
    }
}
