package utilities.parser;

import localities.Municipality;
import localities.Region;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TextParser {
    private Map<Region, List<Municipality>> regionMap;

    public TextParser() {
        this.regionMap = new TreeMap<>();
        createRegionListMap();
    }

    private void createRegionListMap() {
        List<Municipality> municipalities = new ParseMunicipalities().getMunicipalities("./src/main/resources/localities/municipalities.txt");
        List<Region> regions = new ParseRegions().getRegions("./src/main/resources/localities/regions.txt");

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

    class ParseRegions {
        public List<Region> getRegions(String path) {
            List<Region> regions = new ArrayList<>();
            try(
                    FileInputStream fis = new FileInputStream(path);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String name;
                    String regionCode;

                    if (line.equals(""))
                        continue;

                    String[] parseLine = line.split(", ");
                    if (parseLine.length != 2)
                        continue;

                    name = parseLine[0];
                    regionCode = parseLine[1];
                    regions.add(new Region(name, regionCode));
                }

            } catch(IOException ioe) {
                ioe.printStackTrace();
            }

            return regions;
        }
    }


    /**
     * Read txt-file to parse Municipality-objects from it. The txt is set up with name, region code and municipality code,
     * separted by comma. The class contains the static method getMunicipalities which returns a List of Municipality-objects,
     * from the passed path (String).
     */
    class ParseMunicipalities {

        /**
         * Parses Municipality-objects from the passed String path to a text-file. The text-file should be set up accordingly:
         * [String name], [String regionCode], [String municipalityCode]
         * ...
         * Returns a List of Municipality-objects.
         * @param path
         * @return
         */
        public List<Municipality> getMunicipalities(String path) {
            List<Municipality> municipalities = new ArrayList<>();

            try(
                    FileInputStream fis = new FileInputStream(path);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(fis))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String name;
                    String regionCode;
                    String municipalityCode;
                    if (line.equals(""))
                        continue;

                    String[] parseLine = line.split(",");
                    if (parseLine.length != 3)
                        continue;

                    name = parseLine[0].trim();
                    regionCode = parseLine[1].trim();
                    municipalityCode = parseLine[2].trim();

                    municipalities.add(new Municipality(name, municipalityCode, regionCode));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            return municipalities;
        }
    }

}
