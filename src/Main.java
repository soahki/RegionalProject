import localities.Municipality;
import utilities.ParseMunicipalities;

/**
 * Created by abjor on 2017-05-02.
 */
public class Main {
    public static void main(String[] args) {
        for (Municipality municipality : ParseMunicipalities.getMunicipalities("resources\\municipalities.txt")) {
            System.out.println(municipality);
        }
    }
}
