public class Profile {

    // fields
    private final String name;
    private final long phoneNumber;
    private final String occupation;

    // methods

    /**
     * constructor creating a Tradie object
     * @param name the name of the tradie
     * @param phoneNumber the phone number of the tradie
     * @param occupation the occupation of the tradie
     */
    public Profile(String name, long phoneNumber, String occupation) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.occupation = occupation;
    }

    /**
     * @return the name of the tradie
     */
    public String getName() {
        return name;
    }

    /**
     * @return the phone number of the tradie
     */
    public long getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @return the occupation of the tradie
     */
    public String getOccupation() {
        return occupation;
    }

}
