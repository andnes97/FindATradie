import java.io.FileWriter;
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

    /**
     * main method
     * @param args
     */
    public static void main(String[] args) {
        allProfiles = loadProfilesFromFile();

        System.out.println("Welcome to Find A Tradie! \n\n1. Log in \n2. Register new profile" +
                "\n3. Exit program");
        int menuSelection = input.nextInt();

        if (menuSelection == 1) {
            System.out.println("Log in to existing user");

            System.out.println("Username:");
            String username = input.next();
            System.out.println("Password:");
            String password = input.next();

            if (!checkProfile(username, password)) {
                System.out.println("No such username/password combination exists, please try again.");
            } else System.out.println("Hello " + username + "! What would you like to do?");

            System.out.println("Are you a customer looking for help, or a tradie looking for work? (customer/tradie)");
            String profileType = input.next();
            SearchFilter searchFilter = setSearchFilter(profileType);


        } else if (menuSelection == 2) {
            System.out.println("Register a new profile");
            createProfile();

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

            String username = fileContentFeature[0];
            String password = fileContentFeature[1];
            String profileType = fileContentFeature[2];
            String name = fileContentFeature[3];
            long phoneNumber = 0;
            try {
                phoneNumber = Long.parseLong(fileContentFeature[4]);
            } catch (NumberFormatException n) {
                System.out.println("Error, phone number could not be parsed on line" + (i + 1));
                System.exit(0);
            }
            String occupation = fileContentFeature[5];

            Profile profile = new Profile(username, password, profileType, name, phoneNumber, occupation);
            allProfilesDatabase.addProfile(profile);
        }

        return allProfilesDatabase;
    }

    /**
     * method to check if entered username and password combination is in the database (will be more complex later)
     * @param username String representing the entered username
     * @param password String representing the entered password
     * @return true if user exists, false if not
     */
    private static boolean checkProfile(String username, String password) {
        return allProfiles.getProfile(username, password) != null;
    }

    /**
     * method to create a new profile and write it to profileData.txt
     */
    private static void createProfile() {

        System.out.println("Enter your username:");
        String username = input.next();
        System.out.println("Set your new password:");
        String password = input.next();
        System.out.println("Are you a customer or a tradie? (customer/tradie):");
        String profileType = input.next();
        input.nextLine();
        System.out.println("Enter your full name:");
        String name = input.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = input.next();
        System.out.println("Enter your occupation if you are registered as a tradie");
        String occupation = input.next();

        String newProfileEntry = String.format("%s,%s,%s,%s,%s,%s",username,password,profileType,name,phoneNumber,occupation);

        try (FileWriter fileWriter = new FileWriter(filePath,true)) {
            fileWriter.write(System.lineSeparator() + newProfileEntry);
            System.out.println("New profile created and added to our system.");
        } catch (IOException io) {
            System.out.println("Error, something happened when trying to write to the file.");
        }
    }

    /**
     * method to get users criteria to set filters for the search
     * @param profileType the type of the profile (customer/tradie)
     * @return a SearchFilter object with selected filters
     */
    private static SearchFilter setSearchFilter(String profileType) {

        System.out.println("You are searching for a " + profileType + ".");
        String occupation = "";
        if (profileType.equals("customer")) {
            int occupationInt = -1;
            while (occupationInt < 1 || occupationInt > 4) {
                System.out.println("What type of tradie do you need help from? \n1 - Carpenter" +
                "\n2 - Plumber \n3 - Electrician \n4 - None, exit program");
                occupation = input.next();
                try {
                    occupationInt = Integer.parseInt(occupation);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input. Please only user valid integers (1, 2, 3, 4)");
                }
            }
        }

        return new SearchFilter(profileType, occupation);
    }

}

