
public class Profile {

    // fields
    private final String username;
    private final String password;
    private final String profileType;
    private final String name;
    private final long phoneNumber;
    private final String occupation;

    // methods
    /**
     * constructor to create a Profile object
     * @param username the profile username
     * @param password the profile password
     * @param profileType the type of profile (customer/tradie)
     * @param name the name of the profile
     * @param phoneNumber the phone number of the profile
     * @param occupation the occupation of the profile if it is a tradie
     */
    public Profile(String username, String password, String profileType, String name, long phoneNumber, String occupation) {
        this.username = username;
        this.password = password;
        this.profileType = profileType;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
    }

    /**
     * @return the profile username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the profile password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the type of profile (customer/tradie)
     */
    public String getProfileType() {
        return profileType;
    }

    /**
     * @return the name of the profile
     */
    public String getName() {
        return name;
    }

    /**
     * @return the phone number of the profile
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the occupation of the profile if it is a tradie
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * method to get a description of the profile
     * @return String object containing a profile description
     */
    public String getProfileDescription() {
        return "Name: " + this.getName() + "\nPhone number: " + this.getPhoneNumber()
                + "\nOccupation: " + this.getOccupation() + "\n";
    }

}
