public class User {

    private int monedes;

    public User() {
        monedes = 1000;
    }

    public int getMonedes() {
        return monedes;
    }

    public void setMonedes(int monedes) {
        this.monedes += monedes;
    }

}
