public class Mythical extends Pokemon {
    private int id;
    private String kind;
    private Recerca special_research;

    public Mythical(int id, String name, int capture_rate, Legendary legendary, Mythical mythical, int id1, String kind, Recerca special_research) {
        super(id, name, capture_rate, legendary, mythical);
        this.id = id1;
        this.kind = kind;
        this.special_research = special_research;
    }

    public int getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public Recerca getSpecial_research() {
        return special_research;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setSpecial_research(Recerca special_research) {
        this.special_research = special_research;
    }
}
