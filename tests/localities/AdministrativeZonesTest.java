package localities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdministrativeZonesTest {
    private AdministrativeZones zones;

    @Before
    public void setUp() throws Exception {
        zones = new AdministrativeZones();
    }

    @Test
    public void getAdministrativeZonesIsNotNull() throws Exception {
        assertNotNull(zones);
    }

    @Test
    public void municipalityIsPairedWithCorrectRegion() throws Exception {
        
    }
}