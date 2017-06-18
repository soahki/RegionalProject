package utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class JDBCRunnerTest {

    @Test
    public void classIsFound() throws Exception {
        // setup test
        boolean success = true;

        // run
        try {
            JDBCRunner.start();
        } catch (ClassNotFoundException ex) {
            success = false;
        }

        // evaluate
        assertTrue(success);
    }
}
