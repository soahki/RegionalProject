package localities;

public class Region implements Comparable<Region>, Entity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        return (name != null ? name.equals(region.name) : region.name == null) && (regionCode != null ? regionCode.equals(region.regionCode) : region.regionCode == null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (regionCode != null ? regionCode.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Region o) {
        if (this.getRegionCode().equals(o.getRegionCode())) {
            return 0;
        }

        if (this.getRegionCode().equals("AB")) {
            return -1;
        }
        if (o.getRegionCode().equals("AB")) {
            return 1;
        }

        if (this.getRegionCode().length() < 2) {
            if (o.getRegionCode().length() == 2) {
                return -1;
            } else {
                return this.getRegionCode().compareTo(o.getRegionCode());
            }
        } else {
            if (o.getRegionCode().length() < 2) {
                return 1;
            }
            return this.getRegionCode().compareTo(o.getRegionCode());
        }
    }

    @Override
    public String getID() {
        return regionCode;
    }

    @Override
    public String getType() {
        return "Region";
    }
}
