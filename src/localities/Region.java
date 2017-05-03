package localities;

/**
 * Created by abjor on 2017-05-02.
 */
public class Region {
    private String name;
    private String regionCode;

    public Region(String name, String regionCode) {
        this.name = name;
        this.regionCode = regionCode;
    }

    public String getName() {
        return name;
    }

    public String getRegionCode() {
        return regionCode;
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                '}';
    }
}
