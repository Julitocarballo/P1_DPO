import com.google.gson.JsonObject;

public class Legendary extends Pokemon {

    private Gym gym;
    private String kind;

    public Legendary(int id, String name, int capture_rate, Gym gym, String kind) {
        super(id, name, capture_rate);
        this.gym =gym;
        this.kind = kind;

    }

    public Gym getGym() {
        return gym;
    }

}
