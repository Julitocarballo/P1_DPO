public class Pokemons {
    private int id;
    private String name;
    private int capture_rate;

    public Pokemons(int id, String name, int capture_rate) {
        this.id = id;
        this.name = name;
        this.capture_rate = capture_rate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapture_rate() {
        return capture_rate;
    }
}
