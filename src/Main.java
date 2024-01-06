import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    // fields
    public static Scanner input = new Scanner(System.in);
    private static final String filePath = "./profileData.txt";
    private static AllProfiles allProfiles;

    // methods
    public static void main(String[] args) {
        allProfiles = loadProfilesFromFile();

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
     * method loading content from data file as Profile objects in an AllProfiles instance
     * @return allProfilesDatabase: a AllProfiles object containing each individual profile
     */
    private static AllProfiles loadProfilesFromFile() {

        AllProfiles allProfilesDatabase = new AllProfiles();
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

            Profile profile = new Profile(name, phoneNumber, occupation);
            allProfilesDatabase.addProfile(profile);
        }

        return allProfilesDatabase;
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
     * method printing information from every profile in the database
     * (eventually only print info from selected profiles/tradies)
     */
    private static void printProfileInfo() {
        List<Profile> availableProfiles = allProfiles.getProfileDatabase();
        for (Profile profile : availableProfiles) {
            System.out.println(profile.getName() + "\nOccupation: " + profile.getOccupation()
                    + "\nPhone number: " + profile.getPhoneNumber() + "\n");
        }

    }

}

