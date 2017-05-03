package localities;

import utilities.ParseMunicipalities;
import utilities.ParseRegions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministrativeZones {
    private Map<Region, List<Municipality>> regionListMap;

    public AdministrativeZones() {
        this.regionListMap = new HashMap<>();
        createRegionListMap();
    }

    private void createRegionListMap() {
        List<Municipality> municipalities = ParseMunicipalities.getMunicipalities("resources\\municipalities.txt");
        List<Region> regions = ParseRegions.getRegions("resources\\regions.txt");

        for (Region region : regions) {
            regionListMap.put(region, new ArrayList<>());
            for (Municipality municipality : municipalities) {
                if (municipality.getRegionCode().equals(region.getRegionCode())) {
                    regionListMap.get(region).add(municipality);
                }
            }
        }
    }

    public Map<Region, List<Municipality>> getRegionListMap() {
        return regionListMap;
    }
}
