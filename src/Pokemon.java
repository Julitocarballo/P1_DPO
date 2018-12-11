public class Pokemon {
    private int id;
    private String name;
    private int capture_rate;

    public Pokemon(int id, String name, int capture_rate) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }


}
