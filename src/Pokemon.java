public class Pokemon {
    private int id;
    private String name;
    private int capture_rate;
    private Legendary legendary;
    private Mythical mythical;

    public Pokemon(int id, String name, int capture_rate, Legendary legendary, Mythical mythical) {
        this.id = id;
        this.name = name;
        this.capture_rate = capture_rate;
        this.legendary = legendary;
        this.mythical = mythical;
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

    public Legendary getLegendary() {
        return legendary;
    }

    public Mythical getMythical() {
        return mythical;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    public void setLegendary(Legendary legendary) {
        this.legendary = legendary;
    }

    public void setMythical(Mythical mythical) {
        this.mythical = mythical;
    }
}
