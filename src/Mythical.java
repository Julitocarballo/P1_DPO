public class Mythical extends Pokemons {
    private int id;
    private String kind;
    private Recerca special_research;

    public Mythical() {
        Recerca speacial_research = new Recerca();
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
}
