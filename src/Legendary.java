import com.google.gson.JsonObject;

public class Legendary extends Legend {

    private Gym gym;

    public Legendary(int id, String kind, JsonObject gymm) {
        super(id, kind);
        gym = new Gym (gymm.get("name").getAsString(), gymm.get("location").getAsJsonObject());

    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}
