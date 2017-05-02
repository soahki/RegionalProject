package localities;

/**
 * Created by abjor on 2017-05-02.
 */
public class Municipality {
    private String name;
    private String municipalityCode;
    private String regionCode;

    public Municipality(String name, String municipalityCode, String regionCode) {
        this.name = name;
        this.municipalityCode = municipalityCode;
        this.regionCode = regionCode;
    }

    public String getName() {
        return name;
    }

    public String getMunicipalityCode() {
        return municipalityCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "name='" + name + '\'' +
                '}';
    }
}
