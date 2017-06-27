package localities;

import org.junit.Before;
import org.junit.Test;
import utilities.parser.TextParser;

import static org.junit.Assert.*;

public class TextParserTest {
    private TextParser zones;

    @Before
    public void setUp() throws Exception {
        zones = new TextParser();
    }

    @Test
    public void getAdministrativeZonesIsNotNull() throws Exception {
        assertNotNull(zones);
    }

    @Test
    public void municipalityIsPairedWithCorrectRegion() throws Exception {
        boolean isCorrect = true;
        for (Region reg : zones.getRegionMap().keySet()) {
            if (!reg.getRegionCode().equals(zones.getRegionMap().get(reg).get(0).getRegionCode())) {
                isCorrect = false;
            }
        }

        assertTrue(isCorrect);
    }
}