import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    // fields
    public static Scanner input = new Scanner(System.in);
    private final static String filePath = "./tradieDataFile.txt";
    private static AllTradies allTradies;

    // methods
    public static void main(String[] args) {
        allTradies = loadTradiesFromFile(filePath);

        System.out.println("Welcome to Find A Tradie! \n\n\t1. Log in \n\t2. Register new profile" +
                "\n\t3. Exit program");
        int menuSelection = input.nextInt();

        if (menuSelection == 1) {
            System.out.println("Log in to existing user");
            logIn();
        } else if (menuSelection == 2) {
            System.out.println("Register a new profile");
        } else if (menuSelection == 3) {
            System.out.println("Exiting the program");
            System.exit(0);
        }
    }

    /**
     * method loading content from data file as Tradie objects in an AllTradies instance
     * @param filePath a path to the data containing information about tradies
     * @return allTradiesDatabase: a AllTradies object containing each individual tradie
     */
    private static AllTradies loadTradiesFromFile(String filePath) {

        AllTradies allTradiesDatabase = new AllTradies();
        Path path = Path.of(filePath);

        List<String> fileContent = null;
        try {
            fileContent = Files.readAllLines(path);
        } catch (IOException io) {
            System.out.println("Error, file could not be loaded from given file path");
            System.exit(0);
        }

        // split every line in data file into independent bits of information
        for (int i = 1; i < fileContent.size(); i++) {
            String[] fileContentFeature = fileContent.get(i).split(",");

            String name = fileContentFeature[0];
            long phoneNumber = 0;
            try {
                phoneNumber = Long.parseLong(fileContentFeature[1]);
            } catch (NumberFormatException n) {
                System.out.println("Error, phone number could not be parsed on line" + (i + 1));
                System.exit(0);
            }
            String occupation = fileContentFeature[2];

            Tradie tradie = new Tradie(name, phoneNumber, occupation);
            allTradiesDatabase.addTradie(tradie);
        }

        return allTradiesDatabase;
    }

    /**
     * method for logging in (no authentication or access yet)
     */
    private static void logIn() {
        System.out.println("Username:");
        String username = input.next();

        System.out.println("Password:");
        String password = input.next();
        System.out.println("\nWelcome " + username + "! What would you like to do?");
    }

    /**
     * method printing information from every tradie in the database
     * (eventually only print info from selected tradies)
     */
    private static void printTradieInfo() {
        List<Tradie> availableTradies = allTradies.getTradieDatabase();
        for (Tradie tradie: availableTradies) {
            System.out.println(tradie.getName() + "\nOccupation: " + tradie.getOccupation()
                    + "\nPhone number: " + tradie.getPhoneNumber() + "\n");
        }

    }

}

