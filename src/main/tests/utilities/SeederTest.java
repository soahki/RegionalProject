package utilities;

import org.junit.Test;
import utilities.jdbc.Seeder;

import static org.junit.Assert.*;

public class SeederTest {

    @Test
    public void classIsFound() throws Exception {
        // setup test
        boolean success = true;

        // run
        try {
            Seeder.start();
        } catch (ClassNotFoundException ex) {
            success = false;
        }

        // evaluate
        assertTrue(success);
    }
}
