package localities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abjor on 2017-05-02.
 */
public class AdministrativeZones {
    private Map<Region, List<Municipality>> regionListMap;

    public AdministrativeZones() {
        this.regionListMap = new HashMap<>();
    }

    // TODO: Implement this method with municipalities
    private List<Municipality> createMunicipalities() {
        return null;
    }

    // TODO: Implement this method with regions
    private List<Region> createRegions() {
        return null;
    }

    private void createRegionListMap() {
        List<Municipality> municipalities = createMunicipalities();
        List<Region> regions = createRegions();

        // TODO: Map the municipalities to the regions based on their region codes
    }
}
