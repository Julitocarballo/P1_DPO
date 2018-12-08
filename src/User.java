public class User {

    private int monedes;
    private int[] inventari;


    public int[] getInventari() {
        inventari[0] = 3;
        return inventari;
    }

    public void setcomprarInventari(int inventari, int i) {
        this.inventari[i] += inventari;
    }

    public User() {
        monedes = 1000;
    }

    public int getMonedes() {
        return monedes;
    }

    public void setsumarMonedes(int monedes) {
        this.monedes += monedes;
    }

    public void setrestarMonedes(int monedes){this.monedes -= monedes;}

}
