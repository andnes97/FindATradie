import java.util.ArrayList;
import java.util.List;

public class AllProfiles {

    // fields
    private final List<Profile> profileDatabase = new ArrayList<>();

    // methods
    public void addProfile(Profile profile) {
        this.profileDatabase.add(profile);
    }

    public List<Profile> getProfileDatabase() {
        return profileDatabase;
    }

}
