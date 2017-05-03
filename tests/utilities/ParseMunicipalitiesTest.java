package utilities;

import localities.Municipality;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

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

    @Test
    public void municipalitiesHaveCorrectMunicipalityCodeSyntax() throws Exception {
            Pattern pattern = Pattern.compile("[0-9]{4}");
            Matcher matcher;
            boolean matches = true;

        for (Municipality municipality : municipalities) {
            matcher = pattern.matcher(municipality.getMunicipalityCode());
            matches = matcher.matches();
            if (!matches){
                break;
            }
        }

        assertTrue(matches);
    }
}