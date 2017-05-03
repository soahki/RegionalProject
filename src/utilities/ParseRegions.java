package utilities;

import localities.Region;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParseRegions {
    public static List<Region> getRegions(String path) {
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
