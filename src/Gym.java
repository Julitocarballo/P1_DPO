public class Gym {
    private String name;
    Location location = new Location();
    public Gym() {

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
