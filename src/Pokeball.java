public class Pokeball {
    private String name;
    private int capture_rate;
    private int price;

    public Pokeball(String name, int capture_rate, int price) {
        this.name = name;
        this.capture_rate = capture_rate;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public int getCapture_rate(){
        return capture_rate;
    }

    public int getPrice(){
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapture_rate(int capture_rate) {
        this.capture_rate = capture_rate;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
