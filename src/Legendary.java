public class Legendary extends Pokemon {
    private int id;
    private String kind;
    private Gym gym;

    public Legendary() {
        Gym gym = new Gym();
    }

    public int getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public Gym getGym() {
        return gym;
    }
}
