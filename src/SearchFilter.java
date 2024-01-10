/**
 * Class to represent features used while searching for matching profiles
 */
public class SearchFilter {

    // fields
    private final String profileType;
    private final String occupation;

    /**
     * Constructor creating desired filters for searching
     * @param profileType
     * @param occupation
     */
    public SearchFilter(String profileType, String occupation) {
        this.profileType = profileType;
        this.occupation = occupation;
    }

    /**
     * @return the type of the profile (customer/tradie)
     */
    public String getProfileType() {
        return profileType;
    }

    /**
     * @return the occupation of the profile
     */
    public String getOccupation() {
        return occupation;
    }
}
