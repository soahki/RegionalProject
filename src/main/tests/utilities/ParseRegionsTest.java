package utilities;

import localities.Region;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParseRegionsTest {
    private List<Region> regions;

    @Before
    public void setUp() throws Exception {
        regions = ParseRegions.getRegions("resources\\localities\\regions.txt");
    }

    @Test
    public void parsingDoNotReturnNull() throws Exception {
        assertNotNull(regions);
    }

    @Test
    public void parsingReturnsCorrectSize() throws Exception {
        assertEquals(21, regions.size());
    }

    @Test
    public void parsingCreatesCorrectRegionSyntax() throws Exception {
        boolean isCorrect = true;

        for (Region region : regions) {
            if (region.getName().length() == 0 ||
                    region.getName().contains(",") ||
                    region.getRegionCode().length() == 0 ||
                    region.getRegionCode().length() > 2 ) {
                isCorrect = false;
                break;
            }
        }

        assertTrue(isCorrect);
    }
}