import com.google.gson.JsonObject;

public class Gym {
    private String name;
    private Location location;

    public Gym(String name, JsonObject locationn) {
        this.name = name;
        location = new Location(locationn.get("latitude").getAsFloat(), locationn.get("longitude").getAsFloat());
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
