public class Mythical extends Pokemon {
    private int id;
    private String kind;
    Recerca special_research = new Recerca();

    public Mythical() {

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
