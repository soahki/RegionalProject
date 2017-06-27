package localities;

public class Municipality implements Entity{
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

    @Override
    public String getID() {
        return municipalityCode;
    }

    @Override
    public String getType() {
        return "Municipality";
    }
}
