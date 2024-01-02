import java.util.ArrayList;
import java.util.List;

public class AllTradies {

    // fields
    private final List<Tradie> tradieDatabase = new ArrayList<>();

    // methods
    public void addTradie(Tradie tradie) {
        this.tradieDatabase.add(tradie);
    }

    public List<Tradie> getTradieDatabase() {
        return tradieDatabase;
    }

}
