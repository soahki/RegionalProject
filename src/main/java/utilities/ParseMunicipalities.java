package utilities;

import localities.Municipality;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Read txt-file to parse Municipality-objects from it. The txt is set up with name, region code and municipality code,
 * separted by comma. The class contains the static method getMunicipalities which returns a List of Municipality-objects,
 * from the passed path (String).
 */
public class ParseMunicipalities {

    /**
     * Parses Municipality-objects from the passed String path to a text-file. The text-file should be set up accordingly:
     * [String name], [String regionCode], [String municipalityCode]
     * ...
     * Returns a List of Municipality-objects.
     * @param path
     * @return
     */
    public static List<Municipality> getMunicipalities(String path) {
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
