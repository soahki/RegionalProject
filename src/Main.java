import localities.Municipality;
import localities.Region;
import utilities.ParseMunicipalities;
import utilities.ParseRegions;

public class Main {
    public static void main(String[] args) {
        for (Region region : ParseRegions.getRegions("resources\\regions.txt")) {
            System.out.println(region);
        }
    }
}
