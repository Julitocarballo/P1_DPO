public class Legendary extends Pokemon {
    private int id;
    private String kind;
    private Gym gym;

    public Legendary() {

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
