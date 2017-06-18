package localities;

import utilities.ParseMunicipalities;
import utilities.ParseRegions;

import java.util.*;

public class AdministrativeZones {
    private Map<Region, List<Municipality>> regionMap;

    public AdministrativeZones() {
        this.regionMap = new TreeMap<>();
        createRegionListMap();
    }

    private void createRegionListMap() {
        List<Municipality> municipalities = ParseMunicipalities.getMunicipalities("resources\\localities\\municipalities.txt");
        List<Region> regions = ParseRegions.getRegions("resources\\localities\\regions.txt");

        for (Region region : regions) {
            regionMap.put(region, new ArrayList<>());
            for (Municipality municipality : municipalities) {
                if (municipality.getRegionCode().equals(region.getRegionCode())) {
                    regionMap.get(region).add(municipality);
                }
            }
        }
    }

    public Map<Region, List<Municipality>> getRegionMap() {
        return regionMap;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Region region : regionMap.keySet()) {
            builder.append("\n" + region.getName() + " (" + region.getRegionCode() + "): \n");
            for (Municipality municipality : regionMap.get(region)) {
                builder.append("    " + municipality.getName() + "\n");
            }
        }
        return builder.toString();
    }
}
