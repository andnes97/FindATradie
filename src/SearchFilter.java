/**
 * Class to represent features used while searching for matching profiles
 */
public class SearchFilter {

    // fields
    private final String profileTypeFilter;
    private final String occupationFilter;

    /**
     * Constructor creating desired filters for searching
     * @param profileTypeFilter
     * @param occupationFilter
     */
    public SearchFilter(String profileTypeFilter, String occupationFilter) {
        this.profileTypeFilter = profileTypeFilter;
        this.occupationFilter = occupationFilter;
    }

    /**
     * @return the type of the profile (customer/tradie)
     */
    public String getProfileTypeFilter() {
        return profileTypeFilter;
    }

    /**
     * @return the occupation of the profile
     */
    public String getOccupationFilter() {
        return occupationFilter;
    }
}
