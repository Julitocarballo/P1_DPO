import com.google.gson.JsonObject;

public class Gym {
    private String name;
    private Location location;

    public Gym(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

}
