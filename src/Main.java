import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
            loginToProfile();

            System.out.println("Are you a customer looking for help, or a tradie looking for work? (customer/tradie)");
            String profileType = input.next();
            SearchFilter searchFilter = setSearchFilter(profileType);
            processSearch(searchFilter);

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

    private static void loginToProfile() {

        String username = "";
        String password = "";
        while (username.equals("")) {
            System.out.println("Username:");
            username = input.next();
            System.out.println("Password:");
            password = input.next();
            if (!checkProfile(username, password)) {
                System.out.println("The username and/or password is incorrect, please try again.");
                username = "";
            }
        }
        System.out.println("Hello " + username + "! What would you like to do?");
    }

    /**
     * method to check if entered username already exists in the database
     * @param username String representing the entered username
     * @return true if username exists, false if not
     */
    private static boolean checkProfileUsername(String username) {
        return allProfiles.getProfileUsername(username) != null;
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

        String username = "";
        while (username.equals("")) {
            System.out.println("Enter your username:");
            username = input.next();
            if (checkProfileUsername(username)) {
                System.out.println("The entered username already exists, please try another one.");
                username = "";
            }
        }
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
            while (occupationInt < 1 || occupationInt > 3) {
                System.out.println("What type of tradie do you need help from? \n1 - Carpenter" +
                "\n2 - Plumber \n3 - Electrician");
                String occupationInput = input.next();
                try {
                    occupationInt = Integer.parseInt(occupationInput);
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input. Please only user valid integers (1, 2, 3)");
                }
            }

            occupation = switch (occupationInt) {
                case 1 -> "Carpenter";
                case 2 -> "Plumber";
                case 3 -> "Electrician";
                default -> occupation;
            };
        }

        return new SearchFilter(profileType, occupation);
    }

    /**
     * method to display profiles that match entered filters
     * @param searchFilter entered filters by the user
     */
    private static void processSearch(SearchFilter searchFilter) {

        List<Profile> matchingProfiles = allProfiles.findMatches(searchFilter);
        if (matchingProfiles.isEmpty()) {
            System.out.println("Sorry, but there are no tradies available with your search");
        } else {
            System.out.println("The following tradies are available: \n");
            for (Profile p : matchingProfiles) {
                System.out.println(p.getProfileDescription());
            }
        }
    }

}

