import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class GetMapsDataTest {
    /**
     * This tests the full file addresses.txt. The goal is for exceptions not to be thrown.
     */
    @Test
    public void addressesLargeTest() throws Exception {
        // not really a test, but if this passes, we know the API calling is working
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("addresses.txt").getFile());
        String[] args = { file.getAbsolutePath() };

        GetMapsData.main(args);
    }

    /**
     * Tests the smaller addresses-small.txt file
     */
    @Test
    public void addressesSmallTest() throws Exception {
        // not really a test, but if this passes, we know the API calling is working
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("addresses-small.txt").getFile());
        String[] args = { file.getAbsolutePath() };

        GetMapsData.main(args);
    }

}