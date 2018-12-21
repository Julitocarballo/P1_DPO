
//Classe que hereta de Pokémon que serveix per emmagatzemar informació de un pokémon si aquest es llegendari.
public class Legendary extends Pokemon {
    //Atribut que serveix per emmagatzemar tota la informació de un gimnàs de un pokémon llegendari.
    private Gym gym;
    //Atribut que serveix per guardar el tipus de pokémon que es: Llegendari o mític.
    private String kind;
    //Constructor que ens permet crear objectes d’aquesta classe i utilitzar atributs de la super classe, en aquest cas la classe Pokémon.
    public Legendary(int id, String name, int capture_rate, Gym gym, String kind) {
        super(id, name, capture_rate);
        this.gym =gym;
        this.kind = kind;

    }
    //Mètode que retorna tota la informació de un gimnàs de un pokémon llegendari.
    public Gym getGym() {
        return gym;
    }

}
