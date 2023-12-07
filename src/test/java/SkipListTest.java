import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SkipListTest {
    private SkipList skipList;

    @Before
    public void setUp() {
        skipList = new SkipList();
        skipList.insert(3);
        skipList.insert(6);
        skipList.insert(2);
        skipList.insert(7);
        skipList.insert(5);
    }

    @Test
    public void testContainsExistingElement() {
        assertTrue(skipList.contains(6));
    }

    @Test
    public void testContainsNonExistingElement() {
        assertFalse(skipList.contains(4));
    }

    @Test
    public void testInsertion() {
        skipList.insert(4);
        assertTrue(skipList.contains(4));
    }

    @Test
    public void testDeletion() {
        skipList.delete(6);
        assertFalse(skipList.contains(6));
    }
}
