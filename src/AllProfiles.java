import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllProfiles {

    private final Set<Profile> profileSet = new HashSet<>();

    // methods

    /**
     * method adding a profile to the profileSet database
     * @param profile a Profile object
     */
    public void addProfile(Profile profile) {
        this.profileSet.add(profile);
    }

    /**
     * method getting a Profile object, given their username and password
     * @param username a String representing profile's username
     * @param password a String representing profile's password
     * @return a Profile object if username/password is valid, else null
     */
    public Profile getProfile(String username, String password) {
        for (Profile p: profileSet) {
            if (p.getUsername().equals(username) && p.getPassword().equals(password)) return p;
        }
        return null;
    }

    /**
     * method getting a Profile object given the username
     * @param username a String representing profile's username
     * @return a Profile object if username exists
     */
    public Profile getProfileUsername(String username) {
        for (Profile p: profileSet) {
            if (p.getUsername().equals(username)) return p;
        }
        return null;
    }

    /**
     * method finding profiles that match users entered filters to profiles in the database
     * @param searchFilter a SearchFilter object representing the users entered filters
     * @return matchingProfiles: A list of profiles that match the users filters
     */
    public List<Profile> findMatches(SearchFilter searchFilter) {

        List<Profile> matchingProfiles = new ArrayList<>();
        for (Profile p : profileSet) {
            if (p.getOccupation().equals(searchFilter.getOccupation())) matchingProfiles.add(p);
        }

        return matchingProfiles;
    }

}
