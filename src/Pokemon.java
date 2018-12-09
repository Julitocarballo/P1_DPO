public class Pokemon {
    private int id;
    private String name;
    private int capture_rate;
    Legendary legendary = new Legendary();
    Mythical mythical = new Mythical();

    public Pokemon() {

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
