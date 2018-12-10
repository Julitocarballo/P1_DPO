public class Legendary extends Pokemon {
    private int id;
    private String kind;
    private Gym gym;

    public Legendary(int id, String name, int capture_rate, Legendary legendary, Mythical mythical, int id1, String kind, Gym gym) {
        super(id, name, capture_rate, legendary, mythical);
        this.id = id1;
        this.kind = kind;
        this.gym = gym;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}
