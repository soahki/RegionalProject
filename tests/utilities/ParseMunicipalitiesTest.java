package utilities;

import localities.Municipality;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by abjor on 2017-05-02.
 */
public class ParseMunicipalitiesTest {
    private List<Municipality> municipalities;

    @Before
    public void setUp() throws Exception {
        municipalities = ParseMunicipalities.getMunicipalities("resources\\municipalities.txt");
    }

    @Test
    public void parsingFileDoNotReturnNull() throws Exception {
        assertNotNull(municipalities);
    }

    @Test
    public void parsingFileReturnsCorrectAmountOfMunicipalities() throws Exception {
        assertEquals(290, municipalities.size());
    }
}