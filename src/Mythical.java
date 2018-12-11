import com.google.gson.JsonObject;

public class Mythical extends Pokemon {
    private String kind;
    private Recerca special_research;

    public Mythical(int id, String kind,  Recerca special_research, int capture_rate, String name) {
        super(id, name, capture_rate);
        this.kind = kind;
        this.special_research = special_research;

    }

    public Recerca getSpecial_research() {
        return special_research;
    }

    public void setSpecial_research(Recerca special_research) {
        this.special_research = special_research;
    }
}
