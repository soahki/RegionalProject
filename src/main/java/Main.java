import userInterface.UserInterface;
import utilities.JDBCRunner;
import utilities.ParseTables;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        UserInterface.launchUI(args);
        // JDBCRunner.seedDatabase();
    }
}
