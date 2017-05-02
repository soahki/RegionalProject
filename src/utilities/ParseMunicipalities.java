package utilities;

import localities.Municipality;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abjor on 2017-05-02.
 */
public class ParseMunicipalities {
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
