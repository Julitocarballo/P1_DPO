public class User {

    private int monedes;
    private int[] inventari;

    public User(int mida) {
        monedes = 1000;
        inventari = new int[mida];
        inventari[0] = 3;
    }


    public int[] getInventari() {
        return inventari;
    }

    public void setcomprarInventari(int unitats, int i) {
        inventari[i] += unitats;
    }

    public int getMonedes() {
        return monedes;
    }

    public void setsumarMonedes(int monedes) {
        this.monedes += monedes;
    }

    public void setrestarMonedes(int monedes){this.monedes -= monedes;}

}
