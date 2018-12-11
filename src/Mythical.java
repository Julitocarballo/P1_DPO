import com.google.gson.JsonObject;

public class Mythical extends Legend {
    private Recerca special_research;

    public Mythical(int id, String kind,  JsonObject special_researchh) {
        super(id, kind);
        special_research = new Recerca( special_researchh.get("name").getAsString(), special_researchh.get("quests").getAsJsonArray());
    }

    public Recerca getSpecial_research() {
        return special_research;
    }

    public void setSpecial_research(Recerca special_research) {
        this.special_research = special_research;
    }
}
